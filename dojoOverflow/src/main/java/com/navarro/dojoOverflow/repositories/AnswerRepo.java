package com.navarro.dojoOverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.navarro.dojoOverflow.models.Answer;

public interface AnswerRepo extends CrudRepository<Answer, Long> {

	List<Answer> findAll();
	
	// find top 2 by order by ID descending
	List<Answer> findTop2ByOrderByIdDesc();
}
