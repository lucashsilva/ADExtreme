package br.edu.ufcg.computacao.si1.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import br.edu.ufcg.computacao.si1.services.UserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(value = "api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private AuthenticationService authenticationService;

    public UserController(UserServiceImpl userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            userService.create(user);
            return new ResponseEntity<User>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        } catch(TransactionSystemException e) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<User> getUser(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);

        if(user.isPresent()) {
        	return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        } else {
        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    

    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    public ResponseEntity<User> getUserInfo(@RequestHeader(value="Authorization") String token) throws IOException, URISyntaxException{
        Optional<User> user;
		try {
			user = authenticationService.getUserFromToken(token);
			
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}

    }

    @RequestMapping(
            method = RequestMethod.PUT
    )
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

	
}
