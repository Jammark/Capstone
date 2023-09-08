package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.StazionePulman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StazionePulmanPayload extends StazionePayload {

	private int numeroStalli;

	public StazionePulmanPayload(StazionePulman sp) {
		super(sp);
		this.numeroStalli = sp.getNumeroStalli();
	}

}
