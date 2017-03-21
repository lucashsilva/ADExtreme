package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 20/03/17.
 */
public class NotLegalPersonException extends Exception {
    public NotLegalPersonException() {
        super("Legal person can not aplly for a job.");
    }

    public  NotLegalPersonException(String message) {
        super(message);
    }
}
