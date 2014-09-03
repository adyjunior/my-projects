package br.com.ptw.geral.generic.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import br.com.ptw.geral.generic.model.Entidade;
import br.com.ptw.module.questionario.model.Question;
import brx.com.tigerbuilder.util.ReflectionUtils;
import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

public class EntityExtractor<T extends Entidade> implements ResultSetExtractor<T> {

	public T extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Field [] fields = ReflectionUtils.getGenericTypeParameter(this.getClass()).getDeclaredFields();
		for (Field field : fields) {
			String columnName = UtilVilaQueryReflection.getColumnName(field);
			Object value = rs.getObject(columnName);
			
			
		}
		
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
		
		return null;
	}

}
