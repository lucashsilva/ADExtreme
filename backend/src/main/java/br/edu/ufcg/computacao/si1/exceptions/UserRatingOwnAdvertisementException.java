package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by ruan on 18/03/17.
 */
public class UserRatingOwnAdvertisementException extends RatingException {

    public UserRatingOwnAdvertisementException() {
        super("User can not rate his own advertisement.");
    }
}
