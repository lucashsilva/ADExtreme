package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
