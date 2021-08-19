package com.navarro.loginReg.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.navarro.loginReg.models.User;
import com.navarro.loginReg.services.UserService;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService userservice;
	
	// 1 
	// 1 supports(Class<?>): Specifies that a instance of the User Domain Model can be validated with this custom validator
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	// 2
	// 2 validate(Object, Errors): Creating our custom validation. We can add errors via .rejectValue(String, String).
	@Override
	public void validate(Object target,Errors errors) {
		User user = (User) target;
		
		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			// 3
			// 3 .rejectValue(String, String): the first argument is the member variable of our Domain model that we are validating. The second argument is a code for us to use to set an error message.
			errors.rejectValue("passwordConfirmation", "Match");
		}
		
		
		if (userservice.existEmail(user.getEmail())) {
			errors.rejectValue("email", "Verify");
		}
	}
}
