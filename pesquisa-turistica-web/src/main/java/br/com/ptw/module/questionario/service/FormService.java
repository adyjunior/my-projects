package br.com.ptw.module.questionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ptw.module.questionario.dao.FormDAO;
import br.com.ptw.module.questionario.model.Form;

@Service
public class FormService {

	@Autowired
	private FormDAO formDAO;
	
	public List<Form> listWithPagination(Integer numberPage, Integer numberPerPage) {
		return formDAO.listWithPagination(numberPage, numberPerPage);
	}
}
