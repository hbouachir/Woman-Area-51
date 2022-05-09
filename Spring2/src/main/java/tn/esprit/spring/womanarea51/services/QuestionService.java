package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Question;

public interface QuestionService {
    Question addQuestion(Question question, Long quizId);
    void deleteQuestion(Question question);
}
