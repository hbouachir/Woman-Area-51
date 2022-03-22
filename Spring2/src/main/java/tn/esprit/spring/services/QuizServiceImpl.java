package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.ClassKey;
import tn.esprit.spring.entities.Class;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.repositories.ClassRepository;
import tn.esprit.spring.repositories.CourseRepository;
import tn.esprit.spring.repositories.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService{
    @Autowired
    QuizRepository qr;
    @Autowired
    CourseRepository cr;
    @Autowired
    ClassRepository clr;


    @Override
    public Quiz addQuiz(Quiz quiz, Long courseId) {
        Course course = cr.findById(courseId).orElse(null);
        quiz.setCourse(course);
        quiz = qr.save(quiz);
        course.setQuiz(quiz);
        cr.save(course);
        return quiz;
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
}
