package brx.com.tigerbuilder.builder;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ptw.module.admin.model.Usuario;
import br.com.ptw.module.questionario.model.Option;
import br.com.ptw.module.questionario.model.Question;
import brx.com.tigerbuilder.builder.InsertBuilder;

public class InsertBuilderTest{

	@Test
	public void insertBasicTest() {
		String queryBuilded = InsertBuilder.newInstance(Usuario.class).toString();
		String queryCorrect = "insert into admin_usuario(nome, login, email, senha, telefone, dataNascimento, dataCriacao, status)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println(queryBuilded);
		System.out.println(queryCorrect);
		System.out.println();
		
		Assert.assertTrue(queryBuilded.equalsIgnoreCase(queryCorrect));
	}
	
	@Test
	public void insertWithTableVinculateTest() {
		String queryBuilded = InsertBuilder.newInstance(Option.class).toString();
		String queryCorrect = "insert into questionario_option(description, question_id, answer_id) values (?, ?, ?)";
		
		System.out.println(queryBuilded);
		System.out.println(queryCorrect);
		System.out.println();
		
		Assert.assertTrue(queryBuilded.equalsIgnoreCase(queryCorrect));
	}
	
	@Test
	public void insertWithTableVinculateOtherTest() {
		String queryBuilded = InsertBuilder.newInstance(Question.class).toString();
		String queryCorrect = "insert into questionario_question(title, description, helpText, isRequired, form_id) values (?, ?, ?, ?, ?)";
		
		System.out.println(queryBuilded);
		System.out.println(queryCorrect);
		System.out.println();
		
		Assert.assertTrue(queryBuilded.equalsIgnoreCase(queryCorrect));
	}
	
}
