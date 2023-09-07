package com.capstone.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Acquisto;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {


}
