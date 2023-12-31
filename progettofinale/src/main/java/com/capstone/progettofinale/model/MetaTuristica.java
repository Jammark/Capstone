package com.capstone.progettofinale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "meta", uniqueConstraints = { @UniqueConstraint(columnNames = { "nome", "url_immagine" }) })
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class MetaTuristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meta_id")
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;
	@Column(length = 600)
	private String descrizione;
	@Column(name = "url_immagine", nullable = false)
	private String urlImmagine;


	public MetaTuristica(String nome, String descrizione, String urlImmagine) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.urlImmagine = urlImmagine;
	}

}
