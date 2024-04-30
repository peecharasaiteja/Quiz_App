package com.peechara.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peechara.quizapp.model.Quiz;

public interface Quizdao extends JpaRepository<Quiz,Integer>{

}
