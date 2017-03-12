package br.edu.ufcg.computacao.si1.repositories;


import br.edu.ufcg.computacao.si1.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
