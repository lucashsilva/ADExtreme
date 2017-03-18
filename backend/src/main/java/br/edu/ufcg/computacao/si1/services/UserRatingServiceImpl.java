package br.edu.ufcg.computacao.si1.services;


import br.edu.ufcg.computacao.si1.exceptions.UserAutoRatingException;
import br.edu.ufcg.computacao.si1.models.rating.Rating;
import br.edu.ufcg.computacao.si1.models.rating.UserRating;
import br.edu.ufcg.computacao.si1.repositories.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

public class UserRatingServiceImpl implements UserRatingService {

    @Autowired
    private UserRatingRepository userRatingRepository;

    @Override
    public UserRating create(Rating rating) throws UserAutoRatingException{
        UserRating userRating = (UserRating) rating;
        String userName = userRating.getUser().getName().toString();

        if(userName.equals(userRating.getPublisherName())){
            throw new UserAutoRatingException();
        }

        return userRatingRepository.save(userRating);
    }

    @Override
    public Optional<Rating> getRatingById(Long id) {
        return Optional.ofNullable(userRatingRepository.findOne(id));
    }

    @Override
    public double getAverageRating(Long id) {
        double average = 0;
        if(!exists(id)){
            return average;
        }

        Collection<UserRating> ratings = userRatingRepository.findByUserId(id);
        average = ratings.stream()
                .mapToDouble(userRating -> userRating.getGrade()).sum() / ratings.size();

        return average;
    }

    @Override
    public boolean update(Rating rating) {
        if(exists(rating.getId())){
            userRatingRepository.save((UserRating) rating);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(exists(id)){
            userRatingRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Collection<UserRating> getRatingsByUserId(Long id) {
        return userRatingRepository.findByUserId(id);
    }

    private boolean exists(Long id){
        return userRatingRepository.findOne(id) != null;
    }
}
