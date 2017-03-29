package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.exceptions.*;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import br.edu.ufcg.computacao.si1.services.NegotiationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class NegotiationController {

    @Autowired
    private NegotiationService negotiationService;

    @Autowired
    private AuthenticationService authenticationService;

    public NegotiationController(NegotiationService negotiationService, AuthenticationService authenticationService) {
        this.negotiationService = negotiationService;
        this.authenticationService = authenticationService;
    }
    
    @RequestMapping(
            value = "api/advertisements/buy/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HttpStatus> buyAdvertising(@RequestHeader(value = "Authorization") String token,
                                                     @PathVariable long id) throws Exception {
        Optional<User> user;
        try {
            user = authenticationService.getUserFromToken(token);
            negotiationService.buyAdvertising(user.get(), id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (PurchaseJobException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (PurchaseServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (InsufficientCreditException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (BuyOwnAdvertisementException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
