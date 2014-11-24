package br.com.ptw.module.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ptw.module.admin.model.Perfil;
import br.com.ptw.module.admin.service.PerfilService;

/**
 * @author Ady Junior - 10/11/2014
 *
 */
@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@RequestMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {
		List<Perfil> perfis = perfilService.listar();
		
		return new ResponseEntity<List<Perfil>>(perfis, HttpStatus.OK);
	}
	
}
