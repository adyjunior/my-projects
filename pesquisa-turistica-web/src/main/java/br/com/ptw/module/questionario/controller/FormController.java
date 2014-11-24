package br.com.ptw.module.questionario.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ptw.module.questionario.model.Form;
import br.com.ptw.module.questionario.service.FormService;

/**
 * @author Ady Junior - 16/09/2014
 *
 */
@Controller
public class FormController {

	@Autowired
	private FormService formService;
	
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAll(@RequestParam int numberPage, @RequestParam int numberPerPage, Locale locale) {
    	List<Form> list = formService.listWithPagination(numberPage, numberPerPage);
        return new ResponseEntity<List<Form>>(list, HttpStatus.OK);
    }
	
}
