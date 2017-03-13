package br.edu.ufcg.computacao.si1.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.enums.AdType;
import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisingUserException;
import br.edu.ufcg.computacao.si1.models.Advertising;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.repositories.AdvertisingRepository;

@Service
@Transactional
public class AdvertisingServiceImpl implements AdvertisingService {

    private AdvertisingRepository advertisingRepository;

    public AdvertisingServiceImpl(AdvertisingRepository advertisingRepository) {
        this.advertisingRepository = advertisingRepository;
    }

    @Override
    public Advertising create(Advertising ad) throws InvalidAdvertisingUserException {
        AdType adType = ad.getType();
        User user = ad.getUser();

        if(user.getRole().equals(UserRole.NATURAL_PERSON)
                && (adType.equals(AdType.JOB) || adType.equals(AdType.SERVICE))){
            throw new InvalidAdvertisingUserException();
        }

        return advertisingRepository.save(ad);
    }

    @Override
    public Optional<Advertising> getAdById(Long id) {
        return Optional.ofNullable(advertisingRepository.findOne(id));
    }

    @Override
    public Collection<Advertising> getAdByType(String type) {
        return advertisingRepository.findAll().stream()
                .filter(ad -> ad.getType().name().equalsIgnoreCase(type))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Advertising> getAdByTitle(String title) {
        return advertisingRepository.findAll().stream()
                .filter(ad -> ad.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Advertising> getAds() {
        return advertisingRepository.findAll();
    }

    @Override
    public boolean update(Advertising ad) {
        if (advertisingRepository.exists(ad.getId())) {
            advertisingRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (advertisingRepository.exists(id)) {
            advertisingRepository.delete(id);
            return true;
        }
        return false;
    }

}
