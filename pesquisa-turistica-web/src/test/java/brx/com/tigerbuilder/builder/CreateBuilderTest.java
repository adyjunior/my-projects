package brx.com.tigerbuilder.builder;

import org.junit.Test;

import br.com.ptw.module.admin.model.Perfil;

public class CreateBuilderTest {

	@Test
	public void writeScriptAllTables() {
		System.out.println(CreateBuilder.newInstance(Perfil.class).format().createPostgresSequence().toString());
//		System.out.println(CreateBuilder.newInstance(Form.class).format().createPostgresSequence().toString());
//		System.out.println(CreateBuilder.newInstance(Question.class).format().createPostgresSequence().toString());
//		System.out.println(CreateBuilder.newInstance(Option.class).format().createPostgresSequence().toString());
//		System.out.println(CreateBuilder.newInstance(Answer.class).format().createPostgresSequence().toString());
	}
	
}
