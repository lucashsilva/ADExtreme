package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
