package br.com.ptw.module.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ptw.geral.generic.model.Entidade;

/**
 * @author Ady Junior - 10/11/2014
 *
 */
@Entity(name="admin_perfil")
@Table(name="admin_perfil")
public class Perfil extends Entidade{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String nome;
	
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
