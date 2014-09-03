package brx.com.tigerbuilder.builder;

import org.junit.Test;

import br.com.ptw.module.questionario.model.Answer;

public class SelectBuilderTest {

	
	@Test
	public void checkIfListAllIsWork() {
		String sql = SelectBuilder.newInstance(Answer.class)
//			.addProjection(Projection.property("nome"))
//			.addProjection(Projection.property("telefone"))
//			.addProjection(Projection.property("email"))
//			.addProjection(Projection.property("estsaude").addAllColumns())
//			.join(Option.class)
			.toString();
		
		System.out.println(sql);
	}
	
	
}
