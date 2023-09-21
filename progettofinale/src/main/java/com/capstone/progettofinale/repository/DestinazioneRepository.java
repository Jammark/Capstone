package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Destinazione;

import jakarta.persistence.Tuple;

@Repository
public interface DestinazioneRepository extends JpaRepository<Destinazione, Long> {


	@Query(value = "SELECT d.id, SUM(r.rate) AS sr FROM Destinazione d JOIN d.città c JOIN Alloggio a ON a.meta = c OR a.meta = d JOIN Rating r ON r.alloggio = a WHERE r IS NOT NULL GROUP BY d.id ORDER BY sr DESC")
	public List<Tuple> findMostRated();
}
