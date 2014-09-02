package br.com.ptw.geral.context;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Classe responsável por setar o applicationContext ao inicializar a aplicação
 * e fornecer acesso ao container do spring que gerencia os beans.
 * 
 * @author Hylston Natann
 * @version 1.0
 * 
 */
public class SpringCtxHolder implements ApplicationContextAware {

	private static Logger logger = Logger.getLogger(SpringCtxHolder.class);
	protected static ApplicationContext applicationContext;

	/**
	 * Método responsável por setar o applicationContext. Através da
	 * implementação da classe ApplicationContextAware, o método é invocado
	 * quando a aplicação está sendo inicilizada.
	 * 
	 */
	public void setApplicationContext(ApplicationContext appCtx) throws BeansException {

		cleanApplicationContext();
		applicationContext = appCtx;
		logger.info("Inicializando o application Context do Spring");
	}

	public static ApplicationContext getApplicationContext() {

		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanAlias) {
		return (T) getApplicationContext().getBean(beanAlias);
	}

	public static <T> T getBean(Class<T> clazz) {
		String beanAlias =  StringUtils.uncapitalize(clazz.getSimpleName());
		return getBean(beanAlias);
	}

	public static String getRealPath() {
		String path = getApplicationContext().getClassLoader().getResource("").getPath();
//		return path.replace("/", "\\").substring(1, path.lastIndexOf("web") + 3);
		return path.replace("/", "\\").substring(1, path.lastIndexOf("WEB-INF"));
	}

	public static void cleanApplicationContext() {
		applicationContext = null;
	}
}