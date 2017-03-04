package br.edu.ufcg.computacao.si1.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.models.Anuncio;
import br.edu.ufcg.computacao.si1.repositories.AnuncioRepository;

@Service
@Transactional
public class AnuncioServiceImpl implements AnuncioService {

    private AnuncioRepository anuncioRepository;

    public AnuncioServiceImpl(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @Override
    public Anuncio create(Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    @Override
    public Optional<Anuncio> getAnuncio(Long id) {
        return Optional.ofNullable(anuncioRepository.findOne(id));
    }

    @Override
    public Collection<Anuncio> getAnuncio(String tipo) {
        return anuncioRepository.findAll().stream()
                .filter(anuncio -> anuncio.getTipo().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Anuncio> getAnuncios() {
        return anuncioRepository.findAll();
    }

    @Override
    public boolean update(Anuncio anuncio) {
        if (anuncioRepository.exists(anuncio.getId())) {
            anuncioRepository.save(anuncio);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (anuncioRepository.exists(id)) {
            anuncioRepository.delete(id);
            return true;
        }
        return false;
    }
}
