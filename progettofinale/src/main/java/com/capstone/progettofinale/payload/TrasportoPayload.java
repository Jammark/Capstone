package com.capstone.progettofinale.payload;

import java.time.LocalDate;
import java.time.LocalTime;

import com.capstone.progettofinale.model.Trasporto;
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

	public TrasportoPayload(Trasporto t) {
		this.id = t.getId();
		this.nome = t.getNome();
		this.descrizione = t.getDescrizione();
		this.durata = t.getDurata();
		this.dataPartenza = t.getDataPartenza();
		this.dataArrivo = t.getDataArrivo();
		this.postiDisponibili = t.getPostiDisponibili();
		this.postiOccupati = t.getPostiOccupati();
		this.prezzo = t.getPrezzo();
	}

}
