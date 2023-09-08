package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.Città;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CittàPayload extends MetaPayload {

	private DestinazionePayload destinazione;

	public CittàPayload(Città c) {
		super(c);
		if (c.getDestinazione() != null) {
			this.destinazione = new DestinazionePayload(c.getDestinazione());
		}
	}

}
