package com.capstone.progettofinale.payload;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AcquistoPayload {

	private Long id;
	@JsonFormat(pattern = "yyy-MM-dd")
	private LocalDate data;
	private Long userId;
	private Long prenotazioneId;
	@JsonSerialize(using = PrezzoSerializer.class)
	private double prezzo;

}
