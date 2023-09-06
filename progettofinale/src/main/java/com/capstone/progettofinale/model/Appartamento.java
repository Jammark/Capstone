package com.capstone.progettofinale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appartamenti")
@NoArgsConstructor
@Setter
@Getter
public class Appartamento extends Alloggio {

	private int capienza;

	public Appartamento(String nome, String descrizione, double prezzo, MetaTuristica meta, int capienza,
			String urlImmagine) {
		super(nome, descrizione, prezzo, meta, urlImmagine);
		this.capienza = capienza;
	}

}
