package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.models.Ad;

public interface AdService {

    public Ad create(Ad anuncio);

    public Optional<Ad> getAd(Long id);

    public Collection<Ad> getAd(String tipo);

    public Collection<Ad> getAds();

    public boolean update(Ad anuncio);

    public boolean delete(Long id);

}
