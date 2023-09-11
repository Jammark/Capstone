package com.capstone.progettofinale.payload;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.lang.Nullable;

import com.capstone.progettofinale.model.Trasporto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class TrasportoPayload {

	private Long id;

	private String nome;
	private String descrizione;
//	@DateTimeFormat(pattern = "hh:mm", iso = ISO.TIME)
	@JsonFormat(pattern = "H:mm", shape = JsonFormat.Shape.STRING)
//	@Schema(description = "My time, Europe/Madrid zone", example = "18:07:22", required = false, type = "string", format = "time")
//	@JsonDeserialize(using = LocalTimeJsonSerializer.class)
	@Nullable
	private LocalTime durata;

	@JsonFormat(pattern = "yyyy-M-dd")
	private LocalDate dataPartenza;

	@JsonFormat(pattern = "yyyy-M-dd")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private LocalDate dataArrivo;

	private int postiDisponibili;

	private int postiOccupati;

	private double prezzo;

	public TrasportoPayload(Trasporto t) {
		this.id = t.getId();
		this.nome = t.getNome();
		this.descrizione = t.getDescrizione();
		this.durata = t.getDurata();
		this.dataPartenza = t.getDataPartenza();
		this.dataArrivo = t.getDataArrivo();
		this.postiDisponibili = t.getPostiDisponibili();
		this.postiOccupati = t.getPostiOccupati();
		this.prezzo = t.getPrezzo();
	}

}
