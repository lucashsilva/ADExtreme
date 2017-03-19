package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 18/03/17.
 */
public class PurchaseJobException extends Exception{
    public PurchaseJobException() {
        super("Job can not be purchased.");
    }

    public PurchaseJobException(String message) {
        super(message);
    }
}
