package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Quiz;
import tn.esprit.spring.womanarea51.entities.User;

public interface QuizService {
    Quiz addQuiz(Quiz quiz, Long courseId, User U);
    int submitQuizAnswers(Quiz quiz, Long userId);
    Quiz getQuiz(Long courseId);

}
