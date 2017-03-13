package br.edu.ufcg.computacao.si1.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufcg.computacao.si1.models.User;

@JsonInclude(Include.NON_NULL)
public class AuthenticatedUser implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private User user;

    public AuthenticatedUser(String token) {
        this.token = token;
    }
    
    public AuthenticatedUser(User user) {
    	this.user = user;
    }
    
    public AuthenticatedUser(User user, String token) {
    	this.user = user;
    	this.token = token;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    	
    	if(user != null) {
    		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
    	}
    	
        return grantedAuthorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
    
    public String getToken() {
    	return this.token;
    }
    

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true; //this object is created only when theres a valid authentication
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

}