package br.edu.ufcg.computacao.si1.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
    	return null;
    }

    @Override
    @JsonProperty(access = Access.WRITE_ONLY)
    public Object getCredentials() {
        return this.token;
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
        return this.user != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
    
    public User getUserInfo() {
    	return this.user;
    }

}