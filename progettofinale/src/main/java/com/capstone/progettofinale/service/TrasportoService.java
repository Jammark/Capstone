package com.capstone.progettofinale.service;

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
		Volo v = new Volo(vp.getNome(), vp.getDescrizione(), vp.getDurata(), vp.getDataPartenza(), vp.getDataArrivo(),
				vp.getPostiDisponibili(), 0, vp.getPrezzo(), vp.getCompagnia(),
				sSrv.findAereoportoById(vp.getPartenzaId()), sSrv.findAereoportoById(vp.getArrivoId()),
				Optional.ofNullable(vp.getStopId()).map(val -> sSrv.findAereoportoById(val)).orElse(null));
		return this.vRepo.save(v);
	}

	public Tratta saveTratta(TrattaPayload tp) {
		Tratta t = new Tratta(tp.getNome(), tp.getDescrizione(), tp.getDurata(), tp.getDataPartenza(),
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
			a.setCompagniaAerea(ap.getCompagnia());
			a.setPartenza(sSrv.findAereoportoById(ap.getPartenzaId()));
			a.setArrivo(sSrv.findAereoportoById(ap.getArrivoId()));
			a.setStop(sSrv.findAereoportoById(ap.getStopId()));
			return this.vRepo.save(a);
		} else {
			throw new IllegalArgumentException("id aereoporto non valido: " + ap.getId());
		}
	}

	public Tratta updateTratta(TrattaPayload tp) {
		if (tp.getId() != null) {
			Tratta t = this.findTrattaById(tp.getId());
			setFields(t, tp);
			t.setPartenza(sSrv.findStazionePulmanById(tp.getPartenzaId()));
			t.setArrivo(sSrv.findStazionePulmanById(tp.getArrivoId()));
			return this.tRepo.save(t);
		} else {
			throw new IllegalArgumentException("id stazione non valido: " + tp.getId());
		}
	}

	private void setFields(Trasporto t, TrasportoPayload tp) {
		t.setDataArrivo(tp.getDataArrivo());
		t.setDataPartenza(tp.getDataPartenza());
		t.setDescrizione(tp.getDescrizione());
		t.setDurata(tp.getDurata());
		t.setNome(tp.getNome());
		t.setPostiDisponibili(tp.getPostiDisponibili());
		t.setPostiOccupati(tp.getPostiOccupati());
		t.setPrezzo(tp.getPrezzo());
	}

	public List<Volo> findVoliByArrivoEPartenza(Long partenzaId, Long arrivoId) {
		return this.vRepo.findByPartenzaCittàIdAndArrivoCittàId(partenzaId, arrivoId);
	}

	public List<Tratta> findTratteByArrivoEPartenza(Long partenzaId, Long arrivoId) {
		return this.tRepo.findByPartenzaCittàIdAndArrivoCittàId(partenzaId, arrivoId);
	}

}
