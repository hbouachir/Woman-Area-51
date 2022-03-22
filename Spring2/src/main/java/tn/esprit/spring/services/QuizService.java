package tn.esprit.spring.services;

import tn.esprit.spring.entities.Quiz;

public interface QuizService {
    Quiz addQuiz(Quiz quiz, Long courseId);
    int submitQuizAnswers(Quiz quiz, Long userId);

}
