package br.unip.sicc.model;

import br.unip.sicc.dao.DaoException;
import br.unip.sicc.dao.MidiaDao;
import br.unip.sicc.dao.MidiaJdbc;
import br.unip.sicc.dao.MidiaMemoriaDao;


import java.util.List;

public class GerenciadorMidia {
	
	 private static GerenciadorMidia instance;
	
	 private GerenciadorMidia() {
	    }
	 
	 public static GerenciadorMidia getInstance() {
		 if (instance==null) {
			 instance = new GerenciadorMidia();
			 }
		return instance;
	 }
	 
	 private MidiaDao dao = MidiaJdbc.getInstance();
	// private MidiaDao dao = new MidiaMemoriaDao();
	 
	 
	 public Midia getNovoMidia(){
		 Midia midia = new Midia();
		 midia.setId(new Integer(0));
		 midia.setTipo(TipoMidia.cd);
		 midia.setDescricao("");
		 midia.setValor(0.00);
		return midia;
	 }
	 
	 public  void  salvar(Midia midia) throws DaoException{
			boolean eNovo = midia != null  && midia.getId() != null  && !(midia.getId() >0);
		if (eNovo) {
			 dao.incluir(midia);
			 }else{
		    dao.atualizar(midia);
		 }	 
	}	
	 
	 public List<Midia> getTodosMidias() throws DaoException{
		 return dao.getTodos();
	 }
	 
	 
	 public List<Midia> getPorTipo(TipoMidia tipo) throws DaoException{
		if(tipo != null) {
		 return dao.getPorTipo(tipo);
		}else {
		 return dao.getTodos();
		 }
		 }
	 
	 
	 public void excluir(Midia midia) throws DaoException{
		  dao.excluir(midia);
		 
	 }
	 
		 
		 
	 }


