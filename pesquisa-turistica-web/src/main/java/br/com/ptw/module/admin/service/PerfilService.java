package br.com.ptw.module.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ptw.geral.generic.dao.GenericDAO;
import br.com.ptw.geral.generic.service.GenericService;
import br.com.ptw.module.admin.dao.PerfilDAO;
import br.com.ptw.module.admin.model.Perfil;

/**
 * @author Ady Junior - 10/11/2014
 *
 */
@Service
public class PerfilService extends GenericService<Perfil>{

	@Autowired
	private PerfilDAO perfilDAO;
	
	@Override
	public GenericDAO<Perfil> getDao() {
		return perfilDAO;
	}

}
