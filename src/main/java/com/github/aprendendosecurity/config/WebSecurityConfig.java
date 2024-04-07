package com.github.aprendendosecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private SecurityDatabaseService securityService;

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/").permitAll()
				.requestMatchers(HttpMethod.POST, "/login").permitAll()
				.requestMatchers("/users").hasAnyRole("USER", "MANAGER")
				.requestMatchers("/managers").hasRole("MANAGER")
				.anyRequest().authenticated()
		).httpBasic(Customizer.withDefaults());
		return http.build();
	}
/*
Users will not be created like this anymore.
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.builder()
					.username("user")
					.password("{noop}user")
					.roles("USER")
					.build(),
				User.builder()
					.username("admin")
					.password("{noop}admin")
					.roles("USER", "MANAGER")
					.build());
	}
 */
}
