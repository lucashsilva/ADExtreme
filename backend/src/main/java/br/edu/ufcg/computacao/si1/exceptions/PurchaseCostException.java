package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 18/03/17.
 */
public class PurchaseCostException extends Exception{
    public PurchaseCostException() {
        super("Cost can not be purchased.");
    }

    public PurchaseCostException (String message) {
        super(message);
    }
}
