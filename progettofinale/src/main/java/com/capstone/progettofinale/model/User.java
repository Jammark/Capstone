package com.capstone.progettofinale.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "utenti")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "utente_id")
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	private String nome;
	private String cognome;
	@Column(name = "data_nascita")
	private LocalDate dataDiNascita;

	@Enumerated(EnumType.STRING)
	private UserRole ruolo;

	public User(String username, String password, String nome, String cognome, LocalDate dataDiNascita,
			UserRole ruolo) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.ruolo = ruolo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(ruolo.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
