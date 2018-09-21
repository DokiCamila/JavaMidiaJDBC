package br.unip.sicc.dao;

import java.util.ArrayList;
import java.util.List;

import br.unip.sicc.model.Midia;
import br.unip.sicc.model.TipoMidia;

public class MidiaMemoriaDao implements MidiaDao{

	private List<Midia> midias;
	private static int contador = 1;
	

	public MidiaMemoriaDao() {
		 midias = new ArrayList<>();
		 midias.add(new Midia(contador++,TipoMidia.dvd,"DVD da Anitta",14.00));
		 midias.add(new Midia(contador++,TipoMidia.cd,"Cd do Jesus Culture",10.00));
		 midias.add(new Midia(contador++,TipoMidia.dvd,"DVD da Rihanna",20.00));	 
	}
	
	@Override
	public void incluir(Midia midia) throws DaoException {
		if (midia != null) {
			midia.setId(contador++);
			midias.add(midia);
		}else {
			throw new DaoException("Midia Nula");
		}
		
	}
	
	public void atualizar(Midia midia) throws DaoException {
		if(midia != null) {
			for(Midia midiaAtual : midias) {
 				if(midiaAtual.getId() == midia.getId()) {
					int indice = midias.indexOf(midiaAtual);
					midias.set(indice, midia);
					break;
				}
			}
		}else {
			throw new DaoException("Midia nula");
		}
	}

	@Override
	public void excluir(Midia midia) throws DaoException {
		if(midia != null) {
			midias.remove(midia);
		}else {
			throw new DaoException("Midia nula");
		}
	}

	@Override
	public List<Midia> getPorTipo(TipoMidia tipo) throws DaoException {
		List<Midia> midiasFiltradas = new ArrayList<>();
			for (Midia midiaAtual : midias) {
				if(midiaAtual.getTipo() == tipo) {
					midiasFiltradas.add(midiaAtual);
					break;
				}
			}
		return midiasFiltradas;	
	}

	@Override
	public List<Midia> getTodos() throws DaoException {
		return midias;
	}

	
	
	
}
