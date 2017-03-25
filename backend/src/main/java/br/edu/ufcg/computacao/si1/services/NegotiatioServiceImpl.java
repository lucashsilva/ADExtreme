package br.edu.ufcg.computacao.si1.services;

import java.io.IOException;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.*;
import br.edu.ufcg.computacao.si1.models.advertisement.JobAdvertisement;
import br.edu.ufcg.computacao.si1.models.advertisement.ServiceAdvertisement;
import br.edu.ufcg.computacao.si1.models.user.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;

@Service
public class NegotiatioServiceImpl implements NegotiationService{
	
	@Autowired
	private AdvertisementService adService;

	@Autowired
	private UserService userService;

	@Override
	public boolean buyAdvertising(User user, Long id) throws PurchaseJobException, InsufficientCreditException, PurchaseServiceException {
		Optional<Advertisement> ad = adService.getAdById(id);
		
		if(ad.get().getClass() == JobAdvertisement.class)
			throw new PurchaseJobException();

		if(ad.get().getClass() == ServiceAdvertisement.class)
			throw new PurchaseServiceException();
		
		if(user.getCredit() < ad.get().getValue())
			throw new InsufficientCreditException();

		User salesman = ad.get().getUser();

		user.discountCredit(ad.get().getValue());
		salesman.increaseCredit(ad.get().getValue());

		adService.delete(id);
		return userService.update(user) && userService.update(salesman);
	}

	@Override
	public boolean buyService(User user, Long id, String date) throws PurchaseNotServiceException, InsufficientCreditException, IOException {
		Optional<Advertisement> ad = adService.getAdById(id);

		if(ad.get().getClass() != ServiceAdvertisement.class)
			throw new PurchaseNotServiceException();

		if(user.getCredit() < ad.get().getValue())
			throw new InsufficientCreditException();

		User salesman = ad.get().getUser();
		ServiceAdvertisement sAd = (ServiceAdvertisement) ad.get();

		user.discountCredit(sAd.getValue());
		salesman.increaseCredit(sAd.getValue());

		TODO: sAd.setScheduledDate(DateDeserializer.deserialize(date));

		return userService.update(user) && userService.update(salesman) && adService.update(sAd);
	}

	@Override
	public boolean applyForAJob(User user, Long id) throws NotLegalPersonException, AdvertisementNotAJobException {
		Optional<Advertisement> ad = adService.getAdById(id);

		if(ad.get().getClass() != JobAdvertisement.class)
			throw new AdvertisementNotAJobException();

		if(user.getRole().equals(UserRole.NATURAL_PERSON))
			throw new NotLegalPersonException();

		JobAdvertisement jAd = (JobAdvertisement) ad.get();

		jAd.addCandidate(new Candidate(user.getName().toString(), user.getEmail()));

		return adService.update(jAd);
	}


}
