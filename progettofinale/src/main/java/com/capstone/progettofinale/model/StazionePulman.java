package com.capstone.progettofinale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stazioni_pulman")
@NoArgsConstructor
@Setter
@Getter
public class StazionePulman extends Stazione {

	@Column(name = "num_stalli")
	private int numeroStalli;

	public StazionePulman(String nome, String località, String sigla, int numeroStalli) {
		super(nome, località, sigla);
		this.numeroStalli = numeroStalli;
	}

}
