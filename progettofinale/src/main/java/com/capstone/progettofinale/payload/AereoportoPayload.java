package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.Aereoporto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AereoportoPayload extends StazionePayload {

	private int kmDistanza;

	public AereoportoPayload(Aereoporto a) {
		super(a);
		this.kmDistanza = a.getKmDiDistanza();
	}
}
