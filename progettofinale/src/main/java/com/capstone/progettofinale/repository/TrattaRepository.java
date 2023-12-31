package com.capstone.progettofinale.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Tratta;

@Repository
public interface TrattaRepository extends JpaRepository<Tratta, Long> {

	public List<Tratta> findByPartenzaCittàNomeAndArrivoCittàNomeAndDataPartenza(String partenza, String arrivo,
			LocalDate data);

}
