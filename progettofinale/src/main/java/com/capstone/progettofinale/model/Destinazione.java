package com.capstone.progettofinale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destinazioni")
@Setter
@Getter
@NoArgsConstructor
public class Destinazione extends MetaTuristica {

	@Column(name = "contenuto_principale")
	private String contenutoPrincipale;
	@Column(name = "contenuto_secondario")
	private String contenutoSecondario;

	public Destinazione(String nome, String descrizione, String contenutoPrincipale, String contenutoSecondario) {
		super(nome, descrizione);
		this.contenutoPrincipale = contenutoPrincipale;
		this.contenutoSecondario = contenutoSecondario;
	}


}
