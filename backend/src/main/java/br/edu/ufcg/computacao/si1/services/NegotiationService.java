package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.models.user.User;

public interface NegotiationService {
	void buyAdvertising(User User, Long id) throws Exception;
}
