package br.edu.ufcg.computacao.si1.providers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.edu.ufcg.computacao.si1.exceptions.FailedAuthenticationException;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.security.AuthenticatedUser;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationService authenticationService;

    @Autowired
    public UserAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Optional<User> possibleProfile = authenticationService.getUserFromToken((String) authentication.getCredentials());
            
            return new AuthenticatedUser(possibleProfile.get(), (String) authentication.getCredentials());
        } catch (Exception e) {
            throw new FailedAuthenticationException("Authentication failed: " + e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (AuthenticatedUser.class.isAssignableFrom(authentication));
    }
}