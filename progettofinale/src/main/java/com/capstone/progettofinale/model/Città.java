package com.capstone.progettofinale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "città")
@Setter
@Getter
@NoArgsConstructor
public class Città extends MetaTuristica {

	@ManyToOne
	@JoinColumn(name = "destinazione_id")
	private Destinazione destinazione;

	public Città(String nome, String descrizione, Destinazione destinazione) {
		super(nome, descrizione);
		this.destinazione = destinazione;
	}

}
