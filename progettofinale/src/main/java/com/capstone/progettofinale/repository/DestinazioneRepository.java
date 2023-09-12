package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Destinazione;

@Repository
public interface DestinazioneRepository extends JpaRepository<Destinazione, Long> {


	@Query("SELECT DISTINCT(d), SUM(r.rate) sr FROM Destinazione d JOIN d.città c JOIN Alloggio a ON a.meta = c OR a.meta = d JOIN Rating r ON r.alloggio = a GROUP BY d ORDER BY sr DESC")
	public List<Destinazione> findMostRated();
}
