package com.capstone.progettofinale.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestPayload {
	private String name;
	private String surname;
	private String email;
	private String password;
	private LocalDate data;
}