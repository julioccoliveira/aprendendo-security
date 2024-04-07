package com.github.aprendendosecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping
	public String welcome() {
		return "Welcome to this welcome";
	}

	@GetMapping("/users")
	public String users() {
		return "Authorized user";
	}

	@GetMapping("/managers")
	public String managers() {
		return "Authorized manager";
	}

}
