package com.github.aprendendosecurity.service;

import com.github.aprendendosecurity.model.User;
import com.github.aprendendosecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public void createUser(User user) {
		String pass = user.getPassword();
		user.setPassword(encoder.encode(pass));
		repository.save(user);
	}
}
