package br.edu.ufcg.computacao.si1.services;

import java.util.Optional;

import br.edu.ufcg.computacao.si1.exceptions.InsufficientCreditException;
import br.edu.ufcg.computacao.si1.exceptions.PurchaseCostException;
import br.edu.ufcg.computacao.si1.exceptions.PurchaseJobException;
import br.edu.ufcg.computacao.si1.models.advertisement.CostAdvertisement;
import br.edu.ufcg.computacao.si1.models.advertisement.JobAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;

@Service
public class NegotiatioServiceImpl implements NegotiationService{
	
	@Autowired
	private AdvertisementService adService;

	@Autowired
	private UserService userService;

	@Override
	public boolean buyAdvertising(User user, Long id) throws PurchaseJobException, InsufficientCreditException, PurchaseCostException {
		Optional<Advertisement> ad = adService.getAdById(id);
		
		if(ad.get().getClass() == JobAdvertisement.class)
			throw new PurchaseJobException();

		if(ad.get().getClass() == CostAdvertisement.class)
			throw new PurchaseCostException();
		
		if(user.getCredit() < ad.get().getPrice())
			throw new InsufficientCreditException();

		User salesman = ad.get().getUser();

		user.discountCredit(ad.get().getPrice());
		salesman.increaseCredit(ad.get().getPrice());

		return userService.update(user) && userService.update(salesman);
	}

}
