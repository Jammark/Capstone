package com.capstone.progettofinale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.MetaTuristica;

@Repository
public interface MetaRepository extends JpaRepository<MetaTuristica, Long> {

	public Optional<MetaTuristica> findByNome(String nome);

}
