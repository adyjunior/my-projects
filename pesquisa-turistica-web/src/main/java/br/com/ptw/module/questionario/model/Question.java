package br.com.ptw.module.questionario.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.ptw.module.questionario.enums.QuestionType;

@Table(name="questionario_question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String title;
	private String description;
	private String helpText;
	
	@Transient
	private QuestionType questionType;
	private boolean isRequired;
	
	@ManyToOne
	private Form form;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHelpText() {
		return helpText;
	}
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}
	public QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}

}
