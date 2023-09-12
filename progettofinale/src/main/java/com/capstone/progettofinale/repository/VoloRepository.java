package com.capstone.progettofinale.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Volo;

@Repository
public interface VoloRepository extends JpaRepository<Volo, Long> {

	public List<Volo> findByPartenzaCittàNomeAndArrivoCittàNomeAndDataPartenza(String partenza, String arrivo,
			LocalDate data);

	@Query("SELECT v FROM Volo v JOIN Aereoporto a ON v.arrivo = a JOIN a.città c JOIN c.destinazione d WHERE (c.id = :param OR d.id = :param) AND v.dataPartenza BETWEEN :data AND :end")
	public List<Volo> findVoliDisponibili(@Param("param") Long metaId, @Param("data") LocalDateTime data,
			@Param("end") LocalDateTime endData);

	@Query("SELECT v FROM Volo v WHERE v.partenza.id = :partenza AND v.arrivo.id = :arrivo AND v.dataPartenza >= :data ORDER BY v.dataPartenza ASC LIMIT 1")
	public Optional<Volo> findVolorRitorno(@Param("partenza") Long pId, @Param("arrivo") Long aId,
			@Param("data") LocalDateTime data);

}
