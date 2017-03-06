package br.edu.ufcg.computacao.si1.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.models.Ad;
import br.edu.ufcg.computacao.si1.repositories.AdRepository;

@Service
@Transactional
public class AdServiceImpl implements AdService {

    private AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Ad create(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Optional<Ad> getAd(Long id) {
        return Optional.ofNullable(adRepository.findOne(id));
    }

    @Override
    public Collection<Ad> getAd(String type) {
        return adRepository.findAll().stream()
                .filter(ad -> ad.getType().equals(type))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Ad> getAds() {
        return adRepository.findAll();
    }

    @Override
    public boolean update(Ad ad) {
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
