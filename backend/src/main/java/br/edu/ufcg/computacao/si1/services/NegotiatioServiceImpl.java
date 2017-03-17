package br.edu.ufcg.computacao.si1.services;

import java.util.Optional;

import br.edu.ufcg.computacao.si1.models.advertisement.CostAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;

@Service
public class NegotiatioServiceImpl implements NegotiationService{
	
	@Autowired
	AdvertisementService adService;

	@Override
	public void buyAdvertising(User user, Long id) throws Exception {
		Optional<Advertisement> ad = adService.getAdById(id);
		
		if(!user.getRole().equals(UserRole.NATURAL_PERSON))
			throw new Exception();
		
		if(ad.get().getClass() != CostAdvertisement.class)
			throw new Exception();
		
		CostAdvertisement costAdvertisement = (CostAdvertisement) ad.get();
		
		if(user.getCredit() < costAdvertisement.getPrice())
			throw new Exception();
		
		user.discountCredit(costAdvertisement.getPrice());
	}

}
