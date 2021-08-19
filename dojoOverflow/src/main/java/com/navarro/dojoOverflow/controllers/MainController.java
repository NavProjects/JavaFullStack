package com.navarro.dojoOverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.navarro.dojoOverflow.models.Answer;
import com.navarro.dojoOverflow.models.Question;
import com.navarro.dojoOverflow.models.Tag;
import com.navarro.dojoOverflow.services.MainService;

@Controller
public class MainController {

	@Autowired
	private MainService service;
	
	//RENDER dash board
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("quest", service.allQuestions());
		return "index.jsp";
	}
	//RENDER create question and tag route
	@GetMapping("/questions/new")
	// EVERY TIME YOU USE FORM FORM YOU NEED AN EMPTY MODEL ATTRIBUTE
	public String redQuesTag(@ModelAttribute("quest") Question question) {
		return "addQuestion.jsp";
	}
	// POST add question and tag
	@PostMapping("questions/new")
	public String createQT(@Valid
			@ModelAttribute("quest") Question question,
			BindingResult bindingResult 
			,@RequestParam("subject")String subject
			) {
		if(bindingResult.hasErrors()) {
			return "addQuestion.jsp";
		}else {
			Question ques = service.createQ(question);
			Tag ta = service.createT(new Tag(subject));
//			ques.setTags(ta);
//			service.updateQuestion(ques);
			
			
			List<Tag> tags;
			if(ques.getTags() == null) {
				tags = new ArrayList<Tag>();
			}
			else {	
				tags = ques.getTags();
			}
			tags.add(ta);
			ques.setTags(tags);
			service.updateQuestion(ques);
			
			
			return "redirect:/";
		}
	}
	//RENDER answer
	@GetMapping("/questions/{id}")
	// EVERY TIME YOU USE FORM FORM YOU NEED AN EMPTY MODEL ATTRIBUTE
	public String showAnswers(@ModelAttribute("ans") Answer answer,
			@PathVariable("id") Long id, Model model) {
		Question thisQuestion = service.getQuest(id);
		if(thisQuestion == null) {
			return "redirect:/";
		}
		else {
			model.addAttribute("question", thisQuestion);
			
			return "addAnswer.jsp";
		}
	}
	@PostMapping("/questions/{id}")
	public String createAns(@Valid @ModelAttribute("ans") Answer answer,
			BindingResult bindingResult,
			@PathVariable("id") Long id, Model model) {

		if(bindingResult.hasErrors()) {
			Question thisQuestion = service.getQuest(id);
			model.addAttribute("question", thisQuestion);
			return "addAnswer.jsp";
		}
		else {
			service.createA(answer);
			return "redirect:/questions/" + id;
		}
	}
}
