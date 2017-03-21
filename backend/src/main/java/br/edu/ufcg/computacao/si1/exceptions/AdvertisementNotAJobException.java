package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 20/03/17.
 */
public class AdvertisementNotAJobException extends Exception {
    public AdvertisementNotAJobException() {
        super("User only can apply for a Job");
    }

    public AdvertisementNotAJobException(String message) {
        super(message);
    }
}
