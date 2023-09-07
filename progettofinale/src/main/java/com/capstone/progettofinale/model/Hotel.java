package com.capstone.progettofinale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@NoArgsConstructor
@Setter
@Getter
public class Hotel extends Alloggio {

	private int stelle;
	@Column(name = "numero_singole")
	private int numeroSingole;
	@Column(name = "numero_doppie")
	private int numeroDoppie;
	@Column(name = "num_singole_occupate")
	private int numeroSingoleOccupate;
	@Column(name = "num_doppie_occupate")
	private int numeroDoppieOccupate;

	public Hotel(String nome, String descrizione, double prezzo, MetaTuristica meta, int stelle, String urlImmagine,
			int numeroSingole, int numeroDoppie) {
		super(nome, descrizione, prezzo, meta, urlImmagine);
		this.stelle = stelle;
		this.numeroDoppie = numeroDoppie;
		this.numeroSingole = numeroSingole;
	}

	public boolean isDisponibile(int numPosti) {
		return (numeroDoppie - numeroDoppieOccupate) * 2 + numeroSingole - numeroSingoleOccupate > numPosti;
	}

	public boolean occupaPosti(int numPosti) {
		switch (numPosti % 2) {
		case 1:
			numeroDoppieOccupate += (numPosti - 1) / 2;
			numeroSingoleOccupate++;
			break;
		case 0:
			numeroDoppieOccupate += numPosti / 2;
		}
		if (numeroDoppieOccupate > numeroDoppie || numeroSingoleOccupate > numeroSingole) {
			return false;
		} else {
			return true;
		}
	}

}
