package com.capstone.progettofinale.payload;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class TrasportoPayload {

	private Long id;

	private String nome;
	private String descrizione;

	@JsonFormat(pattern = "KK:mm")
	private LocalTime durata;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPartenza;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataArrivo;

	private int postiDisponibili;

	private int postiOccupati;

	private double prezzo;

}
