package com.capstone.progettofinale.payload;

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

}
