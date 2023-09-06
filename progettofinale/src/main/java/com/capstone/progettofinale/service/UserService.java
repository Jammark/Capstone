package com.capstone.progettofinale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserByID(Long id) throws NotFoundException {
		return userRepository.findById(id).orElse(null);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(Long id) throws NotFoundException {
		User userTrovato = userRepository.findById(id).orElse(null);
		userRepository.delete(userTrovato);
	}

	public User updateUserById(User nuovoUser, Long id) {
		User userTrovato = userRepository.findById(id).orElse(null);

		if (userTrovato != null) {
			userTrovato.setCognome(nuovoUser.getCognome());
			userTrovato.setDataDiNascita(nuovoUser.getDataDiNascita());
			userTrovato.setNome(nuovoUser.getNome());
			userTrovato.setPassword(nuovoUser.getPassword());
			userTrovato.setRuolo(nuovoUser.getRuolo());
			userTrovato.setUsername(nuovoUser.getUsername());
			return userRepository.save(userTrovato);
		} else {
			return null;
		}
	}

	public User findById(Long id) {
		return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException());
	}

}
