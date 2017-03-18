package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.rating.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, Long> {

    Collection<UserRating> findByUserId(Long id);

}
