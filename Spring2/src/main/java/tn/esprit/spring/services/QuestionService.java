package tn.esprit.spring.services;

import tn.esprit.spring.entities.Question;

public interface QuestionService {
    Question addQuestion(Question question, Long quizId);
    void deleteQuestion(Question question);
}
