package br.com.ptw.geral.generic.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.ptw.geral.generic.model.Entidade;
import br.com.ptw.util.NumberUtil;
import br.com.ptw.util.springjdbc.SpringJdbcUtil;
import brx.com.tigerbuilder.builder.CreateBuilder;
import brx.com.tigerbuilder.builder.SelectBuilder;
import brx.com.tigerbuilder.builder.UpdateBuilder;

/**
 * @author Ady Junior - 31/08/2014
 *
 * @param <T>
 */
@Repository
public abstract class GenericDAO<T extends Entidade> {

	public T getById(Long id) {
		Class<T> clazz = obterTipoParametrizadoDoObjeto();
		SelectBuilder.forClass(clazz);
		
		return null;
	}

	public void insert(T t) {
		Class<T> clazz = obterTipoParametrizadoDoObjeto();
		String sqlInsert = CreateBuilder.newInstance(clazz).toString();
		Object [] objects = SpringJdbcUtil.getValuesEntityForInsert(t);
		getJdbcTemplate().update(sqlInsert, objects);
	}

	public void update(T t) {
		Class<T> clazz = obterTipoParametrizadoDoObjeto();
		String sqlUpdate = UpdateBuilder.forClass(clazz).toString();
		Object [] objects = SpringJdbcUtil.getValuesEntityForUpdate(t);
		getJdbcTemplate().update(sqlUpdate, objects);
	}

	public void remover(Long id) {
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		Class<T> clazz = obterTipoParametrizadoDoObjeto();
		String sqlUpdate = SelectBuilder.forClass(clazz).toString();
		List<T> elements = (List<T>) getJdbcTemplate().query(sqlUpdate, EntityRowMapper.newInstance());
		return elements;
	}

	@SuppressWarnings("unchecked")
	public List<T> listWithPagination(Integer numberPage, Integer numberPerPage) {
		 if(NumberUtil.isNullOrZero(numberPerPage)){
			 numberPerPage = 10;
		 }
		 
		 if(NumberUtil.isNullOrZero(numberPage)){
			 numberPage = 1;
		 }
		
		 int limit = numberPerPage;
		 int offSet = numberPage;
		 
		Class<T> clazz = obterTipoParametrizadoDoObjeto();
		String sqlUpdate = SelectBuilder.forClass(clazz).addLimit(limit, offSet).toString();
		List<T> elements = (List<T>) getJdbcTemplate().query(sqlUpdate, EntityRowMapper.newInstance());
		 
		// c.setFirstResult((numberPage-1) * numberPerPage);
		// c.setMaxResults(numberPerPage);

		return elements;
	}
	
	public abstract JdbcTemplate getJdbcTemplate();

	@SuppressWarnings("unchecked")
	private Class<T> obterTipoParametrizadoDoObjeto() {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		return clazz;
	}
}
