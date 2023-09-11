package com.capstone.progettofinale.payload;

import java.util.Optional;

import com.capstone.progettofinale.model.Tratta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrattaPayload extends TrasportoPayload {

	private String nomeAzienda;
	private Long partenzaId, arrivoId;

	public TrattaPayload(Tratta t) {
		super(t);
		this.nomeAzienda = t.getAziendaDiTrasporto();
		this.partenzaId = Optional.ofNullable(t.getPartenza()).orElse(null).getId();
		this.arrivoId = Optional.ofNullable(t.getArrivo()).orElse(null).getId();
	}

}
