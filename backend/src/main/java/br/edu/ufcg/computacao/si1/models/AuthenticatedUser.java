package br.edu.ufcg.computacao.si1.models;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 1L;
	private String name;
	private String credentials;
	private Integer id;
	private String role;
	private boolean authenticated = true;

	public AuthenticatedUser() {
	}

	public AuthenticatedUser(String email) {
		this.name = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return role;
	}

}