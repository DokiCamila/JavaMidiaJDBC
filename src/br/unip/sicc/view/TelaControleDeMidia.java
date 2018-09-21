package br.unip.sicc.view;


import br.unip.sicc.model.GerenciadorMidia;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar
;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class TelaControleDeMidia extends JFrame{
	
	private PainelCadastroMidia painelCadastro;
	private PainelBuscaMidia painelBusca; 
	private GerenciadorMidia gerenciador;

	
	private static TelaControleDeMidia instance;
	
	private TelaControleDeMidia(){
        inicializar();
    }
	static TelaControleDeMidia getInstance() {
        if(instance==null){
            instance = new TelaControleDeMidia();
        }
        return instance;
    }
    

    private void inicializar() {
		gerenciador =  GerenciadorMidia.getInstance();
	       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        this.setSize(800, 300);
	        this.setTitle("Controle de Midias: ");
	        this.setLocationRelativeTo(null);
	        this.montaMenu();
	        painelCadastro = PainelCadastroMidia.getInstance();
	        painelBusca = PainelBuscaMidia.getInstance();
	        this.add(painelBusca, BorderLayout.CENTER);
	        this.add(painelCadastro, BorderLayout.WEST);
	        this.setVisible(true);
	    }
	    
		private void montaMenu() {
	        JMenuBar barraDeMenu = new JMenuBar();
	        JMenu menuLancamento = new JMenu("Lançamento");
	        menuLancamento.setMnemonic(KeyEvent.VK_L);
	        JMenu menuAjuda = new JMenu("Ajuda");
	        menuAjuda.setMnemonic(KeyEvent.VK_A);
	        JMenuItem itemNovo = new JMenuItem("Novo");
	        itemNovo.setMnemonic(KeyEvent.VK_N);
	        itemNovo.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                painelCadastro.setMidia(gerenciador.getNovoMidia());
	            }
	        });
	        String mensagemSobre;
	        mensagemSobre = "Software desenvolvido na disciplona ALPOO";
	        JMenuItem itemSobre = new JMenuItem("Sobre");
	        itemSobre.setMnemonic(KeyEvent.VK_S);
	        itemSobre.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null, mensagemSobre,
	                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
	        menuLancamento.add(itemNovo);
	        menuAjuda.add(itemSobre);
	        barraDeMenu.add(menuLancamento);
	        barraDeMenu.add(menuAjuda);
	        this.setJMenuBar(barraDeMenu);
	        
	    }
	    
	    public static void main(String[] args) {
	        @SuppressWarnings("unused")
			TelaControleDeMidia tela = 
	                TelaControleDeMidia.getInstance();
	    }


	
	
	
	

}
