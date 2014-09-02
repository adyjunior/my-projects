package br.com.ptw.webservice.rest;

import java.util.List;


public class RespostaJSON {

	private String status;
	
	private String mensagemErro;
	
	private List<String> codigosEntrevistas;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getCodigosEntrevistas() {
		return codigosEntrevistas;
	}

	public void setCodigosEntrevistas(List<String> codigosEntrevistas) {
		this.codigosEntrevistas = codigosEntrevistas;
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}
	
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
}
