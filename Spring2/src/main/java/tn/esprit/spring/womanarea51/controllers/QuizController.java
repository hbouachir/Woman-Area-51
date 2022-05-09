package tn.esprit.spring.womanarea51.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.womanarea51.entities.*;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.AnswerService;
import tn.esprit.spring.womanarea51.services.QuestionService;
import tn.esprit.spring.womanarea51.services.QuizService;

@RestController
@RequestMapping("/api")
public class QuizController {
    @Autowired
    QuizService qs;
    @Autowired
    QuestionService qqs;
    @Autowired
    AnswerService as;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/Course/{courseId}/getQuiz/")
    @JsonView(CourseView.Extra.class)
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public Quiz getQuiz(@PathVariable Long courseId){
        return qs.getQuiz(courseId);
    }

    @GetMapping("/Course/{courseId}/viewQuiz/")
    @PreAuthorize("hasRole('USER')")
    @JsonView(CourseView.More.class)
    public Quiz getQuizV(@PathVariable Long courseId){
        return qs.getQuiz(courseId);
    }

    @PostMapping("/Course/{courseId}/setQuiz")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public Quiz updateCourse(@PathVariable Long courseId, @RequestBody Quiz q, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return qs.addQuiz(q, courseId, U);
    }

    @PostMapping("/Course/{courseId}/submitQuizAnswers")
    @PreAuthorize("hasRole('USER')")
    public int addAnswer(@RequestBody Quiz q,@PathVariable Long courseId, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return qs.submitQuizAnswers(q,U.getId());
    }
}
