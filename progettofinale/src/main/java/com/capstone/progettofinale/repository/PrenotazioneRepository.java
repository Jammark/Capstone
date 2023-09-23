package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

	public List<Prenotazione> findByUserId(Long id);

	@Query(value = "SELECT p FROM Prenotazione p LEFT JOIN Acquisto a ON a.prenotazione = p WHERE p.user.id = :param AND a is NULL", nativeQuery = false)
	public List<Prenotazione> findNonPagate(@Param("param") Long userId);



}
