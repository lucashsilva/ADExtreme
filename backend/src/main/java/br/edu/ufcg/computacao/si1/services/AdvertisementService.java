package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisementUserException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface AdvertisementService {

    Advertisement create(Advertisement ad) throws InvalidAdvertisementUserException;

    Optional<Advertisement> getAdById(Long id);

    Collection<Advertisement> getAdByType(String type);

    Collection<Advertisement> getAdByTitle(String title);

    Collection<Advertisement> getAds();

    boolean update(Advertisement ad);

    boolean delete(Long id);

    Collection<Advertisement> getAdsByDate(Date initialDate, Date finalDate);
}
