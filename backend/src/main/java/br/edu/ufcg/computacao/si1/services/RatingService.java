package br.edu.ufcg.computacao.si1.services;


import br.edu.ufcg.computacao.si1.exceptions.RatingException;
import br.edu.ufcg.computacao.si1.models.rating.Rating;

import java.util.Collection;
import java.util.Optional;

public interface RatingService {

    Rating create(Rating rating) throws RatingException;

    Optional<Rating> getRatingById(Long id);

    double getAverageRating(Long id);

    boolean update(Rating rating);

    boolean delete(Long id);
}
