package com.github.aprendendosecurity.controller;

import com.github.aprendendosecurity.model.User;
import com.github.aprendendosecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public String users() {
		return "Authorized user";
	}

	@PostMapping
	public void postUser(@RequestBody User user) {
		service.createUser(user);
	}
}
