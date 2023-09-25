package com.capstone.progettofinale.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.model.Trasporto;
import com.capstone.progettofinale.model.Tratta;
import com.capstone.progettofinale.model.Volo;
import com.capstone.progettofinale.payload.TrasportoPayload;
import com.capstone.progettofinale.payload.TrattaPayload;
import com.capstone.progettofinale.payload.VoloPayload;
import com.capstone.progettofinale.repository.TrasportoRepository;
import com.capstone.progettofinale.repository.TrattaRepository;
import com.capstone.progettofinale.repository.VoloRepository;

@Service
public class TrasportoService {

	@Autowired
	private VoloRepository vRepo;

	@Autowired
	private TrattaRepository tRepo;

	@Autowired
	private TrasportoRepository repo;

	@Autowired
	private StazioneService sSrv;

	public Trasporto findById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new IllegalArgumentException("id trasporto non valido: " + id));
	}

	public Volo findVoloById(Long id) {
		return this.vRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("id volo non valido: " + id));
	}

	public Tratta findTrattaById(Long id) {
		return this.tRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id tratta pulman non valido: " + id));
	}

	public List<Volo> findAllVoli() {
		return this.vRepo.findAll();
	}

	public List<Tratta> findAllTratte() {
		return this.tRepo.findAll();
	}

	public Volo saveVolo(VoloPayload vp) {
		Duration durata = Duration.between(vp.getDataPartenza(), vp.getDataArrivo());
		Volo v = new Volo(vp.getNome(), vp.getDescrizione(), durata, vp.getDataPartenza(), vp.getDataArrivo(),
				vp.getPostiDisponibili(), 0, vp.getPrezzo(), vp.getCompagnia(),
				sSrv.findAereoportoById(vp.getPartenzaId()), sSrv.findAereoportoById(vp.getArrivoId()),
				Optional.ofNullable(vp.getStopId()).map(val -> sSrv.findAereoportoById(val)).orElse(null));
		return this.vRepo.save(v);
	}

	public Tratta saveTratta(TrattaPayload tp) {
		Tratta t = new Tratta(tp.getNome(), tp.getDescrizione(),
				Duration.between(tp.getDataPartenza(), tp.getDataArrivo()), tp.getDataPartenza(),
				tp.getDataArrivo(), tp.getPostiDisponibili(), 0, tp.getPrezzo(), tp.getNomeAzienda(),
				sSrv.findStazionePulmanById(tp.getPartenzaId()), sSrv.findStazionePulmanById(tp.getArrivoId()));
		return this.tRepo.save(t);
	}

	public void deleteVolo(Long id) {
		if (id != null) {
			Volo v = this.findVoloById(id);
			this.vRepo.delete(v);
		} else {
			throw new IllegalArgumentException("id volo non valido: " + id);
		}
	}

	public void deleteTratta(Long id) {
		if (id != null) {
			Tratta t = this.findTrattaById(id);
			this.tRepo.delete(t);
		} else {
			throw new IllegalArgumentException("id stazione non valido: " + id);
		}
	}

	public Volo updateVolo(VoloPayload ap) {
		if (ap.getId() != null) {
			Volo a = this.findVoloById(ap.getId());
			setFields(a, ap);
			a.setCompagniaAerea(Optional.ofNullable(ap.getCompagnia()).orElse(a.getCompagniaAerea()));
			a.setPartenza(
					Optional.ofNullable(ap.getPartenzaId()).map(sSrv::findAereoportoById).orElse(a.getPartenza()));
			a.setArrivo(Optional.ofNullable(ap.getArrivoId()).map(sSrv::findAereoportoById).orElse(a.getArrivo()));
			a.setStop(Optional.ofNullable(ap.getStopId()).map(sSrv::findAereoportoById).orElse(null));
			return this.vRepo.save(a);
		} else {
			throw new IllegalArgumentException("id aereoporto non valido: " + ap.getId());
		}
	}

	public Tratta updateTratta(TrattaPayload tp) {
		if (tp.getId() != null) {
			Tratta t = this.findTrattaById(tp.getId());
			setFields(t, tp);
			t.setPartenza(Optional.ofNullable(tp.getPartenzaId()).map(sSrv::findStazionePulmanById).orElse(t.getPartenza()));
			t.setArrivo(Optional.ofNullable(tp.getArrivoId()).map(sSrv::findStazionePulmanById).orElse(t.getArrivo()));
			return this.tRepo.save(t);
		} else {
			throw new IllegalArgumentException("id stazione non valido: " + tp.getId());
		}
	}

	private void setFields(Trasporto t, TrasportoPayload tp) {
		t.setDataArrivo(Optional.ofNullable(tp.getDataArrivo()).orElse(t.getDataArrivo()));
		t.setDataPartenza(Optional.ofNullable(tp.getDataPartenza()).orElse(t.getDataPartenza()));
		t.setDescrizione(Optional.ofNullable(tp.getDescrizione()).orElse(t.getDescrizione()));
		if (tp.getDataPartenza() != null && tp.getDataArrivo() != null) {
			t.updateDurata(Duration.between(tp.getDataPartenza(), tp.getDataArrivo()));
		}
		t.setNome(Optional.ofNullable(tp.getNome()).orElse(t.getNome()));
		t.setPostiDisponibili(Optional.ofNullable(tp.getPostiDisponibili()).orElse(t.getPostiDisponibili()));
		t.setPostiOccupati(Optional.ofNullable(tp.getPostiOccupati()).orElse(t.getPostiOccupati()));
		t.setPrezzo(Optional.ofNullable(tp.getPrezzo()).orElse(t.getPrezzo()));
	}

	public List<VoloPayload> findVoliByArrivoEPartenza(String partenzaNome, String arrivoNome, LocalDate data) {
		return this.vRepo.findByPartenzaCittàNomeAndArrivoCittàNomeAndDataPartenza(partenzaNome, arrivoNome,
				data.atStartOfDay()).stream().map(v -> {
					VoloPayload vp = new VoloPayload(v);
					vp.setPosti(v.getPostiDisponibili()
							- ((Long) this.vRepo.findPostiRimanenti(v.getId()).get(1)).intValue());
					return vp;

				}).toList();
	}

	public boolean getDisponibilitàVolo(Long id, int posti) {
		Volo v = this.findVoloById(id);
		return v.getPostiDisponibili() - (Long) this.vRepo.findPostiRimanenti(id).get(1) > posti;
	}

	public List<Tratta> findTratteByArrivoEPartenza(String partenza, String arrivo, LocalDate data) {
		return this.tRepo.findByPartenzaCittàNomeAndArrivoCittàNomeAndDataPartenza(partenza, arrivo, data);
	}

	public List<Volo> findVoliDisponibili(Long metaId) {
		return this.vRepo.findVoliDisponibili(metaId, LocalDate.now().atStartOfDay(),
				LocalDate.now().plusDays(100).atStartOfDay(), 1).stream().map(this.vRepo::findById)
				.map(e -> e.orElse(null)).filter(e -> e != null).toList();
	}

	public Volo findRitorno(Long pId, Long aId, LocalDate data, int posti) {
		Optional<Volo> val = this.vRepo.findVolorRitorno(pId, aId, data.atStartOfDay(), posti).map(this.vRepo::findById)
				.orElse(null);
		return val == null ? null : val.orElse(null);
	}
}
