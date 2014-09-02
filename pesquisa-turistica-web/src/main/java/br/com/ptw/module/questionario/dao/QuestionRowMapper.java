package br.com.ptw.module.questionario.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import br.com.ptw.module.questionario.model.Question;

public class QuestionRowMapper implements RowMapper<Question>{

	public Question mapRow(ResultSet rs, int row) throws SQLException {
		QuestionExtractor ex = new QuestionExtractor();
		return ex.extractData(rs);
	}
	
}

class QuestionExtractor implements ResultSetExtractor<Question> {

	public Question extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Long id = rs.getLong("id");
		String title = rs.getString("title");
		String description = rs.getString("description");
		String helpText = rs.getString("helpText");
		boolean isRequired = rs.getBoolean("isRequired");
		
		Question question = new Question();
		question.setId(id);
		question.setTitle(title);
		question.setDescription(description);
		question.setHelpText(helpText);
		question.setRequired(isRequired);
		
		return question;
	}
	
}