package com.capstone.progettofinale.model;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "voli")
@Setter
@Getter
@NoArgsConstructor

public class Volo extends Trasporto {

	@Column(name = "compagnia")
	private String compagniaAerea;

	@ManyToOne
	@JoinColumn(name = "partenza_id", nullable = false)
	private Aereoporto partenza;
	@ManyToOne
	@JoinColumn(name = "arrivo_id", nullable = false)
	private Aereoporto arrivo;
	@ManyToOne
	@JoinColumn(name = "stop_id")
	private Aereoporto stop;

	public Volo(String nome, String descrizione, Duration durata, LocalDateTime dataPartenza, LocalDateTime dataArrivo,
			int postiDisponibili, int postiOccupati, double prezzo, String compagniaAerea, Aereoporto partenza,
			Aereoporto arrivo, Aereoporto stop) {
		super(nome, descrizione, durata, dataPartenza, dataArrivo, postiDisponibili, postiOccupati, prezzo);
		this.compagniaAerea = compagniaAerea;
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.stop = stop;
	}

}
