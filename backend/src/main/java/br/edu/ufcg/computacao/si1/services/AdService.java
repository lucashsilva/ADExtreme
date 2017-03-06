package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.models.Advertising;

public interface AdService {

    public Advertising create(Advertising anuncio);

    public Optional<Advertising> getAd(Long id);

    public Collection<Advertising> getAd(String tipo);

    public Collection<Advertising> getAds();

    public boolean update(Advertising anuncio);

    public boolean delete(Long id);

}
