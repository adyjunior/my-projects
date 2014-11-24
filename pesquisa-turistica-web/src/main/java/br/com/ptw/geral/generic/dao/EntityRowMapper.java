package br.com.ptw.geral.generic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ptw.geral.generic.model.Entidade;

public class EntityRowMapper<T extends Entidade> implements RowMapper<T> {

//	public static <T extends Entidade> EntityRowMapper<T> newInstance() {
//		return new EntityRowMapper<T>();
//	}
	private Class<T> clazzOfEntity;
	
	public EntityRowMapper(Class<T> clazzOfEntity) {
		this.clazzOfEntity = clazzOfEntity;
	}
	

	public T mapRow(ResultSet rs, int rowNum) throws SQLException {

		EntityExtractor<T> extractor = new EntityExtractor<T>(clazzOfEntity);

		return extractor.extractData(rs);
	}
}
