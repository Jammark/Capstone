package com.capstone.progettofinale.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Alloggio;

import jakarta.persistence.Tuple;

@Repository
public interface AlloggioRepository extends JpaRepository<Alloggio, Long> {

	@Query("SELECT a.id, SUM(r.rate) sr FROM Alloggio a JOIN Rating r ON r.alloggio = a WHERE a.meta.id = :param GROUP BY a.id ORDER BY sr DESC LIMIT 1")
	public Optional<Long> findMostRated(@Param("param") Long metaId);

	@Query(value = "SELECT d, h.numeroDoppie cd, h.numeroSingole cs FROM Hotel h JOIN Prenotazione p ON p.alloggio.id = h.id JOIN (SELECT GENERATE_SERIES(cast( :p as date), cast(:r as date), '1 day' ) AS d) ON d BETWEEN p.data AND DATEADD(DAY,p.numeroGiorni,p.data) WHERE h.id = :id AND ( p.data BETWEEN :p AND :r OR DATEADD(DAY,p.numeroGiorni,p.data) BETWEEN :p AND :r) GROUP BY d, cd, cs HAVING SUM(p.numeroPosti) > h.numeroDoppie * 2 + h.numeroSingole - :posti")
	public List<Tuple> findDisponibilitàHotel(@Param("p") LocalDate partenza, @Param("r") LocalDate ritorno,
			@Param("id") Long alloggioId, @Param("posti") int posti);

	@Query("SELECT a.id FROM Appartamento a JOIN Prenotazione p ON p.alloggio.id = a.id WHERE a.id = :id AND p IS NOT NULL AND (p.data BETWEEN :p AND :r OR DATEADD(DAY,p.numeroGiorni,p.data) BETWEEN :p AND :r) GROUP BY a.id")
	public Optional<Long> findDisponibilitàAppartamentol(@Param("p") LocalDate partenza,
			@Param("r") LocalDate ritorno,
			@Param("id") Long alloggioId);
}

//unnest(string_to_array(:array,';'))
//@Param("array") String array,
//join days on
//