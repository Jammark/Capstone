package com.capstone.progettofinale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@NoArgsConstructor
@Setter
@Getter
public class Hotel extends Alloggio {

	private int stelle;

	public Hotel(String nome, String descrizione, double prezzo, MetaTuristica meta, int stelle, String urlImmagine) {
		super(nome, descrizione, prezzo, meta, urlImmagine);
		this.stelle = stelle;
	}

}
