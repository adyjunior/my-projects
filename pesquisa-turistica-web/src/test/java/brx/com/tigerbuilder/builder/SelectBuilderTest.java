package brx.com.tigerbuilder.builder;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ptw.module.questionario.model.Answer;

public class SelectBuilderTest {

	@Test
	public void checkIfListAllIsWork() {
		String sql = SelectBuilder.forClass(Answer.class)
//			.addProjection(Projection.property("nome"))
//			.addProjection(Projection.property("telefone"))
//			.addProjection(Projection.property("email"))
//			.addProjection(Projection.property("estsaude").addAllColumns())
//			.join(Option.class)
			.toString();
		
		System.out.println(sql);
	}
	
	@Test
	public void checkIfLimitIsWork() {
		String sqlCreated = SelectBuilder.forClass(Answer.class)
			.addLimit(10).toString();
		
		//String sqlCorrect = "select * from questionario_answer limit 10 offset 5";
		String sqlCorrect = "select * from questionario_answer  limit 10 ";
		
		System.out.println(sqlCreated);
		System.out.println(sqlCorrect);
		
		Assert.assertEquals(sqlCreated, sqlCorrect);
	}
	
	@Test
	public void checkIfLimitAndOffSetIsWork() {
		String sqlCreated = SelectBuilder.forClass(Answer.class)
				.addLimit(10, 3)
				.toString();
		
		String sqlCorrect = "select * from questionario_answer  limit 10 offset 3 ";
		
		System.out.println(sqlCreated);
		System.out.println(sqlCorrect);
		
		Assert.assertEquals(sqlCreated, sqlCorrect);
	}
	
	
}
