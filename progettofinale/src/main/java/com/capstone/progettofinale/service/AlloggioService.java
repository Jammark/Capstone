package com.capstone.progettofinale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.common.IAuthenticationFacade;
import com.capstone.progettofinale.model.Alloggio;
import com.capstone.progettofinale.model.Appartamento;
import com.capstone.progettofinale.model.Hotel;
import com.capstone.progettofinale.payload.AppartamentoPayload;
import com.capstone.progettofinale.payload.HotelPayload;
import com.capstone.progettofinale.repository.AlloggioRepository;
import com.capstone.progettofinale.repository.AppartamentoRepository;
import com.capstone.progettofinale.repository.HotelRepository;

@Service
public class AlloggioService extends AbstractService {

	@Autowired
	private IAuthenticationFacade auth;

	@Autowired
	private HotelRepository hRepo;

	@Autowired
	private AppartamentoRepository aRepo;

	@Autowired
	private AlloggioRepository repo;

	@Autowired
	private MetaService mSrv;


	public AlloggioService() {
		super("alloggi");
	}

	private Long getUserId() {
		return this.auth.getUser().getId();
	}

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

	public void deleteAppartamento(Long id) {
		aRepo.delete(this.findAppartamentoById(id));
	}

	public Hotel saveHotel(HotelPayload hp) {
		Hotel h = new Hotel(hp.getNome(), hp.getDescrizione(), hp.getPrezzo(), mSrv.findById(hp.getMetaId()),
				hp.getNumStelle(), hp.getUrlImmagine(), hp.getNumSingole(), hp.getNumDoppie());
		if (h.getUrlImmagine() != null && !h.getUrlImmagine().isEmpty()) {
			String path = h.getNome() + ".jpg";
			storeImg(h.getUrlImmagine(), path);
			h.setUrlImmagine(path);
		}
		return this.hRepo.save(h);
	}


	public Appartamento saveAppartamento(AppartamentoPayload ap) {
		Appartamento a = new Appartamento(ap.getNome(), ap.getDescrizione(), ap.getPrezzo(),
				mSrv.findById(ap.getMetaId()), ap.getCapienza(), ap.getUrlImmagine());
		if (a.getUrlImmagine() != null && !a.getUrlImmagine().isEmpty()) {
			String path = a.getNome() + ".jpg";
			storeImg(a.getUrlImmagine(), path);
			a.setUrlImmagine(path);
		}
		return this.aRepo.save(a);
	}

	public List<Hotel> findHotelByMeta(Long id) {
		return this.hRepo.findByMetaId(id);
	}

	public List<Appartamento> findAppartamentoByMeta(Long id) {
		return this.aRepo.findByMetaId(id);
	}

	public Hotel updateHotel(HotelPayload hp) {
		Hotel found = this.findHotelById(hp.getId());
		found.setDescrizione(Optional.ofNullable(hp.getDescrizione()).orElse(found.getDescrizione()));
		found.setNome(Optional.ofNullable(hp.getNome()).orElse(found.getNome()));
		found.setStelle(Optional.ofNullable(hp.getNumStelle()).orElse(found.getStelle()));
		found.setPrezzo(Optional.ofNullable(hp.getPrezzo()).orElse(found.getPrezzo()));
		found.setUrlImmagine(Optional.ofNullable(hp.getUrlImmagine()).orElse(found.getUrlImmagine()));
		if (found.getUrlImmagine() != null && !found.getUrlImmagine().isEmpty()) {
			String path = found.getNome() + ".jpg";
			storeImg(found.getUrlImmagine(), path);
			found.setUrlImmagine(path);
		}
		if (hp.getMetaId() != null) {
			found.setMeta(this.mSrv.findById(hp.getMetaId()));
		}
		return this.hRepo.save(found);
	}

	public Appartamento updateAppartamento(AppartamentoPayload ap) {
		Appartamento found = this.findAppartamentoById(ap.getId());
		found.setDescrizione(Optional.ofNullable(ap.getDescrizione()).orElse(found.getDescrizione()));
		found.setNome(Optional.ofNullable(ap.getNome()).orElse(found.getNome()));
		found.setCapienza(Optional.ofNullable(ap.getCapienza()).orElse(found.getCapienza()));
		found.setPrezzo(Optional.ofNullable(ap.getPrezzo()).orElse(found.getPrezzo()));
		found.setUrlImmagine(Optional.ofNullable(ap.getUrlImmagine()).orElse(found.getUrlImmagine()));
		if (found.getUrlImmagine() != null && !found.getUrlImmagine().isEmpty()) {
			String path = found.getNome() + ".jpg";
			storeImg(found.getUrlImmagine(), path);
			found.setUrlImmagine(path);
		}
		if (ap.getMetaId() != null) {
			found.setMeta(this.mSrv.findById(ap.getMetaId()));
		}
		return this.aRepo.save(found);
	}

	public Alloggio getMostRated(Long metaId) {
		return this.repo.findMostRated(metaId).map(this::findById).orElse(null);
	}

}
