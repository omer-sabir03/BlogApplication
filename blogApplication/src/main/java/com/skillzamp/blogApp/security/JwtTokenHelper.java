package com.skillzamp.blogApp.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	
	public static final long jwtTokenValidity=10*60*60;
	public String secret="jwtTokenKey";
	
	//retrieve username from jwt token
	public String getUsernamefromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	//Retrieve expiration date From jwtToken
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T>  T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims=new HashMap<>();
		return validateToken(claims,userDetails.getUsername());
	}

	private String validateToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+jwtTokenValidity*100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String username=getUsernamefromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}	
	
	
	
	

	

