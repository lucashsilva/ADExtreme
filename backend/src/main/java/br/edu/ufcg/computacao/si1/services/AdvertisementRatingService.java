package br.edu.ufcg.computacao.si1.services;


import br.edu.ufcg.computacao.si1.models.rating.AdvertisementRating;

import java.util.Collection;

public interface AdvertisementRatingService extends RatingService{

    Collection<AdvertisementRating> getRatingsByAdvertisementId(Long id);

}
