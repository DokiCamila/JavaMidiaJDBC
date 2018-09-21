package br.unip.sicc.view;

import br.unip.sicc.dao.DaoException;
import br.unip.sicc.model.GerenciadorMidia;
import br.unip.sicc.model.Midia;
import br.unip.sicc.model.TipoMidia;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class PainelCadastroMidia extends JPanel{


	private JLabel lblId,lblTipo, lblDescricao, lblValor;
	private JTextField txtDescricao;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTipo;
	private JButton btOk, btCancelar;
	private JFormattedTextField txtValor, txtId;
	private Midia midia;
    private GerenciadorMidia gerenciador;

    
    private static PainelCadastroMidia instance;

    private PainelCadastroMidia() {
        inicializar();
    }
    public static PainelCadastroMidia getInstance() {
        if (instance == null) {
            instance = new PainelCadastroMidia();
        }
        return instance;
    }
    
    private void inicializar() {
    	gerenciador =  GerenciadorMidia.getInstance();
    	this.setLayout(new BorderLayout());
        JPanel painelCampos = criaPainelCampos();
        JPanel painelBotoes = criaPainelBotoes();
        this.add(painelCampos, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);   	
    }
	private JPanel criaPainelBotoes() {
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btCancelar = new JButton("Cancelar");
        btCancelar.setMnemonic(KeyEvent.VK_C);
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMidia(midia);
            }
        });
        btOk = new JButton("Ok");
        btOk.setMnemonic(KeyEvent.VK_O);
        btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Midia midiaEditado = getMidiaEditado();
                    gerenciador.salvar(midiaEditado);
                    midia = midiaEditado;
                    PainelBuscaMidia.getInstance().atualizaTabela();
                } catch (DaoException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível confirmar a operação",
                            "Confirmação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        painel.add(btCancelar);
        painel.add(btOk);
		return painel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JPanel criaPainelCampos() {
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(7,2,5,5));
		lblId = new JLabel("Id:");
		txtId = new JFormattedTextField(NumberFormat.getIntegerInstance());
		txtId.setEditable(false);
		lblTipo = new JLabel("Tipo:");
		TipoMidia[] tipos = TipoMidia.values();
		cbTipo = new JComboBox(tipos);
		lblDescricao = new JLabel("Descrição:");
		txtDescricao = new JTextField();
		lblValor = new JLabel("Valor:");
		txtValor =  new JFormattedTextField(NumberFormat.getCurrencyInstance());
		painel.add(lblId);
		painel.add(txtId);
		painel.add(lblTipo);
		painel.add(cbTipo);
		painel.add(lblDescricao);
		painel.add(txtDescricao);
		painel.add(lblValor);
		painel.add(txtValor);
		return painel;
	}
    
	 void setMidia(Midia midia) {
		 if(midia != null) {
			 this.midia = midia;
			 txtId.setValue(midia.getId());
			 cbTipo.setSelectedItem(midia.getTipo());
			 txtDescricao.setText(midia.getDescricao());
			 txtValor.setValue(midia.getValor());
		 }else {
			 JOptionPane.showConfirmDialog(this, "Midia nula", "Erro",
	                    JOptionPane.INFORMATION_MESSAGE);
		 } 
		 
	}
	  Midia getMidiaEditado() {
			Midia midiaEditado = new Midia();
			midiaEditado.setId((Integer)txtId.getValue());
			TipoMidia tipo = (TipoMidia) cbTipo.getSelectedItem();
	        midiaEditado.setTipo(tipo);
	        Number valor = (Number) txtValor.getValue();
	        midiaEditado.setValor(Double.valueOf(valor.doubleValue()));
	        midiaEditado.setDescricao(txtDescricao.getText());
	        return midiaEditado;
		}
    
    
    
}
