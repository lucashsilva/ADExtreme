package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by ruan on 18/03/17.
 */
public class UserAutoRatingException extends RatingException {

    public UserAutoRatingException() {
        super("User can not rate himself.");
    }
}
