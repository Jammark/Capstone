package com.capstone.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.MetaTuristica;

@Repository
public interface MetaRepository extends JpaRepository<MetaTuristica, Long> {

}
