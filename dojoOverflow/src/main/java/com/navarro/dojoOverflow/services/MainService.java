package com.navarro.dojoOverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navarro.dojoOverflow.models.Answer;
import com.navarro.dojoOverflow.models.Question;
import com.navarro.dojoOverflow.models.Tag;
import com.navarro.dojoOverflow.repositories.AnswerRepo;
import com.navarro.dojoOverflow.repositories.QuestionRepo;
import com.navarro.dojoOverflow.repositories.TagRepo;

@Service
public class MainService {

	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private AnswerRepo answerRepo;
	
	@Autowired
	private TagRepo tagRepo;
	
	
	//QUESTION Service
	// GET
	// GET ALL
	public List<Question> allQuestions(){
		return questionRepo.findAll();
	}
	// GET ONE
	public Question getQuest(Long id) {
		Optional<Question> optionQuest = questionRepo.findById(id);
		if(optionQuest.isPresent()) {
			return optionQuest.get();
		}
		return null;
	}
	// POST
	// create Question
	public Question createQ(Question question) {
		return questionRepo.save(question);
	}
	//PUT
	// create Question
	public Question updateQuestion(Question question) {
		return questionRepo.save(question);
	}
	
	
	
	//ANSWER Service
	// GET
	// GET ALL
	public List<Answer> allAnswers(){
		return answerRepo.findAll();
	}
	// GET TOP 2
	public List<Answer> topTwoAnswers(){
		return answerRepo.findTop2ByOrderByIdDesc();
	}
	// POST
	// create Answer
	public Answer createA(Answer answer) {
		System.out.println(answer.getId());
		return answerRepo.save(answer);
	}
	// PUT
	public Answer updateAnswer(Answer answer) {
		return answerRepo.save(answer);
	}
	
	
	
	//TAG Service
	// GET
	// GET ALL
	public List<Tag> allTag(){
		return tagRepo.findAll();
	}
	
	
	// POST
	// create Question
	public Tag createT(Tag tag) {
		return tagRepo.save(tag);
	}
	//PUT
	public Tag updateTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
}
