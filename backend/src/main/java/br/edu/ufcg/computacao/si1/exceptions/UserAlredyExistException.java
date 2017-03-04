package br.edu.ufcg.computacao.si1.exceptions;

@SuppressWarnings("serial")
public class UserAlredyExistException extends Exception {
	public UserAlredyExistException() {
		super("User already exist.");
	}
}
