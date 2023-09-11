package com.capstone.progettofinale.payload;

import java.time.LocalDate;
import java.util.Optional;

import com.capstone.progettofinale.model.Alloggio;
import com.capstone.progettofinale.model.MetaTuristica;
import com.capstone.progettofinale.model.Prenotazione;
import com.capstone.progettofinale.model.Trasporto;
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
public class PrenotazionePayload {

	private Long id;

	@JsonFormat(pattern = "yyyy-M-dd")
	private LocalDate data;

	private int numeroGiorni;

	private Long metaId;

	private Long userId;

	private Long alloggioId;

	private Long trasportoId;

	@JsonSerialize(using = PrezzoSerializer.class)
	private double prezzo;

	private int numeroPosti;

	public PrenotazionePayload(Prenotazione p) {
		this.id = p.getId();
		this.data = p.getData();
		this.numeroGiorni = p.getNumeroGiorni();
		this.metaId = Optional.ofNullable(p.getMeta()).map(MetaTuristica::getId).orElse(null);
		this.userId = Optional.ofNullable(p.getUser()).map(User::getId).orElse(null);
		this.alloggioId = Optional.ofNullable(p.getAlloggio()).map(Alloggio::getId).orElse(null);
		this.trasportoId = Optional.ofNullable(p.getTrasporto()).map(Trasporto::getId).orElse(null);
		this.prezzo = p.getPrezzo();
	}
}
