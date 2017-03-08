package br.edu.ufcg.computacao.si1.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException() {
		super("User already exist.");
	}
}
