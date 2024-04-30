package com.peechara.quizapp.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.peechara.quizapp.dao.Questiondao;
import com.peechara.quizapp.model.Question;

@Service
public class Questionservice {
	@Autowired
	Questiondao questiondao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return  new ResponseEntity<>(questiondao.findAll(),HttpStatus.OK); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		 
	}

	public ResponseEntity<List<Question>> getQuestionsbycategory(String category) {
	    try{
		return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
		
		}
	    catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
		questiondao.save(question);
		return new ResponseEntity<>("sucess",HttpStatus.CREATED);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return new ResponseEntity<>("unsucessful",HttpStatus.BAD_REQUEST);

}
}
