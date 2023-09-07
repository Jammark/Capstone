package com.capstone.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Destinazione;

@Repository
public interface DestinazioneRepository extends JpaRepository<Destinazione, Long> {

}
