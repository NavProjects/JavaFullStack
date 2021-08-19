package com.navarro.dojoOverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.navarro.dojoOverflow.models.Question;

public interface QuestionRepo extends CrudRepository<Question, Long> {
	
	List<Question> findAll();
	
}
