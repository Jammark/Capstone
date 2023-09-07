package com.capstone.progettofinale.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlloggioPayload {

	private Long id;
	private String nome, descrizione;
	private MetaPayload meta;
	private double prezzo;
	private String urlImmagine;

}
