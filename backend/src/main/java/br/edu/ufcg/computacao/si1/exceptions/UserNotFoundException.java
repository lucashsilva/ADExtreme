package br.edu.ufcg.computacao.si1.exceptions;

public class UserNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		super("User does not exist. ");
	}
}
