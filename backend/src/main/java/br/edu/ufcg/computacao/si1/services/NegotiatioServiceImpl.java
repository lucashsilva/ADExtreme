package br.edu.ufcg.computacao.si1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.advertising.Advertising;
import br.edu.ufcg.computacao.si1.models.advertising.CostAd;
import br.edu.ufcg.computacao.si1.models.user.User;

@Service
public class NegotiatioServiceImpl implements NegotiationService{
	
	@Autowired
	AdvertisingService adService;

	@Override
	public void buyAdvertising(User user, Long id) throws Exception {
		Optional<Advertising> ad = adService.getAdById(id);
		
		if(!user.getRole().equals(UserRole.NATURAL_PERSON))
			throw new Exception();
		
		if(ad.get().getClass() != CostAd.class)
			throw new Exception();
		
		CostAd costAd = (CostAd) ad.get();
		
		if(user.getCredit() < costAd.getPrice())
			throw new Exception();
		
		user.discountCredit(costAd.getPrice());
	}

}
