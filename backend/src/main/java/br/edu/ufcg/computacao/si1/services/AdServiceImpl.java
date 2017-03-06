package br.edu.ufcg.computacao.si1.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.models.Advertising;
import br.edu.ufcg.computacao.si1.repositories.AdRepository;

@Service
@Transactional
public class AdServiceImpl implements AdService {

    private AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Advertising create(Advertising ad) {
        return adRepository.save(ad);
    }

    @Override
    public Optional<Advertising> getAd(Long id) {
        return Optional.ofNullable(adRepository.findOne(id));
    }

    @Override
    public Collection<Advertising> getAd(String type) {
        return adRepository.findAll().stream()
                .filter(ad -> ad.getType().toString().equals(type))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Advertising> getAds() {
        return adRepository.findAll();
    }

    @Override
    public boolean update(Advertising ad) {
        if (adRepository.exists(ad.getId())) {
            adRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (adRepository.exists(id)) {
            adRepository.delete(id);
            return true;
        }
        return false;
    }

}
