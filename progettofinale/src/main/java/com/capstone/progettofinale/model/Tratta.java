package com.capstone.progettofinale.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tratte")
public class Tratta extends Trasporto {

	@Column(name = "nome_azienda")
	private String aziendaDiTrasporto;

	@ManyToOne
	@JoinColumn(name = "partenza_id")
	private StazionePulman partenza;
	@ManyToOne
	@JoinColumn(name = "arrivo_id")
	private StazionePulman arrivo;

	public Tratta(String nome, String descrizione, LocalTime durata, LocalDate dataPartenza, LocalDate dataArrivo,
			int postiDisponibili, int postiOccupati, double prezzo, String aziendaDiTrasporto, StazionePulman partenza,
			StazionePulman arrivo) {
		super(nome, descrizione, durata, dataPartenza, dataArrivo, postiDisponibili, postiOccupati, prezzo);
		this.aziendaDiTrasporto = aziendaDiTrasporto;
		this.partenza = partenza;
		this.arrivo = arrivo;
	}

}
