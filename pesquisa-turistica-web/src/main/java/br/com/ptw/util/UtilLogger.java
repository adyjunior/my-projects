package br.com.ptw.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class UtilLogger {

	private static Logger logger = Logger.getLogger(UtilLogger.class);
	
	private final static String pattern = "%-6p %c - %m :::[%t] %d%n";
	private static String caminhoFisico;
	
	public static void configurarLog4j(ServletContext servletContext) {
		caminhoFisico = servletContext.getRealPath("/");
		try {
			if(PropriedadesSistema.URL_SISTEMA.contains(":8080")) {
				configurarLog4jParaDesenvolvimento();
			} else {
				configurarLog4jParaProducao();
			}
		} catch (Exception e) {
			logger.error("Erro ao configurar log.", e);
		}
	}
	
	private static void configurarLog4jParaDesenvolvimento() {
		// Criando um FileAppender
		ConsoleAppender appender = new ConsoleAppender();
		
		// Criando um layout para o arquivo de log
		PatternLayout patternLayout = new PatternLayout();
		patternLayout.setConversionPattern(pattern);
		appender.setLayout(patternLayout);
		
		// Efetiva as altera��es
		appender.activateOptions();
		
		//remove todos appenders
		Logger.getRootLogger().removeAllAppenders();
		
		// Adiciona o file appender
		Logger.getRootLogger().addAppender(appender);
	}
	
	private static void configurarLog4jParaProducao() {
		String pathPasta = caminhoFisico+"WEB-INF/logs/";

		File f = new File(pathPasta);
		if( ! f.exists()) {
			f.mkdir();
		}
		
		// Caminho onde o arquivo ser� gravado
		String pathArquivoLog = pathPasta + "logapp.log";
		
		// Criando um FileAppender
		DailyRollingFileAppender appender = new DailyRollingFileAppender();
		appender.setAppend(true);
		appender.setFile(pathArquivoLog);
		appender.setDatePattern(".yyyy-MM-dd");
		
		// Criando um layout para o arquivo de log
		PatternLayout patternLayout = new PatternLayout();
		patternLayout.setConversionPattern(pattern);
		appender.setLayout(patternLayout);
		
		// Efetiva as altera��es
		appender.activateOptions();
		
		//remove todos appenders
		Logger.getRootLogger().removeAllAppenders();
		
		// Adiciona o file appender
		Logger.getRootLogger().addAppender(appender);
	}
	
	
}
