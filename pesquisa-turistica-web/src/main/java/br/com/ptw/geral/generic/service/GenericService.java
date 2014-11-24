package br.com.ptw.geral.generic.service;

import java.util.List;

import br.com.ptw.geral.generic.dao.GenericDAO;
import br.com.ptw.geral.generic.model.Entidade;

/**
 * @author Ady Junior - 30/03/2014
 *
 * @param <T>
 */
public abstract class GenericService<T extends Entidade> {

	public abstract GenericDAO<T> getDao();
	
	public T consultar(Long id) {
		return getDao().getById(id);
	}
	
	public void incluir(T t) {
		getDao().insert(t);
	}
	
	public void atualizar(T t) {
		getDao().update(t);
	}
	
	public List<T> listar() {
		return getDao().listAll();
	}
	
	public List<T> listarComPaginacao(Integer numberPage, Integer numberPerPage) {
		return getDao().listWithPagination(numberPage, numberPerPage);
	}
	
	public void remover(Long id){
		getDao().remover(id);
	}
	
}
