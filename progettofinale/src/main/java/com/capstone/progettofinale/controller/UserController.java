package com.capstone.progettofinale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.model.UserRole;
import com.capstone.progettofinale.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;



	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		try {
			User user = userService.findById(id);
			return ResponseEntity.ok(user);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}





	@PutMapping("upgrade/{id}")
	public ResponseEntity<User> upgrade(@PathVariable Long id) {
		User u = userService.findById(id);
		if (u == null) {
			return ResponseEntity.notFound().build();
		} else {
			u.setRuolo(UserRole.ADMIN);
			userService.saveUser(u);
			return ResponseEntity.ok(u);
		}
	}
}
