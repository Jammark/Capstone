package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.MetaTuristica;

import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private String nome;
	@NotNull
	private String descrizione;
	private String imgUrl;
	private Long rating;
	private Integer numHotels;

	public MetaPayload(MetaTuristica m) {
		this.id = m.getId();
		this.nome = m.getNome();
		this.descrizione = m.getDescrizione();
		this.imgUrl = m.getUrlImmagine();

	}

}
