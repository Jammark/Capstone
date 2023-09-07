package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Appartamento;

@Repository
public interface AppartamentoRepository extends JpaRepository<Appartamento, Long> {

	public List<Appartamento> findByMetaId(Long id);
}
