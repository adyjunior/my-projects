package br.com.ptw.module.questionario.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ptw.geral.generic.model.Entidade;

@Table(name = "questionario_option")
public class Option extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String description;
	
	@ManyToOne
	private Question question;
	
	@ManyToOne
	private Answer answer;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
