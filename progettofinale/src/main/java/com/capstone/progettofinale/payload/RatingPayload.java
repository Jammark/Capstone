package com.capstone.progettofinale.payload;

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
	private Long id;
	private AlloggioPayload alloggio;
	private UserResponsePayload user;

}
