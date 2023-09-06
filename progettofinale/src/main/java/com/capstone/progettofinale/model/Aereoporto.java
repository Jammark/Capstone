package com.capstone.progettofinale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aereoporti")
@NoArgsConstructor
@Setter
@Getter
public class Aereoporto extends Stazione {

	@Column(name = "km_distanza")
	private int kmDiDistanza;

	public Aereoporto(String nome, String località, String sigla, int kmDiDistanza) {
		super(nome, località, sigla);
		this.kmDiDistanza = kmDiDistanza;
	}

}
