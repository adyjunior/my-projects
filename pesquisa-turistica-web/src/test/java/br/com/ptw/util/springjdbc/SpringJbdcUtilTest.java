package br.com.ptw.util.springjdbc;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ptw.module.questionario.model.Answer;
import br.com.ptw.module.questionario.model.Option;
import br.com.ptw.module.questionario.model.Question;

public class SpringJbdcUtilTest {

	@Test
	public void valuesGetOfEntityAreCorrects() {
		Answer answer = new Answer();
		answer.setId(4645564L);
		Question question = new Question();
		question.setId(111111L);
		
		Option option = new Option();
		option.setAnswer(answer);
		option.setDescription("TESTANDO");
		option.setQuestion(question);
		
		Object[] objects = SpringJdbcUtil.getValuesEntityForInsert(option);
	
		for (Object object : objects) {
			System.out.println(object);
		}
		
		Assert.assertNotNull(option);
	}
	
}
