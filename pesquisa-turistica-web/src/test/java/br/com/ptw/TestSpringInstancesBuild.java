package br.com.ptw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringInstancesBuild {

	private static final String FILE_SPRING_CONFIG = "spring-for-tests-config.xml";

	@SuppressWarnings("resource")
	public static <T> T getBean(Class<T> clazz) {
		ApplicationContext context = new ClassPathXmlApplicationContext(FILE_SPRING_CONFIG);
		return context.getBean(clazz);
	}	
}
