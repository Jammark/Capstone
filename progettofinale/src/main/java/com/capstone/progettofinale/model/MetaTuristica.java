package com.capstone.progettofinale.model;

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
@Table(name = "meta")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MetaTuristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meta_id")
	private Long id;

	private String nome;
	private String descrizione;
	@Column(name = "url_immagine")
	private String urlImmagine;

	public MetaTuristica(String nome, String descrizione, String urlImmagine) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.urlImmagine = urlImmagine;
	}

}
