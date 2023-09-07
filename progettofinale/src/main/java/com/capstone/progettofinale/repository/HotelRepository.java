package com.capstone.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

	public List<Hotel> findByMetaId(Long id);

}
