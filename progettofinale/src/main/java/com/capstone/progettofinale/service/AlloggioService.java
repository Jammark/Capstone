package com.capstone.progettofinale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.model.Alloggio;
import com.capstone.progettofinale.model.Appartamento;
import com.capstone.progettofinale.model.Hotel;
import com.capstone.progettofinale.payload.AppartamentoPayload;
import com.capstone.progettofinale.payload.HotelPayload;
import com.capstone.progettofinale.repository.AlloggioRepository;
import com.capstone.progettofinale.repository.AppartamentoRepository;
import com.capstone.progettofinale.repository.HotelRepository;

@Service
public class AlloggioService {

	@Autowired
	private HotelRepository hRepo;

	@Autowired
	private AppartamentoRepository aRepo;

	@Autowired
	private AlloggioRepository repo;

	@Autowired
	private MetaService mSrv;

	public Alloggio findById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id alloggio non valido: " + id));
	}

	public Hotel findHotelById(Long id) {
		return hRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id hotel non valido: " + id));
	}

	public Appartamento findAppartamentoById(Long id) {
		return aRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id appartamento non valido: " + id));
	}

	public List<Hotel> findAllHotel() {
		return hRepo.findAll();
	}

	public List<Appartamento> findAllAppartamenti() {
		return aRepo.findAll();
	}

	public void deleteHotel(Long id) {
		hRepo.delete(this.findHotelById(id));
	}

	public Hotel saveHotel(Hotel c) {
		return this.hRepo.save(c);
	}

	public Appartamento saveAppartamento(Appartamento d) {
		return this.aRepo.save(d);
	}

	public List<Hotel> findHotelByMeta(Long id) {
		return this.hRepo.findByMetaId(id);
	}

	public List<Appartamento> findAppartamentoByMeta(Long id) {
		return this.aRepo.findByMetaId(id);
	}

	public Hotel updateHotel(HotelPayload hp) {
		Hotel found = this.findHotelById(hp.getId());
		found.setDescrizione(hp.getDescrizione());
		found.setNome(hp.getNome());
		found.setStelle(hp.getNumStelle());
		found.setPrezzo(hp.getPrezzo());
		found.setUrlImmagine(hp.getUrlImmagine());
		if (hp.getMeta() != null) {
			found.setMeta(this.mSrv.findById(hp.getMeta().getId()));
		} else {
			throw new IllegalArgumentException("campo meta non valido nel body: " + hp);
		}
		return this.saveHotel(found);
	}

	public Appartamento updateAppartamento(AppartamentoPayload ap) {
		Appartamento found = this.findAppartamentoById(ap.getId());
		found.setDescrizione(ap.getDescrizione());
		found.setNome(ap.getNome());
		found.setCapienza(ap.getCapienza());
		found.setPrezzo(ap.getPrezzo());
		found.setUrlImmagine(ap.getUrlImmagine());
		if (ap.getMeta() != null) {
			found.setMeta(this.mSrv.findById(ap.getMeta().getId()));
		} else {
			throw new IllegalArgumentException("campo meta non valido nel body: " + ap);
		}
		return this.saveAppartamento(found);
	}

}
