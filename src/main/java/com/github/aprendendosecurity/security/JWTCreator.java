package com.github.aprendendosecurity.security;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String ROLES_AUTHORITIES = "authorities";

	public static String create(String prefix, String stringKey, JWTObject jwtObject) {
		SecretKeySpec key = new SecretKeySpec(stringKey.getBytes(), "HmacSHA512");
		String token = Jwts.builder().subject(jwtObject.getSubject()).issuedAt(jwtObject.getIssuedAt()).expiration(jwtObject.getExpiration())
				.claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())).signWith(key).compact();

//		String token = Jwts.builder().setSubject(jwtObject.getSubject()).setIssuedAt(jwtObject.getIssuedAt()).setExpiration(jwtObject.getExpiration())
//				.claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())).signWith(SignatureAlgorithm.HS512, key).compact();
		return prefix + " " + token;
	}
	public static JWTObject create(String token, String prefix, String stringKey)
			throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException {
		SecretKeySpec key = new SecretKeySpec(stringKey.getBytes(), "HmacSHA512");
		JWTObject object = new JWTObject();

		token = token.replace(prefix, "");

		Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

		object.setSubject(claims.getSubject());
		object.setExpiration(claims.getExpiration());
		object.setIssuedAt(claims.getIssuedAt());
		object.setRoles((List) claims.get(ROLES_AUTHORITIES));

		return object;

	}
	private static List<String> checkRoles(List<String> roles) {
		return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
	}


}