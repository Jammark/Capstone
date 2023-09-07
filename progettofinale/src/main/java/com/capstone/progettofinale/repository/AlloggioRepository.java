package com.capstone.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Alloggio;

@Repository
public interface AlloggioRepository extends JpaRepository<Alloggio, Long> {

}
