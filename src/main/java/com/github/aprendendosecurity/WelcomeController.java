package com.github.aprendendosecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping
	public String welcome() {
		return "Welcome to this welcome";
	}

	@GetMapping("/users")
	@PreAuthorize("hasAnyRole('MANAGER', 'USER')")
	public String users() {
		return "Authorized user";
	}

	@GetMapping("/managers")
	@PreAuthorize("hasRole('MANAGER')")
	public String managers() {
		return "Authorized manager";
	}

}
