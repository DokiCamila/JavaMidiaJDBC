package br.unip.sicc.view;


import br.unip.sicc.model.Midia;
import br.unip.sicc.model.TipoMidia;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MidiaTableModel implements TableModel {
	
	private List<Midia> midias;
	
	public MidiaTableModel(List<Midia> midias) {
		this.midias = midias;
	}

	@Override
	public int getRowCount() {
		return midias.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0 :
			return "Id";
		case 1:
			return "Tipo";
		case 2:
			return "Descricao";
		case 3:
			return "Valor";
		
		}
		return "";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return TipoMidia.class;
		case 2:
			return String.class;
		case 3:
			return Double.class;
		}
		return void.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Midia midiaAtual = midias.get(rowIndex);
		switch (columnIndex) {
		case 0:
			 return midiaAtual.getId();
		case 1:
			return midiaAtual.getTipo();
		case 2:
			return midiaAtual.getDescricao();
		case 3:
			return midiaAtual.getValor();
		}
		return "";
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
	}

	Midia  getMidias(int linha) {
		return midias.get(linha);
	}

	void setMidias(List<Midia> midias) {
		this.midias = midias;
	}
	
	
	

}
