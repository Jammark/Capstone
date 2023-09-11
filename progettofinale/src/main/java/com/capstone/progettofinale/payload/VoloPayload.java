package com.capstone.progettofinale.payload;

import java.util.Optional;

import com.capstone.progettofinale.model.Aereoporto;
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
		this.partenzaId = Optional.ofNullable(v.getPartenza()).map(Aereoporto::getId).orElse(null);
		this.arrivoId = Optional.ofNullable(v.getArrivo()).map(Aereoporto::getId).orElse(null);
		this.stopId = Optional.ofNullable(v.getStop()).map(Aereoporto::getId).orElse(null);
	}

}
