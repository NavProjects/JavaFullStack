package com.navarro.loginReg.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.navarro.loginReg.models.User;

public interface UserRep extends CrudRepository<User, Long> {
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	// CHECKS FOR EMAIL RETURNS BOOLEAN
	boolean existsByEmail(String email);
	
	
}
