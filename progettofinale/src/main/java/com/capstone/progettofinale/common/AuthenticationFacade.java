package com.capstone.progettofinale.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.service.UserService;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Autowired
	private UserService uSrv;

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public User getUser() {

		return uSrv.findByUsername(this.getAuthentication().getName());
	}
}
