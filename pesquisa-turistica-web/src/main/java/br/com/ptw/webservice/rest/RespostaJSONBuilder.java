package br.com.ptw.webservice.rest;

import java.util.List;

import br.com.ptw.webservice.rest.enums.EnumStatusRespostaJSON;

public class RespostaJSONBuilder {

	private RespostaJSON resposta = new RespostaJSON();
	
	private RespostaJSONBuilder(){
	}
	
	public static RespostaJSONBuilder newInstance() {
		return new RespostaJSONBuilder();
	}
	
	public RespostaJSONBuilder status(String status) {
		resposta.setStatus(status);
		return this;
	}
	
	public RespostaJSONBuilder msgErro(String msgErro) {
		resposta.setMensagemErro(msgErro);
		return this;
	}
	
	public RespostaJSONBuilder codigosEntrevistas(List<String> codigosEntrevistas) {
		resposta.setCodigosEntrevistas(codigosEntrevistas);
		return this;
	}
	
	public RespostaJSON build() {
		return resposta;
	}
	
	
	public static RespostaJSON buildMsgErroWithString(String msg) {
		RespostaJSON respostaJSON = RespostaJSONBuilder.newInstance()
				.status(EnumStatusRespostaJSON.ERROR.toString())
				.msgErro(msg)
				.build();
		
		return respostaJSON;
	}
	
	public static RespostaJSON buildMsgOkWithCodigos(List<String> codigos) {
		RespostaJSON respostaJSON = RespostaJSONBuilder.newInstance()
				.status(EnumStatusRespostaJSON.OK.toString())
				.codigosEntrevistas(codigos)
				.build();
		
		return respostaJSON;
	}
	
}
