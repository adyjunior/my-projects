package br.com.ptw.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;


public class PropriedadesSistema {
	static Logger logger = Logger.getLogger(PropriedadesSistema.class.getSimpleName());

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	
	public static String NOME_CLIENTE = getProperty("cliente.nome");
	
	public static String SMTP_SERVER = getProperty("smtp.server");
	
	public static String SMTP_PORT = getProperty("smtp.port");
	
	public static String USERNAME = getProperty("username");
	
	public static String PASSWORD = getProperty("password");
	
	public static String EMAIL_ERRORS = getProperty("email.errors");
	
	public static String URL_SISTEMA = getProperty("url.sistema");
	
	//public static String PASTA_ABSOLUTO_PROJETO = getProperty("path.absoluto.projeto");
	
	private static String getProperty(String property) {
		try {
			return resourceBundle.getString(property);
		} catch (Exception e) {
			logger.error("Propiedade " + property + " nao encontrada", e);
		}
		return null;
	}

}
