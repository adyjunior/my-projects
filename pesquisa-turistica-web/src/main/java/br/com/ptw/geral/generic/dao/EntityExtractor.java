package br.com.ptw.geral.generic.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import br.com.ptw.geral.generic.model.Entidade;
import brx.com.tigerbuilder.util.ReflectionUtils;
import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

public class EntityExtractor<T extends Entidade> implements ResultSetExtractor<T> {

	private Class<T> clazzOfEntity;
	
	public EntityExtractor(Class<T> clazzOfEntity) {
		this.clazzOfEntity = clazzOfEntity;
	}
	
	public T extractData(ResultSet rs) throws SQLException, DataAccessException {
		T entidade = null;
		try {
			entidade = (T) clazzOfEntity.newInstance();

			List<Field> fields = UtilVilaQueryReflection.listFieldsValidColumnOfEntity(clazzOfEntity);
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
