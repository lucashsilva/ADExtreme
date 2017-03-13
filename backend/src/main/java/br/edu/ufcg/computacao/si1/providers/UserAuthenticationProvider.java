package br.edu.ufcg.computacao.si1.providers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.edu.ufcg.computacao.si1.exceptions.FailedAuthenticationException;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.security.AuthenticatedUser;
import br.edu.ufcg.computacao.si1.services.JwtService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @Autowired
    public UserAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Optional<User> possibleProfile = jwtService.verify((String) authentication.getCredentials());
            
            return new AuthenticatedUser(possibleProfile.get());
        } catch (Exception e) {
            throw new FailedAuthenticationException("Failed to verify token" + e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticatedUser.class.equals(authentication);
    }
}