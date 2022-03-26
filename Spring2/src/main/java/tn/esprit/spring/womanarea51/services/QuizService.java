package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Quiz;

public interface QuizService {
    Quiz addQuiz(Quiz quiz, Long courseId);
    int submitQuizAnswers(Quiz quiz, Long userId);

}
