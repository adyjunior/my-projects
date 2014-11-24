package br.com.ptw.module.questionario.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ptw.geral.generic.model.Entidade;

@Table(name="questionario_form")
public class Form extends Entidade{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String descricao;
	private Date data;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}
