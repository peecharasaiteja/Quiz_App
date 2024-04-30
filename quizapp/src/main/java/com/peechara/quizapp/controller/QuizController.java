package com.peechara.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peechara.quizapp.model.Question;
import com.peechara.quizapp.model.QuestionWrapper;
import com.peechara.quizapp.model.Quiz;
import com.peechara.quizapp.model.Response;
import com.peechara.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService quizservice;
	@PostMapping("create")
	public ResponseEntity<String> CreateQuiz(@RequestParam String category,@RequestParam int numQuestions,@RequestParam String title){
	
     return quizservice.createQuiz(category,numQuestions,title);
     
}
	@GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
    return quizservice.getQuizQuestions(id);
}
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
		return quizservice.calculateResult(id,responses);
} 
}
