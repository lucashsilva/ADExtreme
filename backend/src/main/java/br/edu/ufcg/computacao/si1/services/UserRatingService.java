package br.edu.ufcg.computacao.si1.services;


import br.edu.ufcg.computacao.si1.models.rating.UserRating;

import java.util.Collection;

public interface UserRatingService extends RatingService{

    Collection<UserRating> getRatingsByUserId(Long id);

}
