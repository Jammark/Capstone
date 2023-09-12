package com.capstone.progettofinale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Alloggio;

@Repository
public interface AlloggioRepository extends JpaRepository<Alloggio, Long> {

	@Query("SELECT a.id, SUM(r.rate) sr FROM Alloggio a JOIN Rating r ON r.alloggio = a WHERE a.meta.id = :param GROUP BY a.id ORDER BY sr DESC LIMIT 1")
	public Optional<Long> findMostRated(@Param("param") Long metaId);

}
