package br.com.ptw.geral.generic.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import br.com.ptw.geral.generic.model.Entidade;
import brx.com.tigerbuilder.util.ReflectionUtils;
import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

public class EntityExtractor<T extends Entidade> implements ResultSetExtractor<T> {

	@SuppressWarnings("unchecked")
	public T extractData(ResultSet rs) throws SQLException, DataAccessException {
		T entidade = null;
		try {
			Class<?> clazzOfEntity = ReflectionUtils.getGenericTypeParameter(this.getClass());
			entidade = (T) clazzOfEntity.newInstance();

			Field[] fields = ReflectionUtils.getGenericTypeParameter(this.getClass()).getDeclaredFields();
			for (Field field : fields) {
				String columnName = UtilVilaQueryReflection.getColumnName(field);
				Object value = rs.getObject(columnName);
				ReflectionUtils.setValueForAttribute(entidade, field.getName(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entidade;
	}

}
