package br.edu.ufcg.computacao.si1.services;


import br.edu.ufcg.computacao.si1.exceptions.UserAutoRatingException;
import br.edu.ufcg.computacao.si1.exceptions.UserRatingOwnAdvertisementException;
import br.edu.ufcg.computacao.si1.models.rating.AdvertisementRating;
import br.edu.ufcg.computacao.si1.models.rating.Rating;
import br.edu.ufcg.computacao.si1.repositories.AdvertisementRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

public class AdvertisementRatingServiceImpl implements AdvertisementRatingService{

    @Autowired
    private AdvertisementRatingRepository advertisementRatingRepository;

    @Override
    public Rating create(Rating rating) throws UserRatingOwnAdvertisementException{
        AdvertisementRating advertisementRating = (AdvertisementRating) rating;
        String userName = advertisementRating.getAdvertisement().getUser().getName().toString();

        if(userName.equals(rating.getPublisherName())){
            throw new UserRatingOwnAdvertisementException();
        }

        return advertisementRatingRepository.save((AdvertisementRating) rating);
    }

    @Override
    public Optional<Rating> getRatingById(Long id) {
        return Optional.ofNullable(advertisementRatingRepository.findOne(id));
    }

    @Override
    public double getAverageRating(Long id) {
        double average = 0;
        if(!exists(id)){
            return average;
        }

        Collection<AdvertisementRating> ratings = advertisementRatingRepository.findByAdvertisementId(id);
        average = ratings.stream()
                .mapToDouble(advertisementRating -> advertisementRating.getGrade()).sum() / ratings.size();

        return average;
    }

    @Override
    public boolean update(Rating rating) {
        if(exists(rating.getId())){
            advertisementRatingRepository.save((AdvertisementRating) rating);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(exists(id)){
            advertisementRatingRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Collection<AdvertisementRating> getRatingsByAdvertisementId(Long id) {
        return advertisementRatingRepository.findByAdvertisementId(id);
    }

    private boolean exists(Long id){
        return advertisementRatingRepository.findOne(id) != null;
    }
}
