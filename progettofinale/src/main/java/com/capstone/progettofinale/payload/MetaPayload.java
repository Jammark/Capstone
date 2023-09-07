package com.capstone.progettofinale.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class MetaPayload {

	private Long id;
	private String nome;
	private String descrizione;

}
