package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.Alloggio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlloggioPayload {

	private Long id;
	private String nome, descrizione;
	private Long metaId;
	private Double prezzo;
	private String urlImmagine;
	private Integer rate;

	public AlloggioPayload(Alloggio a) {
		this.id = a.getId();
		this.nome = a.getNome();
		this.descrizione = a.getDescrizione();
		if (a.getMeta() != null) {
			this.metaId = a.getMeta().getId();
		}
		this.prezzo = a.getPrezzo();
		this.urlImmagine = a.getUrlImmagine();
	}

}
