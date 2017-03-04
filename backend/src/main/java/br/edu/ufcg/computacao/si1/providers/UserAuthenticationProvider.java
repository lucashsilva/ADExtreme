package br.edu.ufcg.computacao.si1.providers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.edu.ufcg.computacao.si1.services.UsuarioServiceImpl;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private UsuarioServiceImpl usuarioService;
    
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
   
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        if (usuarioService.autenticar(email, password)) {
            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
        } else {
        	throw new BadCredentialsException("Bad credentials.");
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
    	return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}