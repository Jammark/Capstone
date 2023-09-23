package com.capstone.progettofinale.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Volo;

import jakarta.persistence.Tuple;

@Repository
public interface VoloRepository extends JpaRepository<Volo, Long> {

	public List<Volo> findByPartenzaCittàNomeAndArrivoCittàNomeAndDataPartenza(String partenza, String arrivo,
			LocalDateTime data);

	@Query("SELECT Distinct(v.id) FROM Volo v JOIN Aereoporto a ON v.arrivo = a JOIN a.città c JOIN c.destinazione d JOIN Prenotazione p ON p.trasporto.id = v.id OR p.ritorno.id = v.id WHERE (c.id = :param OR d.id = :param) AND v.dataPartenza BETWEEN :data AND :end GROUP BY v.id HAVING SUM(p.numeroPosti) < v.postiDisponibili - :posti")
	public List<Long> findVoliDisponibili(@Param("param") Long metaId, @Param("data") LocalDateTime data,
			@Param("end") LocalDateTime endData, @Param("posti") int posti);

	@Query("SELECT v.id FROM Volo v JOIN Prenotazione p ON p.trasporto.id = v.id OR p.ritorno.id = v.id WHERE v.partenza.id = :partenza AND v.arrivo.id = :arrivo AND v.dataPartenza >= :data GROUP BY v.id HAVING SUM(p.numeroPosti) < v.postiDisponibili - :posti ORDER BY v.dataPartenza ASC LIMIT 1")
	public Optional<Long> findVolorRitorno(@Param("partenza") Long pId, @Param("arrivo") Long aId,
			@Param("data") LocalDateTime data, @Param("posti") int posti);

	@Query("SELECT v.id, coalesce(SUM(p.numeroPosti), 0) AS sum FROM Volo v LEFT JOIN Prenotazione p ON p.trasporto.id = v.id OR p.ritorno.id = v.id WHERE v.id = :voloId GROUP BY v.id")
	public Tuple findPostiRimanenti(@Param("voloId") Long voloId);

}
