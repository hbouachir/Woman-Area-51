package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Answer;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.repositories.AnswerRepository;
import tn.esprit.spring.repositories.QuestionRepository;
import tn.esprit.spring.repositories.QuizRepository;
@Service
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    QuestionRepository qr;
    @Autowired
    AnswerRepository ar;

    @Override
    public Answer addAnswer(Answer answer, Long idQuestion) {
        Question question = qr.findById(idQuestion).orElse(null);
        answer.setQuestion(question);
        return ar.save(answer);
    }

    @Override
    public void deleteAnswer(Answer answer) {
        ar.delete(answer);
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        return ar.save(answer);
    }
}
