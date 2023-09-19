package com.capstone.progettofinale.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTAuthFilter extends OncePerRequestFilter {


	@Autowired
	JWTTools jwttools;

	@Autowired
	UserService usersService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new UnauthorizedException("Per favore passa il token nell'authorization header");
		String token = authHeader.substring(7);
		System.out.println("TOKEN -------> " + token);


		jwttools.verifyToken(token);


		try {
		String id = jwttools.extractSubject(token);
			User currentUser = usersService.findById(Long.valueOf(id));

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(currentUser, null,
				currentUser.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authToken);


		filterChain.doFilter(request, response);
	} catch (NumberFormatException e) {
		throw new UnauthorizedException("Token non valido.");
	}


	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		System.out.println(request.getServletPath());

		return new AntPathMatcher().match("/auth/**", request.getRequestURI())
				|| new AntPathMatcher().match("/mete/image/**", request.getServletPath())
				|| new AntPathMatcher().match("/alloggi/image/**", request.getServletPath())
				|| new AntPathMatcher().match("/mete/città/most_rated", request.getServletPath())
				|| new AntPathMatcher().match("/mete/destinazioni/most_rated", request.getServletPath());
	}

}
