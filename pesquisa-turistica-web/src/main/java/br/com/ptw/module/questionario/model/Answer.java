package br.com.ptw.module.questionario.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ptw.geral.generic.model.Entidade;

@Table(name = "questionario_answer")
public class Answer extends Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String textAnswer;
	
	@ManyToOne
	private Question question;
	
	@ManyToOne
	private Option option;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
