package com.project.quiz.project.Service;

import com.project.quiz.project.DAO.QuestionsDao;
import com.project.quiz.project.DAO.QuizDao;
import com.project.quiz.project.model.QuestionWrapper;
import com.project.quiz.project.model.Questions;
import com.project.quiz.project.model.Quiz;
import com.project.quiz.project.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionsDao questionsDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Questions> questions = questionsDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz = quizDao.findById(id);
       List<Questions> questionsFromDB = quiz.get().getQuestions();
       List<QuestionWrapper> questionForUser = new ArrayList<>();
       for(Questions q : questionsFromDB){
           QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getOption_1(), q.getOption_2(), q.getOption_3(), q.getOption_4(), q.getQuestionTitle());
           questionForUser.add(qw);
       }

       return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response: responses){
            if(response.getResponse().equals(questions.get(i).getCorrectAns()))
                right++;
            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
