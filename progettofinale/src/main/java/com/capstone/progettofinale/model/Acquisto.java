package com.capstone.progettofinale.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acquisti", uniqueConstraints = { @UniqueConstraint(columnNames = { "prenotazione_id", "utente_id" }) })
@NoArgsConstructor
@Getter
@Setter

public class Acquisto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stazione_id")
	private Long id;

	@Column(nullable = false)
	private double prezzo;

	private LocalDate data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prenotazione_id")
	private Prenotazione prenotazione;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private User utente;

	public Acquisto(double prezzo, LocalDate data, Prenotazione prenotazione, User utente) {
		super();
		this.prezzo = prezzo;
		this.data = data;
		this.prenotazione = prenotazione;
		this.utente = utente;
	}

}
