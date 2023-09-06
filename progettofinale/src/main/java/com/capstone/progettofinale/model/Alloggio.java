package com.capstone.progettofinale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alloggi")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Alloggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alloggio_id")
	private Long id;

	private String nome;
	private String descrizione;

	private double prezzo;
	@Column(name = "url_immagine")
	private String urlImmagine;

	@ManyToOne
	@JoinColumn(name = "meta_id")
	private MetaTuristica meta;

	public Alloggio(String nome, String descrizione, double prezzo, MetaTuristica meta, String urlImmagine) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.meta = meta;
		this.urlImmagine = urlImmagine;
	}

}
