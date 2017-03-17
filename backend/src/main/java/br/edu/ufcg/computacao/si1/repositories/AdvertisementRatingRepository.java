package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.rating.AdvertisementRating;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdvertisementRatingRepository extends JpaRepository<AdvertisementRating, Long> {
}
