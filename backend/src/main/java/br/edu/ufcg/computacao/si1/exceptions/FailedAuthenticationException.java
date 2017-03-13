package br.edu.ufcg.computacao.si1.exceptions;

import org.springframework.security.core.AuthenticationException;

public class FailedAuthenticationException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FailedAuthenticationException() {
		super("Bad credentials.");
	}
	
	public FailedAuthenticationException(String message) {
		super(message);
	}
}
