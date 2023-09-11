package com.capstone.progettofinale.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.common.IAuthenticationFacade;
import com.capstone.progettofinale.model.Alloggio;
import com.capstone.progettofinale.model.Rating;
import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.payload.AlloggioPayload;
import com.capstone.progettofinale.payload.RatingPayload;
import com.capstone.progettofinale.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private IAuthenticationFacade auth;

	@Autowired
	private RatingRepository repo;

	@Autowired
	private UserService uSrv;

	@Autowired
	private AlloggioService aSrv;

	private Long getUserId() {
		return this.auth.getUser().getId();
	}

	public Rating findById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new IllegalArgumentException("id rating non valido: " + id));
	}

	public List<Rating> findAll() {
		return this.repo.findAll();
	}

	public Rating save(RatingPayload rp) {
		if (rp.getAlloggioId() != null) {
			User u = uSrv.findById(this.getUserId());
			Alloggio a = aSrv.findById(rp.getAlloggioId());
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

	public List<Rating> findByAlloggi(List<Long> alloggiIds, long userId) {
		return this.repo.findByUserIdAndAlloggioIdIn(userId,
				Set.of(alloggiIds.stream().toArray(Long[]::new)));
	}

	public void setRatings(List<? extends AlloggioPayload> alloggi) {
		List<Rating> ratings = this.findByAlloggi(alloggi.stream().map(AlloggioPayload::getId).toList(), getUserId());
		alloggi.forEach(a -> {
			List<Integer> lista = ratings.stream().filter(r -> r.getAlloggio().getId().equals(a.getId()))
					.map(Rating::getRate).toList();

			if (!lista.isEmpty()) {
				a.setRate(Math.floorDiv(lista.stream().reduce(0, (sum, val) -> sum + val), lista.size()));
			}
		});
	}

}
