package com.capstone.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Trasporto;

@Repository
public interface TrasportoRepository extends JpaRepository<Trasporto, Long> {

}
