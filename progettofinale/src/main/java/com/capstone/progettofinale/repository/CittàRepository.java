package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Città;

@Repository
public interface CittàRepository extends JpaRepository<Città, Long> {

	public List<Città> findByDestinazioneId(Long id);

}
