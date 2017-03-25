package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.exceptions.*;
import br.edu.ufcg.computacao.si1.models.user.User;

import java.io.IOException;

public interface NegotiationService {
	boolean buyAdvertising(User User, Long id) throws PurchaseJobException, InsufficientCreditException, PurchaseServiceException;

    boolean buyService(User user, Long id, String date) throws PurchaseNotServiceException, InsufficientCreditException, IOException;

    boolean applyForAJob(User user, Long id) throws NotLegalPersonException, AdvertisementNotAJobException;
}
