package br.edu.ufcg.computacao.si1.services;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ufcg.computacao.si1.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

	private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
	private String secret = "si1lab03";
	private String tokenPrefix = "Bearer";
	private String headerString = "Authorization";

	public void addAuthentication(HttpServletResponse response, String email) throws IOException {
		// We generate a token now.

		String JWT = Jwts.builder().setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		response.addHeader(headerString, tokenPrefix + " " + JWT);
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Content-Type", "application/json");
		response.getWriter().append(getJSON(email, JWT));

	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(headerString);

		if (token != null) {
			// parse the token.
			String email = extractEmail(token);
			if (email != null) // we managed to retrieve a user
			{
				return new User(email);
			}
		}
		return null;
	}

	public String extractEmail(String token) {
		if (token != null) {
			// parse the token.
			String email = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			return email;
		}
		return null;
	}

	public String getJSON(String email, String token) {
		return "{\"email\": \"" + email + "\", \"token\": \"" + token + "\"}";
	}
}