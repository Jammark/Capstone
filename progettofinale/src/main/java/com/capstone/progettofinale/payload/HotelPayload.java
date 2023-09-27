package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelPayload extends AlloggioPayload {

	private Integer numStelle;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer numSingole;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer numDoppie;

	public HotelPayload(Hotel h) {
		super(h);
		this.numStelle = h.getStelle();
	}

}
