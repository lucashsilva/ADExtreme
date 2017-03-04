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
	
	public AuthenticatedUser(String name, String credentials, Integer id, String role) {
		this.name = name;
		this.credentials = credentials;
		this.id = id;
		this.role = role;
	}
	
	public AuthenticatedUser(String email) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getCredentials() {
		return credentials;
	}

	public Integer getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	}