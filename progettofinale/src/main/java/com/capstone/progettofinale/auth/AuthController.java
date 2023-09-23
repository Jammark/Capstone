package com.capstone.progettofinale.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.model.UserRole;
import com.capstone.progettofinale.payload.LoginSuccessfullPayload;
import com.capstone.progettofinale.payload.UserLoginPayload;
import com.capstone.progettofinale.payload.UserRequestPayload;
import com.capstone.progettofinale.payload.UserResponsePayload;
import com.capstone.progettofinale.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	UserService usersService;

	@Autowired
	JWTTools jwtTools;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserResponsePayload> saveUser(@Valid @RequestBody UserRequestPayload body) {
		try {
			try {
				usersService.findByUsername(body.getEmail());
				throw new BadRequestException("Username utente già registrato.");
			} catch (IllegalArgumentException e) {
				log.info("Email utente non registrata");
			}

			User user = usersService.saveUser(convertPayload(body));
			UserResponsePayload up = new UserResponsePayload(user.getId(), user.getUsername(), user.getNome(),
					user.getCognome(), user.getDataDiNascita(), user.getRuolo().name());
			return ResponseEntity.ofNullable(up);
		} catch (DataIntegrityViolationException e) {
			log.error(e.getMessage());
			throw new BadRequestException("username già esistente.");
		}


	}

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginSuccessfullPayload login(@Valid @RequestBody UserLoginPayload body) {


		User user = usersService.findByUsername(body.getEmail());


		if (passwordEncoder.matches(body.getPassword(), user.getPassword())) {


			String token = jwtTools.createToken(user);
			return new LoginSuccessfullPayload(token, new UserResponsePayload(user.getId(), user.getUsername(),
					user.getNome(),
					user.getCognome(), user.getDataDiNascita(), user.getRuolo().name()));

		} else {

			throw new UnauthorizedException("Credenziali non valide!");
		}
	}

	private User convertPayload(UserRequestPayload body) {
		User newUser = new User(body.getEmail(), this.passwordEncoder.encode(body.getPassword()), body.getName(),
				body.getSurname(),
				body.getDataDiNascita(), UserRole.USER);
		return newUser;
	}

}
