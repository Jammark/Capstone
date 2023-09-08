package com.capstone.progettofinale.payload;

import com.capstone.progettofinale.model.Appartamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppartamentoPayload extends AlloggioPayload {

	private int capienza;

	public AppartamentoPayload(Appartamento a) {
		super(a);
		this.capienza = a.getCapienza();
	}

}
