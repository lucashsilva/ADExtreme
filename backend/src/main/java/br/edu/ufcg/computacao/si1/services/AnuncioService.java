package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.models.Anuncio;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AnuncioService {

    public Anuncio create(Anuncio anuncio);

    public Optional<Anuncio> getAnuncio(Long id);

    public Collection<Anuncio> getAnuncio(String tipo);

    public Collection<Anuncio> getAnuncios();

    public boolean update(Anuncio anuncio);

    public boolean delete(Long id);

}
