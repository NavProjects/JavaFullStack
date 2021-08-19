package com.navarro.dojoOverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.navarro.dojoOverflow.models.Tag;

public interface TagRepo extends CrudRepository<Tag, Long> {
	
	List<Tag> findAll();

}
