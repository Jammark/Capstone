package com.capstone.progettofinale.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Volo;

@Repository
public interface VoloRepository extends JpaRepository<Volo, Long> {

	public List<Volo> findByPartenzaCittàNomeAndArrivoCittàNomeAndDataPartenza(String partenza, String arrivo,
			LocalDate data);

}
