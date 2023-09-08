package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.Stazione;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class StazionePayload {

	private Long id;

	private String nome;
	private String località;
	private String sigla;

	public StazionePayload(Stazione s) {
		this.id = s.getId();
		this.nome = s.getNome();
		this.località = s.getLocalità();
		this.sigla = s.getSigla();
	}

}
