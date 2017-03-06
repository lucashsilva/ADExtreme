package br.edu.ufcg.computacao.si1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.exceptions.UserAlredyExistException;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.services.TokenAuthenticationService;
import br.edu.ufcg.computacao.si1.services.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private TokenAuthenticationService tokenService;
	
	@CrossOrigin
	@RequestMapping(
			value = "api/users",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			userService.create(user);
		
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} catch (UserAlredyExistException e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		} catch(TransactionSystemException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
