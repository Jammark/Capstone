package com.capstone.progettofinale.service;

import java.util.List;
import java.util.Optional;

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

	@Autowired
	private MetaService mSrv;

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
		if (ap.getCityId() != null) {
			a.setCittà(mSrv.findCittàById(ap.getCityId()));
		}
		return this.aRepo.save(a);
	}

	public StazionePulman saveStazionePulman(StazionePulmanPayload t) {
		StazionePulman sp = new StazionePulman(t.getNome(), t.getLocalità(), t.getSigla(), t.getNumeroStalli());
		if (t.getCityId() != null) {
			sp.setCittà(mSrv.findCittàById(t.getCityId()));
		}
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
			a.setKmDiDistanza(Optional.ofNullable(ap.getKmDistanza()).orElse(a.getKmDiDistanza()));
			a.setLocalità(Optional.ofNullable(ap.getLocalità()).orElse(a.getLocalità()));
			a.setNome(Optional.ofNullable(ap.getNome()).orElse(a.getNome()));
			a.setSigla(Optional.ofNullable(ap.getSigla()).orElse(a.getSigla()));
			a.setCittà(Optional.ofNullable(ap.getCityId()).map(mSrv::findCittàById).orElse(a.getCittà()));
			return this.aRepo.save(a);
		} else {
			throw new IllegalArgumentException("id aereoporto non valido: " + ap.getId());
		}
	}

	public StazionePulman updateStazionePulman(StazionePulmanPayload sp) {
		if (sp.getId() != null) {
			StazionePulman s = this.findStazionePulmanById(sp.getId());
			s.setNumeroStalli(Optional.ofNullable(sp.getNumeroStalli()).orElse(s.getNumeroStalli()));
			s.setLocalità(Optional.ofNullable(sp.getLocalità()).orElse(s.getLocalità()));
			s.setNome(Optional.ofNullable(sp.getNome()).orElse(s.getNome()));
			s.setSigla(Optional.ofNullable(sp.getSigla()).orElse(s.getSigla()));
			s.setCittà(Optional.ofNullable(sp.getCityId()).map(mSrv::findCittàById).orElse(s.getCittà()));
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
