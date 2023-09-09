package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Acquisto;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {

	public List<Acquisto> findByUtenteId(Long id);
}
