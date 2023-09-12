package com.capstone.progettofinale.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trasporti")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Trasporto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trasporto_id")
	private Long id;

	private String nome;
	private String descrizione;

	@Column(columnDefinition = "TIME")
	private LocalTime durata;

	@Column(name = "data_partenza")
	private LocalDateTime dataPartenza;

	@Column(name = "data_arrivo")
	private LocalDateTime dataArrivo;

	@Column(name = "posti_disponibili")
	private int postiDisponibili;

	@Column(name = "posti_occupati")
	private int postiOccupati;

	@Column(precision = 2)
	private double prezzo;

	public Trasporto(String nome, String descrizione, Duration durata, LocalDateTime dataPartenza,
			LocalDateTime dataArrivo,
			int postiDisponibili, int postiOccupati, double prezzo) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.updateDurata(durata);
		;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.postiDisponibili = postiDisponibili;
		this.postiOccupati = postiOccupati;
		this.prezzo = prezzo;
	}

	public void updateDurata(Duration durata) {
		this.durata = LocalTime.of((int) durata.toHours(), (int) (durata.toMinutes() % 60));
	}

}
