package com.peechara.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peechara.quizapp.dao.Questiondao;
import com.peechara.quizapp.dao.Quizdao;
import com.peechara.quizapp.model.Question;
import com.peechara.quizapp.model.QuestionWrapper;
import com.peechara.quizapp.model.Quiz;
import com.peechara.quizapp.model.Response;

@Service
public class QuizService {
	@Autowired
	Quizdao quizdao;
	@Autowired
	Questiondao questiondao;

	public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {
		List<Question> questions = questiondao.findRandomQuestionsByCategory(category,numQuestions);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizdao.save(quiz);
		return new ResponseEntity<>("sucess",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizdao.findById(id);
		List<Question> questionfromdb = quiz.get().getQuestions();
		List<QuestionWrapper> questionforuser = new ArrayList<>();
		for(Question q : questionfromdb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionforuser.add(qw);
		}	
		return new ResponseEntity<>(questionforuser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
	Quiz quiz =quizdao.findById(id).get();
	List<Question> questions = quiz.getQuestions();
	int right =0;
	int i=0;
		for (Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
			right++;
			}
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
