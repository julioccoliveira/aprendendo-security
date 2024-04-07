package com.github.aprendendosecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/").permitAll()
				.requestMatchers(HttpMethod.POST, "/login").permitAll()
				.requestMatchers("/users").hasAnyRole("USER", "MANAGER")
				.requestMatchers("/managers").hasRole("MANAGER")
				.anyRequest().authenticated()
		).formLogin(Customizer.withDefaults());
		return http.build();
	}

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
}
