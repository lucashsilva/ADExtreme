package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.exceptions.EntityNotFoundException;
import br.edu.ufcg.computacao.si1.exceptions.RatingException;
import br.edu.ufcg.computacao.si1.exceptions.UserAutoRatingException;
import br.edu.ufcg.computacao.si1.exceptions.UserRatingOwnAdvertisementException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import br.edu.ufcg.computacao.si1.models.rating.Rating;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.repositories.AdvertisementRepository;
import br.edu.ufcg.computacao.si1.repositories.RatingRepository;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private UserRepository userRepository;
    private AdvertisementRepository advertisementRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository,
                             AdvertisementRepository advertisementRepository){
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Rating create(Rating rating) throws RatingException, EntityNotFoundException {
        verifyRating(rating);
        return ratingRepository.save(rating);
    }

    private void verifyRating(Rating rating) throws RatingException, EntityNotFoundException {

        Long id = rating.getRatedEntityId();

        if(userRepository.exists(id)){
            User user = userRepository.findOne(id);

            if(user.getName().toString().equals(rating.getPublisherName()))
                throw new UserAutoRatingException();

        } else if(advertisementRepository.exists(id)){
            Advertisement advertisement = advertisementRepository.findOne(id);

            if(advertisement.getUser().getName().toString().equals(rating.getPublisherName()))
                throw new UserRatingOwnAdvertisementException();

        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Optional<Rating> getRatingById(Long id) {
        return Optional.ofNullable(ratingRepository.findOne(id));
    }

    @Override
    public double getAverageRating(Long id) {
        Collection<Rating> ratings = getRatingsByRatedEntityId(id);

        if(ratings.size() == 0){
            return 0;
        }
        return ratings.stream()
                .mapToDouble(rating -> rating.getGrade()).sum() / ratings.size();
    }

    @Override
    public boolean update(Rating rating) {
        if(exists(rating.getId())){
            ratingRepository.save(rating);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(exists(id)){
            ratingRepository.delete(id);
            return true;
        }
        return false;
    }

    private boolean exists(Long id){
        return ratingRepository.findOne(id) != null;
    }

    @Override
    public Collection<Rating> getRatingsByRatedEntityId(Long id) {
        return ratingRepository.findAll().stream()
                .filter(rating -> rating.getRatedEntityId() == id).collect(Collectors.toList());
    }

}
