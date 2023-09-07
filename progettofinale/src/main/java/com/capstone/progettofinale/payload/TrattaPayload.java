package com.capstone.progettofinale.payload;

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

}
