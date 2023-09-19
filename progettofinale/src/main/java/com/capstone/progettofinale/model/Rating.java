package com.capstone.progettofinale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ratings", uniqueConstraints = @UniqueConstraint(columnNames = { "utente_id", "alloggio_id" }))
@NoArgsConstructor
@Setter
@Getter
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "alloggio_id")
	private Alloggio alloggio;

	@Column(nullable = false)
	private int rate;

	public Rating(User user, Alloggio alloggio, int rate) {
		super();
		this.user = user;
		this.alloggio = alloggio;
		this.rate = rate;
	}

}
