package br.com.ptw.module.questionario.model;

import java.io.Serializable;

public class Option implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String description;
	
	private Question question;
	private Answer answer;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

}
