package br.edu.ufcg.computacao.si1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import br.edu.ufcg.computacao.si1.exceptions.UserAlredyExistException;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.services.TokenAuthenticationService;
import br.edu.ufcg.computacao.si1.services.UserServiceImpl;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = "api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private TokenAuthenticationService tokenService;
	

    @RequestMapping(
            method = RequestMethod.POST
    )
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


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUserById(id).get();

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{email}",
            method = RequestMethod.GET
    )
    public ResponseEntity<User> getUser(@PathVariable String email){
        User user = userService.getUserByEmail(email).get();

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<User>> getUsers(){
        Collection<User> users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
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
