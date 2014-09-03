package brx.com.tigerbuilder.builder;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ptw.module.admin.model.Usuario;
import br.com.ptw.module.questionario.model.Option;
import br.com.ptw.module.questionario.model.Question;

public class UpdateBuilderTest {

	@Test
	public void checkIfUpdateBasicIsWork() {
		String queryBuilded = UpdateBuilder.newInstance(Usuario.class).toString();
		String queryCorrect = "update admin_usuario set id=?, nome=?, login=?, email=?, senha=?, telefone=?, dataNascimento=?, dataCriacao=?, status=?";
		
		System.out.println(queryBuilded);
		System.out.println(queryCorrect);
		System.out.println();
		
		Assert.assertTrue(queryBuilded.equalsIgnoreCase(queryCorrect));
	}
	
	@Test
	public void checkIfUpdateWithTableVinculateIsWork() {
		String queryBuilded = UpdateBuilder.newInstance(Option.class).toString();
		String queryCorrect = "update questionario_option set id=?, description=?, question_id=?, answer_id=?";
		
		System.out.println(queryBuilded);
		System.out.println(queryCorrect);
		System.out.println();
		
		Assert.assertTrue(queryBuilded.equalsIgnoreCase(queryCorrect));
	}
	
	@Test
	public void checkIfUpdateWithTableVinculateIsWorkInOtherCase() {
		String queryBuilded = UpdateBuilder.newInstance(Question.class).toString();
		String queryCorrect = "update questionario_question set id=?, title=?, description=?, helpText=?, isRequired=?, form_id=?";
		
		System.out.println(queryBuilded);
		System.out.println(queryCorrect);
		System.out.println();
		
		Assert.assertTrue(queryBuilded.equalsIgnoreCase(queryCorrect));
	}
	
	
}
