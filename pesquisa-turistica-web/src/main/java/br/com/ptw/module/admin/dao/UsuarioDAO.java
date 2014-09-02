package br.com.ptw.module.admin.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.ptw.geral.generic.dao.GenericDAO;
import br.com.ptw.module.admin.model.Usuario;

/**
 * @author Ady Junior - 30/03/2014
 *
 */
@Repository
public class UsuarioDAO extends GenericDAO<Usuario>{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
}
