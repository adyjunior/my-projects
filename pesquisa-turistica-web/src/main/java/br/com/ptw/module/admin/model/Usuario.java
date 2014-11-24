package br.com.ptw.module.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.ptw.geral.generic.model.Entidade;
import br.com.ptw.module.admin.enums.EnumStatusUsuario;

/**
 * @author Ady Junior - 30/03/2014
 *
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name="admin_usuario")
@Table(name="admin_usuario")
public class Usuario extends Entidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(length=255)
	private String nome;
	
	@Column(length=100)
	private String login;
	
	@Column(length=100)
	private String email;
	
	@Column(length=50)
	private String senha;
	
	@Column(length=30)
	private String telefone;
	
	private Date dataNascimento;
	
	private Date dataCriacao;
	
	/**
	 * Alimentado por {@link EnumStatusUsuario}
	 */
	@Column(length=50)
	private String status;
	
	@Transient
	private String novaSenha;
	
	@Transient
	private String confirmarSenha;
	
	
	public Usuario(){
	}
	
	public Usuario(String user, String pass){
		this.login = user;
		this.senha = pass;
	}
	
	public Usuario(Long id){
		this.id = id;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Date getDataCriacao(){
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao){
		this.dataCriacao = dataCriacao;
	}
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
}
