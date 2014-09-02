package br.com.ptw.module.questionario.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ptw.geral.generic.dao.GenericDAO;
import br.com.ptw.module.questionario.model.Question;

@Repository
public class QuestionDAO extends GenericDAO<Question>{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	public void save(Question question) {
		if (question == null) {
			throw new RuntimeException("Informe a mercadoria para salvar!");
		}
		if (question.getId() == null) {
			jdbcTemplate.update("SQL", 
					new Object[] { question.getTitle(), question.getHelpText(), question.isRequired()});
		} else {
			jdbcTemplate.update("sql", 
					new Object[] { question.getTitle(), question.getHelpText(), question.isRequired()});
		}
	}

	@Transactional
	public void remove(Question question) {
		if (question == null || question.getId() == null) {
			throw new RuntimeException("Informe a mercadoria para exclusão!");
		}

		jdbcTemplate.update("sql", new Object[] { question.getId() });
	}

	public Question findById(Integer id) {
		if (id == null || id.intValue() <= 0) {
			throw new RuntimeException("Informe o id valido para fazer a busca!");
		}

		return jdbcTemplate.queryForObject("sql", new Object[] { id }, new QuestionRowMapper());
	}

	public List<Question> getAll() {
		return jdbcTemplate.query("sql", new QuestionRowMapper());
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}