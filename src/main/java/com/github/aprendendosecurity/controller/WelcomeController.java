package com.github.aprendendosecurity.controller;

import com.github.aprendendosecurity.model.User;
import com.github.aprendendosecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {

	@GetMapping
	public String welcome() {
		return "Welcome to this welcome";
	}

}
