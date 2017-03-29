package br.edu.ufcg.computacao.si1.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.exceptions.BuyOwnAdvertisementException;
import br.edu.ufcg.computacao.si1.exceptions.InsufficientCreditException;
import br.edu.ufcg.computacao.si1.exceptions.PurchaseNotServiceException;
import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import br.edu.ufcg.computacao.si1.services.NegotiationService;

/**
 * Created by gustavo on 23/03/17.
 */

@CrossOrigin
@RestController
@RequestMapping("api/users/service")
public class ServiceController {

    @Autowired
    private NegotiationService service;

    @Autowired
    private AuthenticationService authenticationService;

    public ServiceController(NegotiationService negotiationService, AuthenticationService authenticationService) {
        this.service = negotiationService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HttpStatus> buyService(@RequestHeader(value="Authorization") String token,
                                                 @RequestParam("id") Long id, @RequestParam("date") String date)
            throws IOException, URISyntaxException {
        Optional<User> user;
        try {
            user = authenticationService.getUserFromToken(token);

            service.buyService(user.get(), id, date);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (InsufficientCreditException | BuyOwnAdvertisementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (PurchaseNotServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
