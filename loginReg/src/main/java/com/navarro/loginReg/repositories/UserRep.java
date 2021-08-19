package com.navarro.loginReg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.navarro.loginReg.models.User;

public interface UserRep extends CrudRepository<User, Long> {
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	Optional<User> findUserByEmail(String email);
}
