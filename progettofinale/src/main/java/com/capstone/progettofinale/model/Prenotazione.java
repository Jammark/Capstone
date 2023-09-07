package com.capstone.progettofinale.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prenotazioni")
@Setter
@Getter
@NoArgsConstructor
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prenotazione_id")
	private Long id;

	private LocalDate data;
	@Column(name = "numero_giorni")
	private int numeroGiorni;

	@ManyToOne
	@JoinColumn(name = "meta_id")
	private MetaTuristica meta;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "alloggio_id")
	private Alloggio alloggio;

	@ManyToOne
	@JoinColumn(name = "trasporto_id")
	private Trasporto trasporto;

	@Column(precision = 2)
	private double prezzo;

	@Column(name = "num_posti")
	private int numeroPosti;

	public Prenotazione(LocalDate data, int numeroGiorni, MetaTuristica meta, User user, Alloggio alloggio,
			Trasporto trasporto, int numeroPosti) {
		super();
		this.data = data;
		this.numeroGiorni = numeroGiorni;
		this.meta = meta;
		this.user = user;
		this.alloggio = alloggio;
		this.trasporto = trasporto;
		this.numeroPosti = numeroPosti;
	}

}
