package br.edu.ufcg.computacao.si1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.exceptions.UsuarioJaExisteException;
import br.edu.ufcg.computacao.si1.models.Usuario;
import br.edu.ufcg.computacao.si1.services.TokenAuthenticationService;
import br.edu.ufcg.computacao.si1.services.UsuarioServiceImpl;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	@Autowired
	private TokenAuthenticationService tokenService;
	
	@CrossOrigin
	@RequestMapping(
			value = "api/users",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> addUser(@RequestBody Usuario user) {
		try {
			usuarioService.create(user);
		
			return new ResponseEntity<Usuario>(HttpStatus.CREATED);
		} catch (UsuarioJaExisteException e) {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}
		
	}
	
}
