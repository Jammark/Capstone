package com.capstone.progettofinale.payload;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginSuccessfullPayload {
	String accessToken;

	private UserResponsePayload user;
}

