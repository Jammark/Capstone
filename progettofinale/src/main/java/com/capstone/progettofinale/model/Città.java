package com.capstone.progettofinale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
//@OnDelete(action = OnDeleteAction.CASCADE)
public class Città extends MetaTuristica {

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "destinazione_id")
	private Destinazione destinazione;

	public Città(String nome, String descrizione, String urlImmagine, Destinazione destinazione) {
		super(nome, descrizione, urlImmagine);
		this.destinazione = destinazione;
	}

}
