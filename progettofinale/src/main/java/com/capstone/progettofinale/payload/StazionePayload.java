package com.capstone.progettofinale.payload;

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

}
