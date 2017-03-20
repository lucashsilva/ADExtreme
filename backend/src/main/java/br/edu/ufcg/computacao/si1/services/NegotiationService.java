package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.exceptions.InsufficientCreditException;
import br.edu.ufcg.computacao.si1.exceptions.PurchaseServiceException;
import br.edu.ufcg.computacao.si1.exceptions.PurchaseJobException;
import br.edu.ufcg.computacao.si1.exceptions.PurchaseNotServiceException;
import br.edu.ufcg.computacao.si1.models.user.User;

public interface NegotiationService {
	boolean buyAdvertising(User User, Long id) throws PurchaseJobException, InsufficientCreditException, PurchaseServiceException;

    boolean buyService(User user, Long id, String date) throws PurchaseNotServiceException, InsufficientCreditException;
}
