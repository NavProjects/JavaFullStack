package com.navarro.loginReg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navarro.loginReg.models.User;
import com.navarro.loginReg.services.UserService;
import com.navarro.loginReg.validators.UserValidator;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator validator;
	
	
	// REGISTRATION
	// render registration
	@RequestMapping("/registration")
	public String registerForm(@ModelAttribute("user") User user) {
		return "registration.jsp";
	}
	
	
	//POST for registration
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, 
			BindingResult result, 
			HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "registration.jsp";
		}
		User u = userService.registerUser(user);
		session.setAttribute("userId", u.getId());
		return "redirect:/home";
		}

	
	// LOGIN
	// render login
	@RequestMapping("/login")
	public String login() {
		return "login.jsp";
	}

	
	// POST login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, 
			@RequestParam("password") String password, 
			Model model,
			HttpSession session,
			RedirectAttributes flash) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if(isAuthenticated) {
			User person = userService.findByEmail(email);
			session.setAttribute("userId", person.getId());
			return "redirect:/home";
		}
		else {
			flash.addFlashAttribute("error", "Invalid Credentials try again please");
			return "redirect:/login";
		}
	}

	
	// HOME
	// render home
	@RequestMapping("/home")
	public String home(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("userId");
		
		if(id != null) {
			User u = userService.findUserById(id);
			model.addAttribute("user", u);
			return "home.jsp";			
		}
		else {
			return "redirect:/login";
		}
	}
	
	
	//LOGUOT
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
