package br.unip.sicc.view;



import br.unip.sicc.dao.DaoException;
import br.unip.sicc.model.GerenciadorMidia;
import br.unip.sicc.model.Midia;
import br.unip.sicc.model.TipoMidia;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;



@SuppressWarnings("serial")
public class PainelBuscaMidia extends JPanel {
	
	 
	private JLabel  lblFiltro ;
	    @SuppressWarnings("rawtypes")
		private JComboBox cbFiltroTipo ; 
	    private JButton btBuscar, btSelecionar, btExcluir;
	    private JTable tbMidias;
	    private GerenciadorMidia gerenciador;
	    private static PainelBuscaMidia instance;

	    public PainelBuscaMidia() {
	        gerenciador = GerenciadorMidia.getInstance();
	        this.setLayout(new BorderLayout());
	        JPanel painelFiltro = criaPainelFiltro();
	        JScrollPane painelTabela = criaPainelTabela();
	        JPanel painelBotoes = criaPainelBotoes();
	        this.add(painelFiltro, BorderLayout.NORTH);
	        this.add(painelTabela, BorderLayout.CENTER);
	        this.add(painelBotoes, BorderLayout.SOUTH);
	    }
	    
	    public static PainelBuscaMidia getInstance() {
	    	 if (instance == null) {
	             instance = new PainelBuscaMidia();
	         }
	         return instance;
		}

		
	    
	   private JPanel criaPainelBotoes() {
			JPanel painel = new JPanel();
			painel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			btSelecionar = new JButton("Selecionar");
			btSelecionar.setMnemonic(KeyEvent.VK_S);
			btSelecionar.addActionListener(new SelecionarListener());
			btExcluir = new JButton("Excluir");
	        btExcluir.setMnemonic(KeyEvent.VK_X);
	        btExcluir.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                Midia midia = getMidiaSelecionado();
	                if (midia == null) {
	                    JOptionPane.showMessageDialog(null, "Selecione uma midia","Selecione", JOptionPane.INFORMATION_MESSAGE);
	                    return;
	                }
	                String mensagem = "Confirma a exclusão da midia " + midia.getId();
	                int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação",JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	                try {
	                    if (opcao == JOptionPane.OK_OPTION) {
	                        gerenciador.excluir(midia);
	                        atualizaTabela();
	                    }
	                } catch (DaoException ex) {
	                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a exclusão","Exclusão", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });
	        painel.add(btSelecionar);
	        painel.add(btExcluir);
	        return painel;
	    }
	   

	private JScrollPane criaPainelTabela() {
			MidiaTableModel midiasTableModel = null;
			try {
				midiasTableModel = new MidiaTableModel(gerenciador.getTodosMidias());
	        } catch (DaoException ex) {
	            System.out.println("Mostar erro");
	        }
	        tbMidias = new JTable(midiasTableModel);
	        tbMidias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JScrollPane scroll = new JScrollPane(tbMidias);
	        return scroll;
		}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JPanel criaPainelFiltro() {
		
		JPanel painel = new JPanel();
        lblFiltro = new JLabel("Tipo");
        TipoMidia[] tipos = TipoMidia.values();
        cbFiltroTipo = new JComboBox(tipos);
        cbFiltroTipo.insertItemAt("TODOS", 0);
        cbFiltroTipo.setSelectedIndex(0);
        btBuscar = new JButton("Busca");
        btBuscar.setMnemonic(KeyEvent.VK_B);
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object tipo = cbFiltroTipo.getSelectedItem();
                if(tipo instanceof TipoMidia){
                    TipoMidia tipoMidia = (TipoMidia) tipo;
                    try {                    
                        List<Midia> porTipo = gerenciador.getPorTipo(tipoMidia);
                        tbMidias.setModel(new MidiaTableModel(porTipo));
                    } catch (DaoException ex) {
                        Logger.getLogger(PainelBuscaMidia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        List<Midia> todas = gerenciador.getTodosMidias();
                        tbMidias.setModel(new MidiaTableModel(todas));
                    } catch (DaoException ex) {
                        Logger.getLogger(PainelBuscaMidia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        painel.add(lblFiltro);
        painel.add(cbFiltroTipo);
        painel.add(btBuscar);
        return painel;
    }

	 private class SelecionarListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Midia midia = getMidiaSelecionado();
	            if (midia == null) {
	                JOptionPane.showMessageDialog(null, "Selecione uma midia",
	                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }
	            PainelCadastroMidia.getInstance().setMidia(midia);
	        }
	    }
	 private Midia getMidiaSelecionado() {
	        int linhaSelecionada = tbMidias.getSelectedRow();
	        Midia midiaSelecionado = null;
	        if (linhaSelecionada == -1) {
	            System.out.println("Nenhuma linha selecionada");
	        } else {
	            MidiaTableModel tableModel
	                    = (MidiaTableModel) tbMidias.getModel();
	            midiaSelecionado
	                    = tableModel.getMidias(linhaSelecionada);
	        }
	        return midiaSelecionado;
	    }
	
	  void atualizaTabela() {
		   Object itemSelecionado = cbFiltroTipo.getSelectedItem();
	        TipoMidia tipoSelecionado = null;
	        if (itemSelecionado instanceof TipoMidia) {
	            tipoSelecionado= (TipoMidia) itemSelecionado;
	        }
	        try {
	            List<Midia> listaFiltrada = gerenciador.getPorTipo(tipoSelecionado);
	            MidiaTableModel model = (MidiaTableModel) tbMidias.getModel();
	            model.setMidias(listaFiltrada);
	            tbMidias.repaint();
	            tbMidias.revalidate();
	        } catch (DaoException ex) {
	            JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca",
	                    "Busca", JOptionPane.ERROR_MESSAGE);
	        }
	    }




	


	
			
}

	    

