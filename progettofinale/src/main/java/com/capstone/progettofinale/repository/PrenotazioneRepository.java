package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

	public List<Prenotazione> findByUserId(Long id);

	@Query(value = "SELECT p FROM Acquisto a RIGHT JOIN a.prenotazione p WHERE a IS EMPTY AND p.user.id = ?1", nativeQuery = true)
	public List<Prenotazione> findNonPagate(Long userId);

}
