package com.capstone.progettofinale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Città;

import jakarta.persistence.Tuple;

@Repository
public interface CittàRepository extends JpaRepository<Città, Long> {

	public List<Città> findByDestinazioneId(Long id);

	public Optional<Città> findByNome(String nome);

	@Query("SELECT c.id, SUM(r.rate) sr  FROM Città c JOIN Alloggio a ON a.meta = c JOIN Rating r ON r.alloggio = a GROUP BY c.id ORDER BY sr DESC")
	public List<Tuple> findMostRated();
}
