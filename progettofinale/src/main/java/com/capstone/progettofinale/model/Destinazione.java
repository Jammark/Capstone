package com.capstone.progettofinale.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destinazioni")
@Setter
@Getter
@NoArgsConstructor
public class Destinazione extends MetaTuristica {

	@Column(name = "contenuto_principale", length = 400)
	private String contenutoPrincipale;
	@Column(name = "contenuto_secondario", length = 400)
	private String contenutoSecondario;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "destinazione", cascade = CascadeType.PERSIST)
	private Set<Città> città;

	public Destinazione(String nome, String descrizione, String urlImmagine, String contenutoPrincipale,
			String contenutoSecondario) {
		super(nome, descrizione, urlImmagine);
		this.contenutoPrincipale = contenutoPrincipale;
		this.contenutoSecondario = contenutoSecondario;
	}

	public Set<Città> getCittà() {
		return this.città != null ? this.città : new HashSet<>();
	}
}
