package com.capstone.progettofinale.payload;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginPayload {
	@Email(message = "valore campo email non valido.")
	private String email;
	private String password;
}