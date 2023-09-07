package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Aereoporto;

@Repository
public interface AereoportoRepository extends JpaRepository<Aereoporto, Long> {
	public List<Aereoporto> findByCittàId(Long id);
}
