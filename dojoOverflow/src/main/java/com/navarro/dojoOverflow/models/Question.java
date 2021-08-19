package com.navarro.dojoOverflow.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="questions")
public class Question{
	
	
	//Member Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Can not be blank")
    private String question;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(updatable=false)
    private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
	
	
	// these are GETTERS and SETTERS
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    
    //tag table association
    //MANY TO MANY
    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
		name = "questions_tags",
		joinColumns = @JoinColumn(name = "question_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
		)
    private List<Tag> tags;
//    private List<Tag> tags = new ArrayList<Tag>();
    
	// answer table association 
	//ONE TO MANY
	@OneToMany(mappedBy="question", fetch=FetchType.LAZY)
	private List<Answer> answers;
	
	
	// Constructor
	public Question() {

	}
	
	
	// GETTERS and SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
