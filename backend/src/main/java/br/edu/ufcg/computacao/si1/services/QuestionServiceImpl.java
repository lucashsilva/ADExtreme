package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.models.question.Question;
import br.edu.ufcg.computacao.si1.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ruan on 11/03/17.
 */
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question create(Question question){

        return questionRepository.save(question);
    }
}
