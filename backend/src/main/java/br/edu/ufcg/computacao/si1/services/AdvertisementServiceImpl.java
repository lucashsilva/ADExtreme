package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisementUserException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import br.edu.ufcg.computacao.si1.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Advertisement create(Advertisement ad) throws InvalidAdvertisementUserException {
        return advertisementRepository.save(ad);
    }

    @Override
    public Optional<Advertisement> getAdById(Long id) {
        return Optional.ofNullable(advertisementRepository.findOne(id));
    }

    @Override
    public Collection<Advertisement> getAdByType(String type) {
        return advertisementRepository.findByType(type);
    }

    @Override
    public Collection<Advertisement> getAdByTitle(String title) {
        return advertisementRepository.findAll().stream()
                .filter(ad -> ad.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Advertisement> getAds() {
        return advertisementRepository.findAll();
    }

    @Override
    public boolean update(Advertisement ad) {
        if (advertisementRepository.exists(ad.getId())) {
            advertisementRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (advertisementRepository.exists(id)) {
            advertisementRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Advertisement> getAdsByDate(Date initialDate, Date finalDate) {
        Date end;
        if(finalDate == null)
            end = new Date();
        else
            end = finalDate;

        return advertisementRepository.findAll().stream().filter(ad ->
                ad.getPublicationDate().after(initialDate) && ad.getPublicationDate().before(end))
                .collect(Collectors.toList());
    }

}
