package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisimentUserException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;

public interface AdvertisementService {

    Advertisement create(Advertisement ad) throws InvalidAdvertisimentUserException;

    Optional<Advertisement> getAdById(Long id);

    Collection<Advertisement> getAdByType(String type);

    Collection<Advertisement> getAdByTitle(String title);

    Collection<Advertisement> getAds();

    boolean update(Advertisement ad);

    boolean delete(Long id);

}
