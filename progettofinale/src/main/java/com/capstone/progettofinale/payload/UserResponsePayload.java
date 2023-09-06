package com.capstone.progettofinale.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserResponsePayload {

	private String username;
	private String nome;
	private String cognome;
	private LocalDate dataDiNascita;

	private String ruolo;
}
