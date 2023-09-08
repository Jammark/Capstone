package com.capstone.progettofinale.payload;

import java.time.LocalDate;

import com.capstone.progettofinale.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserResponsePayload {

	private Long id;

	private String username;
	private String nome;
	private String cognome;
	private LocalDate dataDiNascita;

	private String ruolo;

	public UserResponsePayload(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.nome = u.getNome();
		this.cognome = u.getCognome();
		this.dataDiNascita = u.getDataDiNascita();
	}
}
