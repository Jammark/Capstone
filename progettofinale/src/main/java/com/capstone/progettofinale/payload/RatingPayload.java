package com.capstone.progettofinale.payload;

import java.util.Optional;

import com.capstone.progettofinale.model.Alloggio;
import com.capstone.progettofinale.model.Rating;
import com.capstone.progettofinale.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingPayload {

	private int rate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long id;
	private Long alloggioId;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long userId;

	public RatingPayload(Rating r) {
		this.id = r.getId();
		this.rate = r.getRate();
		this.alloggioId = Optional.of(r.getAlloggio()).map(Alloggio::getId).orElse(null);
		this.userId = Optional.of(r.getUser()).map(User::getId).orElse(null);
	}

}
