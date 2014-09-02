package br.com.ptw.util;

import br.com.ptw.module.admin.model.Usuario;
import br.com.ptw.module.questionario.model.Form;
import br.com.ptw.module.questionario.model.Question;
import brx.com.tigerbuilder.builder.CreateBuilder;

public class CriacaoTabelas {

	public static void main(String[] args) {
		criarScriptTabelas();
	}
	
	private static void criarScriptTabelas() {
		
		System.out.println(CreateBuilder.newInstance(Usuario.class).format().toString());
		System.out.println();
		System.out.println(CreateBuilder.newInstance(Form.class).format().toString());
		System.out.println();
		System.out.println(CreateBuilder.newInstance(Question.class).format().toString());
		
	}
	
	
}
