package com.github.aprendendosecurity.security;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JWTObject {
	private String subject; 	//Username
	private Date issuedAt; 		//Token created at
	private Date expiration; 	//Token expires at
	private List<String> roles; //User roles

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setRoles(String... roles){
		this.roles = Arrays.asList(roles);
	}
}