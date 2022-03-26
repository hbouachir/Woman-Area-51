package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.womanarea51.entities.Answer;
import tn.esprit.spring.womanarea51.entities.Question;
import tn.esprit.spring.womanarea51.entities.Quiz;
import tn.esprit.spring.womanarea51.services.AnswerService;
import tn.esprit.spring.womanarea51.services.QuestionService;
import tn.esprit.spring.womanarea51.services.QuizService;

@RestController
public class QuizController {
    @Autowired
    QuizService qs;
    @Autowired
    QuestionService qqs;
    @Autowired
    AnswerService as;



    @PostMapping("/addQuiz/{courseId}")
    public Quiz updateCourse(@PathVariable Long courseId, @RequestBody Quiz q){
        return qs.addQuiz(q, courseId);
    }

    @PostMapping("/addQuiz/addQuestion/{quizId}")
    public Question addQuestion(@PathVariable Long quizId, @RequestBody Question q){
        return qqs.addQuestion(q, quizId);
    }
    @PostMapping("/addQuiz/addQuestion/addAnswer/{questionId}")
    public Answer addAnswer(@PathVariable Long questionId, @RequestBody Answer a){
        System.out.println(a.getCorrect());
        return as.addAnswer(a, questionId);
    }
    @PostMapping("/submitQuizAnswers/")
    public int addAnswer(@RequestBody Quiz q){
        Long userId = 1L;
        return qs.submitQuizAnswers(q,userId);
    }
}
