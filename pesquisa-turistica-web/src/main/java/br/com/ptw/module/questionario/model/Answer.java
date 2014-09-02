package br.com.ptw.module.questionario.model;

import java.io.Serializable;

public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String textAnswer;
	
	private Question question;
	private Option option;
	
	public String getTextAnswer() {
		return textAnswer;
	}
	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Option getOption() {
		return option;
	}
	public void setOption(Option option) {
		this.option = option;
	}
	
}
