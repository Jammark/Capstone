package com.capstone.progettofinale.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.auth.BadRequestException;
import com.capstone.progettofinale.common.IAuthenticationFacade;
import com.capstone.progettofinale.model.Acquisto;
import com.capstone.progettofinale.model.Alloggio;
import com.capstone.progettofinale.model.Appartamento;
import com.capstone.progettofinale.model.Hotel;
import com.capstone.progettofinale.model.Prenotazione;
import com.capstone.progettofinale.model.Volo;
import com.capstone.progettofinale.payload.AcquistoPayload;
import com.capstone.progettofinale.payload.PrenotazionePayload;
import com.capstone.progettofinale.repository.AcquistoRepository;
import com.capstone.progettofinale.repository.HotelRepository;
import com.capstone.progettofinale.repository.PrenotazioneRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrenotazioneService {

	@Autowired
	private IAuthenticationFacade auth;

	@Autowired
	private PrenotazioneRepository pRepo;

	@Autowired
	private AcquistoRepository aRepo;

	@Autowired
	private TrasportoService tSrv;

	@Autowired
	private HotelRepository hRepo;

	@Autowired
	private AlloggioService aSrv;

	@Autowired
	private MetaService mSrv;

	@Autowired
	private UserService uSrv;


	private Long getUserId() {
		return this.auth.getUser().getId();
	}

	public Prenotazione findById(Long id) {
		return this.pRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id prenotazione inesistente: " + id));
	}

	public Acquisto findAcquistoById(Long id) {
		return this.aRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id acquisto inesistente: " + id));
	}


	public List<Prenotazione> findAll() {
		return this.pRepo.findByUserId(getUserId());
	}

	public List<Prenotazione> findDaPagare() {
		return this.pRepo.findNonPagate(getUserId());
	}

	public List<Acquisto> findAllAcquisti() {
		return this.aRepo.findAll();
	}

	public List<Acquisto> riepilogoAcquisti() {
		return this.aRepo.findByUtenteId(getUserId());
	}

	public Prenotazione save(PrenotazionePayload pp) {
		boolean disp = this.aSrv.getDisponibilità(pp.getAlloggioId(), pp.getData(),
				pp.getDataFine(),
				pp.getNumeroPosti());
		boolean dispVoli = tSrv.getDisponibilitàVolo(pp.getTrasportoId(), pp.getNumeroPosti())
				&& tSrv.getDisponibilitàVolo(pp.getRitornoId(), pp.getNumeroPosti());
		if (!disp) {
			throw new BadRequestException(
					"Posti non disponibili per alloggio: " + aSrv.findById(pp.getAlloggioId()).getNome());
		}
		if (!dispVoli) {
			throw new BadRequestException("Posti non disponibili per i voli.");
		}
		log.info("ricevuta richiesta prenotazione: " + pp);
		Long g = ChronoUnit.DAYS.between(pp.getData(), pp.getDataFine());
		Alloggio al = aSrv.findById(pp.getAlloggioId());
		Prenotazione p = new Prenotazione(pp.getData(), g.intValue(), Optional.ofNullable(al.getMeta()).orElse(null),
				uSrv.findById(this.getUserId()), al, tSrv.findById(pp.getTrasportoId()),
				pp.getNumeroPosti());
		p.setRitorno(tSrv.findById(pp.getRitornoId()));
		p.setPrezzo(
				p.getAlloggio().getPrezzo() * p.getNumeroGiorni()
						+ (p.getTrasporto().getPrezzo() + p.getRitorno().getPrezzo()) * p.getNumeroPosti());
		
		if(p.getAlloggio() instanceof Appartamento a && a.getCapienza() < p.getNumeroPosti()) {
			throw new IllegalArgumentException("Capienza appartamento non sufficiente: " + a.getCapienza());
		}else if(p.getAlloggio() instanceof Hotel h && !h.isDisponibile(p.getNumeroPosti())) {
			throw new IllegalArgumentException("Posti hotel non disponibili: "+ h);
		}
		return this.pRepo.save(p);

	}
	
	public Acquisto acquistaPrenotazione(Long prenotazioneId) {
		Prenotazione p = this.findById(prenotazioneId);
		if (p.getUser() != null && p.getUser().getId().equals(getUserId())) {
			AcquistoPayload ap = new AcquistoPayload(null, LocalDate.now(), getUserId(), prenotazioneId, p.getPrezzo(),
					null);
			return this.saveAcquisto(ap);
		} else {
			throw new IllegalArgumentException(
					"Id prenotazione non valido: " + prenotazioneId + ", pre l'utente: " + auth.getUser());
		}
	}

	public Acquisto saveAcquisto(AcquistoPayload ap) {
		Prenotazione p = findById(ap.getPrenotazioneId());
		Acquisto a = new Acquisto(p.getPrezzo(), ap.getData(), p, uSrv.findById(ap.getUserId()));
		if (p.getAlloggio() instanceof Hotel h) {
			if (h.occupaPosti(p.getNumeroPosti())) {
				hRepo.save(h);
			} else {
				throw new IllegalArgumentException("Prenotazione non più valida, posti non disponibili: " + h);
			}
		}
		return this.aRepo.save(a);
	}

	public List<Prenotazione> findDaPagare(Long userId) {
		return this.pRepo.findNonPagate(userId);
	}

	public List<PrenotazionePayload> getPacchetti(Long metaId, int numPosti) {
		Alloggio a = aSrv.getMostRated(metaId);
		if (a == null) {
			log.info("nessun risultato per pacchetto.");
			return Collections.EMPTY_LIST;
		}
		List<Volo> voli = tSrv.findVoliDisponibili(a.getMeta().getId());
		log.info("pacchetti numero voli trovati: " + voli.size());
		return voli.stream().map(v -> {
			Volo ritorno = tSrv.findRitorno(v.getArrivo().getId(), v.getPartenza().getId(),
					v.getDataArrivo().plusDays(4).toLocalDate(), numPosti);
			if (ritorno == null) {
				log.info("nessun match per volo di ritorno.");
				return null;
			}
			Long g = ChronoUnit.DAYS.between(v.getDataPartenza(), ritorno.getDataArrivo());
			return new PrenotazionePayload(null, v.getDataPartenza().toLocalDate(),
					ritorno.getDataPartenza().toLocalDate(), g.intValue(), metaId,
					getUserId(),
					a.getId(),
					v.getId(), ritorno.getId(), 0, numPosti);
		}).filter(p -> p != null).toList();
	}
}
