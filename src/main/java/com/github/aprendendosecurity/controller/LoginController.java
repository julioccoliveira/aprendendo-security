package com.github.aprendendosecurity.controller;

import com.github.aprendendosecurity.dto.Login;
import com.github.aprendendosecurity.dto.Session;
import com.github.aprendendosecurity.model.User;
import com.github.aprendendosecurity.repository.UserRepository;
import com.github.aprendendosecurity.security.JWTCreator;
import com.github.aprendendosecurity.security.JWTObject;
import com.github.aprendendosecurity.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
@RestController
public class LoginController {
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private SecurityConfig securityConfig;
	@Autowired
	private UserRepository repository;

	@PostMapping("/login")
	public Session logar(@RequestBody Login login){
		User user = repository.findByUsername(login.getUsername());
		if(user != null) {
			boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
			if (!passwordOk) {
				throw new RuntimeException("Wrong password for: " + login.getUsername());
			}
			//Estamos enviando um objeto Sessão para retornar mais informações do usuário
			Session sessao = new Session();
			sessao.setLogin(user.getUsername());

			JWTObject jwtObject = new JWTObject();
			jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
			jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
			jwtObject.setRoles(user.getRoles());
			sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
			return sessao;
		} else {
			throw new RuntimeException("Login error.");
		}
	}
}

