package br.com.ptw.geral.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * @author Ady Junior - 30/03/2014
 *
 */
public class InicializacaoListener implements ServletContextListener{

	private static Logger logger = Logger.getLogger(InicializacaoListener.class.getSimpleName());
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		logger.info("INICIALIZACAO DO CONTEXTO !!!");
		//ConnectionJPA.getConnection();
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("FINALIZACAO DO CONTEXTO !!!");		
		//JPAUtil.closeEntityManagerFactory();
	}


}
