package com.capstone.progettofinale.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // mi permette di dichiarare su ogni singolo endpoint i permessi di accesso in
						// base al ruolo dell'utente (preAuthorize annotation)
public class SecurityConfig {
	@Autowired
	JWTAuthFilter jwtFilter;
	@Autowired
	CorsFilter corsFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http.cors(c -> c.disable());

		// ************************* CORS (CROSS-ORIGIN RESOURCE SHARING)
		// **************************

		// I browser di default adottano la Same Origin Policy, una politica stringente
		// che non permette
		// richieste http su origin differenti da quella del frontend

		// ************ Esempi di origin differenti ***************
		// http://localhost:3000 e http://localhost:3001 sono 2 diverse origin perché
		// hanno porta diversa
		// https://fe.com e https://be.com sono 2 diverse origin perché sono 2 domini
		// diversi
		// https://domain.com e http://domain.com sono 2 diverse origin perché hanno
		// protocolli diversi

		// Quello che possiamo fare è configurare il backend in maniera che istruisca il
		// frontend affinchè sia più permissivo. Ciò significa configurare CORS per
		// "rilassare" questa stringente politica


		http.csrf(c -> c.disable());

		// Se vogliamo utilizzare JWT dobbiamo disabilitare anche le sessioni
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// http.authorizeHttpRequests(auth ->
		// auth.requestMatchers("/users/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll())
				.csrf(AbstractHttpConfigurer::disable);


		/*
		 * http.authorizeHttpRequests((authorize) ->
		 * authorize.requestMatchers("/resource/**").hasAuthority("USER")
		 * .anyRequest().authenticated());
		 * 
		 * http.authorizeHttpRequests((authorize) ->
		 * authorize.requestMatchers("/users/**").hasAuthority("ADMIN")
		 * .anyRequest().authenticated());
		 */
		/*
		 * http.authorizeHttpRequests( (authz) -> authz.requestMatchers("/users/**",
		 * "").hasRole("ADMIN").anyRequest().authenticated());
		 * 
		 * 
		 * http.authorizeHttpRequests( (authz) ->
		 * authz.requestMatchers("/cliente/**").hasRole("ADMIN").anyRequest().
		 * authenticated());
		 */

		http.authorizeHttpRequests(
				(authz) -> authz.requestMatchers(HttpMethod.GET, "/cliente/**", "/fattura/**")
						// .permitAll()
						.hasAnyAuthority("USER", "ADMIN")
						.requestMatchers(HttpMethod.POST, "/fattura/**", "/users/**", "/cliente/**")
						.hasAuthority("ADMIN")
						// .permitAll()
						.anyRequest().authenticated());

		/*
		 * http.authorizeHttpRequests((authz) -> authz.requestMatchers(HttpMethod.GET,
		 * "/fattura/**") .hasAnyRole("USER", "ADMIN").anyRequest());
		 */
		/*
		 * http.authorizeHttpRequests( auth -> auth.requestMatchers(HttpMethod.POST,
		 * "/fattura/**", "/users/**", "/cliente/**")
		 * .hasRole("ADMIN").anyRequest().authenticated())
		 * .csrf(AbstractHttpConfigurer::disable);
		 */

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(corsFilter, JWTAuthFilter.class);

		return http.build();
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11); // 11 è il cosiddetto numero di ROUNDS ovvero quante volte viene eseguito
												// l'algoritmo. In pratica ci serve per settare la velocità di
												// esecuzione dell'algoritmo (+ è alto il numero, + lento l'algoritmo)
	}

}
