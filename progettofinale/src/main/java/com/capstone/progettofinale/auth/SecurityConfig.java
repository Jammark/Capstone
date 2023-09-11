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
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JWTAuthFilter jwtFilter;
	@Autowired
	CorsFilter corsFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http.cors(c -> c.disable());

		http.csrf(c -> c.disable());


		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll())
				.csrf(AbstractHttpConfigurer::disable);


		http.authorizeHttpRequests(auth -> auth.requestMatchers("/mete/image/**", "/alloggi/image/**").permitAll())
				.csrf(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(
				(authz) -> authz
						.requestMatchers(HttpMethod.GET, "/mete/**", "/alloggi/**", "/stazioni/**", "/trasporti/**",
								"/prenotazioni/**")
						.hasAnyAuthority("USER", "ADMIN")
						.requestMatchers(HttpMethod.POST, "/prenotazioni/**", "/ratings/**")
						.hasAnyAuthority("USER", "ADMIN")
						.requestMatchers(HttpMethod.POST, "/prenotazioni/**", "/ratings/**")
						.hasAnyAuthority("USER", "ADMIN")
						.requestMatchers(HttpMethod.POST, "/mete/**", "/users/**", "/alloggi/**", "/stazioni/**",
								"/trasporti/**")
						.hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/mete/**", "/users/**", "/alloggi/**", "/stazioni/**",
								"/trasporti/**")
						.hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/mete/**", "/users/**", "/alloggi/**", "/stazioni/**",
								"/trasporti/**")
						.hasAuthority("ADMIN")
						.anyRequest().authenticated());



		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(corsFilter, JWTAuthFilter.class);

		return http.build();
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

}
