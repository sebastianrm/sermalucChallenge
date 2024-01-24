/**
 * 
 */
package com.srm.sermalucChallenge.utils;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Sermaluc Challenge
 * 
 * Java Web Token Utilities
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Component
public class JwtUtils {
	
	private static final String BEARER = "Bearer ";

	private static final String AUTHORIZATION = "Authorization";


	@Value("${srm.app.jwtSecret}")
	private String jwtSecret;

	/**
	 * Generate token From passport
	 * @param authentication
	 * @return
	 */
	public String generateJwtToken(
			@NotBlank(message = "{notblank.email}")
			@Size(max = 50)
			@Email(message = "{notvalid.email}")
			String mail) {

		return Jwts.builder()
				.setSubject(mail)
				.setIssuedAt(new Date())
				.signWith(key(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String getSubjectFromJwtToken(String token) {
		
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public Date getExpiredDate(String token) {
		
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getExpiration();
		
	}

	public boolean validateJwtToken(String authToken) throws MalformedJwtException, ExpiredJwtException, UnsupportedJwtException,IllegalArgumentException{
		
		Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
		return true;
		

	}

	public String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader(AUTHORIZATION);

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER)) {
			return headerAuth.substring(7);
		}

		return null;
	}

}
