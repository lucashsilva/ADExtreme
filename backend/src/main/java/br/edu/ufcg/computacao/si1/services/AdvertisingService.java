package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisingUserException;
import br.edu.ufcg.computacao.si1.models.Advertising;

public interface AdvertisingService {

    public Advertising create(Advertising ad) throws InvalidAdvertisingUserException;

    public Optional<Advertising> getAdById(Long id);

    public Collection<Advertising> getAdByType(String type);

    public Collection<Advertising> getAdByTitle(String title);

    public Collection<Advertising> getAds();

    public boolean update(Advertising ad);

    public boolean delete(Long id);

}
