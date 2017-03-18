package br.edu.ufcg.computacao.si1.exceptions;


public class RatingException extends Exception {

    public RatingException(String msg) {
        super("Rating exception. " + msg);
    }
}
