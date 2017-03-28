package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 28/03/17.
 */
public class BuyOwnAdvertisementException extends Exception{
    public BuyOwnAdvertisementException() {
        super("User is trying to buy his own ad");
    }

    public BuyOwnAdvertisementException(String message) {
        super(message);
    }
}
