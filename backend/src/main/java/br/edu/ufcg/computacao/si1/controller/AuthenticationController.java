package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.exceptions.FailedAuthenticationException;
import br.edu.ufcg.computacao.si1.models.user.UserCredentials;
import br.edu.ufcg.computacao.si1.security.AuthenticatedUser;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping(path = "api/auth/login")
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AuthenticatedUser login(@RequestBody UserCredentials credentials, HttpServletResponse response) throws FailedAuthenticationException {


        return authenticationService.authenticate(credentials)
                .map(user -> {
                    String token;

                    try {
                        token = authenticationService.tokenFor(user);
                        response.setHeader("Authorization", token);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return new AuthenticatedUser(user, token);
                })
                .orElseThrow(() -> new FailedAuthenticationException(credentials.getEmail()));
    }
}