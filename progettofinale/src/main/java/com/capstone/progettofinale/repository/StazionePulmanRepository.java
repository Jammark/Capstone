package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.StazionePulman;

@Repository
public interface StazionePulmanRepository extends JpaRepository<StazionePulman, Long> {

	public List<StazionePulman> findByCittàId(Long id);
}
