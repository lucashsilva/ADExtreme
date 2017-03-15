package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRatingRepository extends JpaRepository<UserRating, Long> {
}
