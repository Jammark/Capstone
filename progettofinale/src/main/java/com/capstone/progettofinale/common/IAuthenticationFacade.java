package com.capstone.progettofinale.common;

import org.springframework.security.core.Authentication;

import com.capstone.progettofinale.model.User;

public interface IAuthenticationFacade {
	Authentication getAuthentication();

	User getUser();
}
