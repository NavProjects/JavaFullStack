package com.navarro.loginReg.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.navarro.loginReg.models.User;
import com.navarro.loginReg.repositories.UserRep;

@Service
public class UserService {
	
	@Autowired
	private UserRep userRep;
	
	// POST
	// Register User and hash password
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRep.save(user);
	}
	
//	 validate user by email
	public boolean existEmail(String email) {
		return userRep.existsByEmail(email);
	}
	//GET
	// find user by email 
	public User findByEmail(String email) {
		return userRep.findByEmail(email);
	}
	
	//GET
	// find user by id
	public User findUserById(Long id) {
		Optional<User> u = userRep.findById(id);
		
		if(u.isPresent()) {
			return u.get();
		}else {
			return null;
		}
	}
	
	
	// authenticate user
	public boolean authenticateUser(String email, String password) {
		// find user by email first
		User user = userRep.findByEmail(email);
		// if nothing is found return false
		if(user == null) {
			return false;
		}else {
			// if it matches return true otherwise return false
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
	}
}
