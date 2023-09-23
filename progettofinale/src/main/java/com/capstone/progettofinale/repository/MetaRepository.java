package com.capstone.progettofinale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.MetaTuristica;

@Repository
public interface MetaRepository extends JpaRepository<MetaTuristica, Long> {

	public Optional<MetaTuristica> findByNome(String nome);

	@Query("SELECT COUNT(*) FROM Hotel h WHERE h.meta.id = :param")
	public Long findNumHotel(@Param("param") Long metaId);


}
