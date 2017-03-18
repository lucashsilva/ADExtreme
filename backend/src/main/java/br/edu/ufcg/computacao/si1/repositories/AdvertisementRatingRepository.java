package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.rating.AdvertisementRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdvertisementRatingRepository extends JpaRepository<AdvertisementRating, Long> {

    Collection<AdvertisementRating> findByAdvertisementId(Long id);
}
