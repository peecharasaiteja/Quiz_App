package com.peechara.quizapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.peechara.quizapp.model.Question;
import com.peechara.quizapp.service.Questionservice;


@RestController
@RequestMapping("questions")
public class QuestionController {	
	@Autowired
	Questionservice questionservice;
@GetMapping("Allquestions")
public ResponseEntity<List<Question>> getAllQuestions() {
	return questionservice.getAllQuestions();
}
@GetMapping("category/{category}")
public ResponseEntity<List<Question>> getQuestionsbycategory(@PathVariable String category){
	return questionservice.getQuestionsbycategory(category);
}
@PostMapping("add")
public ResponseEntity<String> addQuestion(@RequestBody Question question) {
	return questionservice.addQuestion(question);	
}
}
