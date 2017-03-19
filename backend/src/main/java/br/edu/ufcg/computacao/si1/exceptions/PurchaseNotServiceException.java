package br.edu.ufcg.computacao.si1.exceptions;

/**
 * Created by gustavo on 19/03/17.
 */
public class PurchaseNotServiceException extends  Exception{
    public PurchaseNotServiceException() {
        super("Advertisement you are trying to purchase is not a service");
    }

    public PurchaseNotServiceException(String message) {
        super(message);
    }
}
