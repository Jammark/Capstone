package com.capstone.progettofinale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.model.Alloggio;
import com.capstone.progettofinale.model.Rating;
import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.payload.RatingPayload;
import com.capstone.progettofinale.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository repo;

	@Autowired
	private UserService uSrv;

	@Autowired
	private AlloggioService aSrv;

	public Rating findById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new IllegalArgumentException("id rating non valido: " + id));
	}

	public List<Rating> findAll() {
		return this.repo.findAll();
	}

	public Rating save(RatingPayload rp) {
		if (rp.getUser() != null && rp.getUser().getId() != null && rp.getAlloggio() != null
				&& rp.getAlloggio().getId() != null) {
			User u = uSrv.findById(rp.getUser().getId());
			Alloggio a = aSrv.findById(rp.getAlloggio().getId());
			Rating r = new Rating(u, a, this.checkRate(rp.getRate()));
			return this.repo.save(r);
		} else {
			throw new IllegalArgumentException("utente o allogio non specificati nel body: " + rp);
		}

	}

	public void delete(Rating r) {
		this.repo.delete(r);
	}

	public Rating update(RatingPayload rp) {
		if (rp.getId() != null) {
			Rating found = this.findById(rp.getId());
			found.setRate(this.checkRate(rp.getRate()));
			return this.repo.save(found);
		} else {
			throw new IllegalArgumentException("id rating non valido: " + rp.getId());
		}

	}

	private int checkRate(int rate) {
		if (rate >= 0 && rate <= 5)
			return rate;
		else
			throw new IllegalArgumentException("valore rate non valido min 0 max 5; val: " + rate);
	}

}
