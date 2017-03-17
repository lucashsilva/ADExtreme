package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisimentUserException;
import br.edu.ufcg.computacao.si1.models.advertising.Advertisement;

public interface AdvertisementService {

    public Advertisement create(Advertisement ad) throws InvalidAdvertisimentUserException;

    public Optional<Advertisement> getAdById(Long id);

    public Collection<Advertisement> getAdByType(String type);

    public Collection<Advertisement> getAdByTitle(String title);

    public Collection<Advertisement> getAds();

    public boolean update(Advertisement ad);

    public boolean delete(Long id);

}
