package com.capstone.progettofinale.payload;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class UserRequestPayload {
	@NotNull(message = "campo nome obbligatorio.")
	private String name;
	@NotNull(message = "campo cognome obbligatorio.")
	private String surname;
	@Email(message = "valore campo email non valido.")
	@NotNull(message = "campo email obbligatorio.")
	private String email;
	@NotNull(message = "campo password obbligatorio.")
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDiNascita;
}