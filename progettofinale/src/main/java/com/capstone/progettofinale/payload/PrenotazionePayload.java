package com.capstone.progettofinale.payload;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazionePayload {

	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;

	private int numeroGiorni;

	private Long metaId;

	private Long userId;

	private Long alloggioId;

	private Long trasportoId;

	@JsonSerialize(using = PrezzoSerializer.class)
	private double prezzo;

	private int numeroPosti;
}
