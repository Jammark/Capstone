package com.capstone.progettofinale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.model.Aereoporto;
import com.capstone.progettofinale.model.StazionePulman;
import com.capstone.progettofinale.payload.AereoportoPayload;
import com.capstone.progettofinale.payload.StazionePulmanPayload;
import com.capstone.progettofinale.repository.AereoportoRepository;
import com.capstone.progettofinale.repository.StazionePulmanRepository;

@Service
public class StazioneService {

	@Autowired
	private AereoportoRepository aRepo;

	@Autowired
	private StazionePulmanRepository pRepo;

	public Aereoporto findAereoportoById(Long id) {
		return this.aRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id aereoporto non valido: " + id));
	}

	public StazionePulman findStazionePulmanById(Long id) {
		return this.pRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id stazione pulman non valido: " + id));
	}

	public List<Aereoporto> findAllAereoporti() {
		return this.aRepo.findAll();
	}

	public List<StazionePulman> findAllStazioniPulman() {
		return this.pRepo.findAll();
	}

	public Aereoporto saveAereoporto(AereoportoPayload ap) {
		Aereoporto a = new Aereoporto(ap.getNome(), ap.getLocalità(), ap.getSigla(), ap.getKmDistanza());
		return this.aRepo.save(a);
	}

	public StazionePulman saveStazionePulman(StazionePulmanPayload t) {
		StazionePulman sp = new StazionePulman(t.getNome(), t.getLocalità(), t.getSigla(), t.getNumeroStalli());
		return this.pRepo.save(sp);
	}

	public void deleteAereoporto(Long id) {
		if (id != null) {
			Aereoporto a = this.findAereoportoById(id);
			this.aRepo.delete(a);
		} else {
			throw new IllegalArgumentException("id aereoporto non valido: " + id);
		}
	}

	public void deleteStazionePulman(Long id) {
		if (id != null) {
			StazionePulman s = this.findStazionePulmanById(id);
			this.pRepo.delete(s);
		} else {
			throw new IllegalArgumentException("id stazione non valido: " + id);
		}
	}

	public Aereoporto updateAereoporto(AereoportoPayload ap) {
		if (ap.getId() != null) {
			Aereoporto a = this.findAereoportoById(ap.getId());
			a.setKmDiDistanza(ap.getKmDistanza());
			a.setLocalità(ap.getLocalità());
			a.setNome(ap.getNome());
			a.setSigla(ap.getSigla());
			return this.aRepo.save(a);
		} else {
			throw new IllegalArgumentException("id aereoporto non valido: " + ap.getId());
		}
	}

	public StazionePulman updateStazionePulman(StazionePulmanPayload sp) {
		if (sp.getId() != null) {
			StazionePulman s = this.findStazionePulmanById(sp.getId());
			s.setNumeroStalli(sp.getNumeroStalli());
			s.setLocalità(sp.getLocalità());
			s.setNome(sp.getNome());
			s.setSigla(sp.getSigla());
			return this.pRepo.save(s);
		} else {
			throw new IllegalArgumentException("id stazione non valido: " + sp.getId());
		}
	}

	public List<Aereoporto> findAereoportoByCittà(Long cittàId) {
		return this.aRepo.findByCittàId(cittàId);
	}

	public List<StazionePulman> findStazionePulmanByCittà(Long cittàId) {
		return this.pRepo.findByCittàId(cittàId);
	}
}
