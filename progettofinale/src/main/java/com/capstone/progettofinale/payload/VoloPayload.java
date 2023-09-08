package com.capstone.progettofinale.payload;

import java.util.Optional;

import com.capstone.progettofinale.model.Volo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoloPayload extends TrasportoPayload {

	private String compagnia;
	private Long partenzaId, arrivoId, stopId;

	public VoloPayload(Volo v) {
		super(v);
		this.compagnia = v.getCompagniaAerea();
		this.partenzaId = Optional.ofNullable(v.getPartenza()).orElse(null).getId();
		this.arrivoId = Optional.ofNullable(v.getArrivo()).orElse(null).getId();
		this.stopId = Optional.ofNullable(v.getStop()).orElse(null).getId();
	}

}
