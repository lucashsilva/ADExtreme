package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 18/03/17.
 */
public class PurchaseServiceException extends Exception{
    public PurchaseServiceException() {
        super("Service can not be purchased.");
    }

    public PurchaseServiceException (String message) {
        super(message);
    }
}
