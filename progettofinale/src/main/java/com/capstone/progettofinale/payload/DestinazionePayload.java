package com.capstone.progettofinale.payload;

import java.util.Set;

import com.capstone.progettofinale.model.Città;
import com.capstone.progettofinale.model.Destinazione;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinazionePayload extends MetaPayload {

	@NotNull
	private String contenutoPrincipale, contenutoSecondario;

	private Set<Long> cityIds;

	public DestinazionePayload(Destinazione d) {
		super(d);
		this.contenutoPrincipale = d.getContenutoPrincipale();
		this.contenutoSecondario = d.getContenutoSecondario();
		this.cityIds = Set.of(d.getCittà().stream().map(Città::getId).toArray(Long[]::new));
	}

}
