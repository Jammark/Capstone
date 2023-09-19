package com.capstone.progettofinale.payload;

import java.time.LocalDate;
import java.util.Optional;

import com.capstone.progettofinale.model.Acquisto;
import com.capstone.progettofinale.model.Prenotazione;
import com.capstone.progettofinale.model.User;
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
	private PrenotazionePayload prenotazione;

	public AcquistoPayload(Acquisto a) {
		this.id = a.getId();
		this.data = a.getData();
		this.userId = Optional.ofNullable(a.getUtente()).map(User::getId).orElse(null);
		this.prenotazioneId = Optional.ofNullable(a.getPrenotazione()).map(Prenotazione::getId).orElse(null);
		this.prezzo = a.getPrezzo();
		this.prenotazione = Optional.ofNullable(a.getPrenotazione()).map(PrenotazionePayload::new).orElse(null);

	}

}
