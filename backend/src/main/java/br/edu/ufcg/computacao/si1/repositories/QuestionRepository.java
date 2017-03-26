package br.edu.ufcg.computacao.si1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.si1.models.question.Question;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
