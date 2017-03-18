package br.edu.ufcg.computacao.si1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import br.edu.ufcg.computacao.si1.services.NegotiationService;

@CrossOrigin
@RestController
@RequestMapping("api/users/buy")
public class NegotiationController {
	
	@Autowired
	private NegotiationService service;
	
	@Autowired
	private AuthenticationService authenticationService;

	public NegotiationController(NegotiationService negotiationService, AuthenticationService authenticationService) {
		this.service = negotiationService;
		this.authenticationService = authenticationService;
	}

	@RequestMapping(
			method = RequestMethod.POST,
			produces= MediaType.APPLICATION_JSON_VALUE
	)
	public void buyAdvertising(@RequestHeader(value="Authorization") String token, @RequestBody Long id) throws Exception {
		Optional<User> user;
		try {
			user = authenticationService.getUserFromToken(token);
			
			service.buyAdvertising(user.get(), id);
			
			//return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			//return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}
	}
}
