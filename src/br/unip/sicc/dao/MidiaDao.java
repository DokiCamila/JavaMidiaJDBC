package br.unip.sicc.dao;

import br.unip.sicc.model.Midia;
import br.unip.sicc.model.TipoMidia;
import java.util.List;

public interface MidiaDao {

	public void atualizar(Midia midia) throws DaoException;
	
	public void incluir(Midia midia) throws DaoException;
	public void excluir(Midia midia) throws DaoException;
	
	public List<Midia> getPorTipo(TipoMidia tipo) throws DaoException;
	
	public List<Midia> getTodos() throws DaoException;
	
	
	
	
}
