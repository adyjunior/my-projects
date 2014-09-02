package br.com.ptw.geral.generic.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.ptw.geral.generic.model.Entidade;

/**
 * @author Ady Junior - 31/08/2014
 *
 * @param <T>
 */
public abstract class GenericDAO<T extends Entidade> {

	public T getById(Long id) {
		return null;
	}

	public void insert(T t) {
	}

	public void update(T t) {
	}

	public void remover(Long id) {
	}

	public List<T> listAll() {
		return null;
	}

	public List<T> listWithPagination(Integer numberPage, Integer numberPerPage) {
		//
		// Class<T> clazz = this.obterTipoParametrizadoDoObjeto();
		// Criteria c = this.getCriteria(clazz);
		//
		// //Integer qtdElementos = (Integer) consultarQtdElementos(dc);
		// if(UtilObjeto.ehNumeroNuloOuZero(numberPerPage)){
		// numberPerPage = 10;
		// }
		//
		// if(UtilObjeto.ehNumeroNuloOuZero(numberPage)){
		// numberPage = 1;
		// }
		//
		// c.setFirstResult((numberPage-1) * numberPerPage);
		// c.setMaxResults(numberPerPage);

		return null;
	}

	@SuppressWarnings("unchecked")
	private Class<T> obterTipoParametrizadoDoObjeto() {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		return clazz;
	}
}
