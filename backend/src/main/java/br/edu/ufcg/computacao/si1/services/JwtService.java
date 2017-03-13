package br.edu.ufcg.computacao.si1.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    private static final String ISSUER = "ad-extreme";
    private UserService userService;
    private final String secretKey = "si1lab03";

    @Autowired
    public JwtService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public String tokenFor(User user) throws IOException, URISyntaxException {
       
        Date expiration = Date.from(LocalDateTime.now().plusHours(24 * 7).toInstant(ZoneOffset.UTC));
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    
    public Optional<User> getUserFromToken(String token) throws IOException, URISyntaxException, UserNotFoundException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        
        Optional<User> user = userService.getUserByEmail(claims.getBody().getSubject().toString());
        
        if(user.isPresent()) {
        	return user;
        } else {
        	throw new UserNotFoundException();
        }
    }
}