package tn.esprit.spring.womanarea51.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.entities.*;
import tn.esprit.spring.womanarea51.entities.Class;
import tn.esprit.spring.womanarea51.repositories.*;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService{
    @Autowired
    QuizRepository qr;
    @Autowired
    CourseRepository cr;
    @Autowired
    ClassRepository clr;
    @Autowired
    QuestionRepository qqr;
    @Autowired
    AnswerRepository ar;


    @Override
    public Quiz addQuiz(Quiz quiz, Long courseId, User U) {
        Course course = cr.findById(courseId).orElse(null);
        System.out.println(course.getInstructor().equals(U));
        System.out.println(U.getRoles().contains(ERole.ROLE_ADMIN));
       // if (course != null && (!course.getInstructor().equals(U) || !U.getRoles().contains(ERole.ROLE_ADMIN))) return null;
        if (course != null) {
            if (course.getQuiz() != null) {
                Quiz old_quiz = course.getQuiz();
                for (Question q : old_quiz.getQuestions()) {
                    for (Answer a : q.getAnswers()) {
                        ar.delete(a);
                    }
                    qqr.delete(q);
                }
                quiz.setQuizId(old_quiz.getQuizId());
            }else{
                quiz = qr.save(quiz);
                course.setQuiz(quiz);
                cr.save(course);
            }



                for(Question q: quiz.getQuestions()){
                    q.setQuiz(quiz);
                    Question qua = qqr.save(q);
                    for(Answer a: q.getAnswers()){
                        a.setQuestion(qua);
                        ar.save(a);
                    }
                }

            return quiz;
        }
        return null;

    }

    @Override
    public int submitQuizAnswers(Quiz quiz, Long userId) {
        Quiz quiz_orig = qr.findById(quiz.getQuizId()).orElse(null);
        final int[] correct = {0};
        final int[] wrong = {0};
        double score = 0.0;
        int final_score = 0;
        quiz_orig.getQuestions().forEach(question -> {
            quiz.getQuestions().forEach(q_question -> {
                if (q_question.getIdQuestion() == question.getIdQuestion()){
                    if (q_question.getAnswers().equals(question.getAnswers())) correct[0]+=1;
                    else wrong[0]+=1;
                }
            });
        });
        score = (correct[0]*1.0/(correct[0]+wrong[0]))*100;
        ClassKey ck = new ClassKey(userId, quiz_orig.getCourse().getCourseId());
        Class quiz_class = clr.findById(ck).orElse(null);
        final_score = (int) score;
        if (quiz_class.getScore() < final_score){
            quiz_class.setScore(final_score);
            if (quiz_orig.getRequiredToSuccess() <= final_score) quiz_class.setStatus("Passed");
            clr.save(quiz_class);
        }
        return final_score;
    }

    @Override
    public Quiz getQuiz(Long courseId) {
        Quiz quiz = cr.findById(courseId).orElse(null).getQuiz();
        return quiz;
    }
}
