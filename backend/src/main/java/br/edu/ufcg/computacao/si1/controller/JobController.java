package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.exceptions.AdvertisementNotAJobException;
import br.edu.ufcg.computacao.si1.exceptions.NotLegalPersonException;
import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import br.edu.ufcg.computacao.si1.services.NegotiationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by gustavo on 23/03/17.
 */

@CrossOrigin
@RestController
@RequestMapping("api/users/job")
public class JobController {

    @Autowired
    private NegotiationService service;

    @Autowired
    private AuthenticationService authenticationService;

    public JobController(NegotiationService negotiationService, AuthenticationService authenticationService) {
        this.service = negotiationService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HttpStatus> applyForAJob(@RequestHeader(value="Authorization") String token, @RequestBody Long id)
            throws IOException, URISyntaxException {
        Optional<User> user;

        try {
            user = authenticationService.getUserFromToken(token);

            service.applyForAJob(user.get(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (NotLegalPersonException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (AdvertisementNotAJobException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
