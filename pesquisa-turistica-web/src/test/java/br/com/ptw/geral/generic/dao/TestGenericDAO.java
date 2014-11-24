package br.com.ptw.geral.generic.dao;

import java.util.Date;

import org.junit.Test;

import br.com.ptw.TestSpringInstancesBuild;
import br.com.ptw.module.questionario.dao.FormDAO;
import br.com.ptw.module.questionario.model.Form;


public class TestGenericDAO {

	@Test
	public void insertDataIntoFormTable() {
		
		FormDAO dao = TestSpringInstancesBuild.getBean(FormDAO.class);
		for (int i = 2; i < 280; i++) {
			Form form = new Form();
			form.setDescricao("Form " + i);
			form.setData(new Date());
			dao.insert(form);
		}
		
	}
	
}
