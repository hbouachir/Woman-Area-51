package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Answer;

public interface AnswerService {
    Answer addAnswer(Answer answer, Long idQuestion);
    void deleteAnswer(Answer answer);
    Answer updateAnswer(Answer answer);

}
