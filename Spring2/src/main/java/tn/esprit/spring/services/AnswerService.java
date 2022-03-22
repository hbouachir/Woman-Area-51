package tn.esprit.spring.services;

import tn.esprit.spring.entities.Answer;

public interface AnswerService {
    Answer addAnswer(Answer answer, Long idQuestion);
    void deleteAnswer(Answer answer);
    Answer updateAnswer(Answer answer);

}
