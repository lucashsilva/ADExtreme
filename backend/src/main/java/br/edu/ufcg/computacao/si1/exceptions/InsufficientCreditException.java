package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 18/03/17.
 */
public class InsufficientCreditException extends Exception{
    public InsufficientCreditException() {
        super("User does not have enough credits.");
    }

    public InsufficientCreditException(String message) {
        super(message);
    }
}
