package agenda.ig;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import agenda.sistema.principal.Agenda;
import agenda.sistema.principal.Contato;
import agenda.sistema.principal.TabelaContatos;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JRadioButton;


public class AgendaJanela {

	private Agenda agenda;
	private JFrame frmAgenda;
	private JTextField textField_NomeCadastrar;
	private JTextField textField_NomePesquisar;
	private JTextField textField_NomeRemover;
	private JTextField textField_TelefoneCadastrar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaJanela window = new AgendaJanela();
					window.frmAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AgendaJanela() {
		initialize();
		
		
		
		this.agenda = new Agenda();
		try{
			FileInputStream fileStream = new FileInputStream("foo.ser");
			 //2 - Crie um ObjectInputStream
			 ObjectInputStream os = new ObjectInputStream(fileStream);
			
			agenda = (Agenda) os.readObject();
				
			//}
			os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//	try{
//		FileOutputStream arquivoGrav = new FileOutputStream("ContatosBD.txt");
//		ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
//		
//		for(Agenda a : agenda){
//			objGravar.writeObject(a);
//		}
//	}catch(Exception e){
//		e.printStackTrace();
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgenda = new JFrame();
		frmAgenda.setTitle("Agenda");
		frmAgenda.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmAgenda.setBounds(100, 100, 688, 403);
		frmAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgenda.getContentPane().setLayout(null);
		
		JLabel lblCadastrar = new JLabel("Novo Contato");
		lblCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblCadastrar.setBounds(58, 34, 137, 20);
		frmAgenda.getContentPane().add(lblCadastrar);
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblPesquisar.setBounds(270, 34, 110, 20);
		frmAgenda.getContentPane().add(lblPesquisar);
		
		JLabel lblRemover = new JLabel("Remover");
		lblRemover.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblRemover.setBounds(517, 34, 110, 20);
		frmAgenda.getContentPane().add(lblRemover);
		
		textField_NomeCadastrar = new JTextField();
		textField_NomeCadastrar.setBounds(109, 87, 86, 20);
		frmAgenda.getContentPane().add(textField_NomeCadastrar);
		textField_NomeCadastrar.setColumns(10);
		
		textField_NomePesquisar = new JTextField();
		textField_NomePesquisar.setBounds(343, 87, 86, 20);
		frmAgenda.getContentPane().add(textField_NomePesquisar);
		textField_NomePesquisar.setColumns(10);
		
		textField_NomeRemover = new JTextField();
		textField_NomeRemover.setBounds(576, 87, 86, 20);
		frmAgenda.getContentPane().add(textField_NomeRemover);
		textField_NomeRemover.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(36, 90, 46, 14);
		frmAgenda.getContentPane().add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(23, 136, 59, 14);
		frmAgenda.getContentPane().add(lblTelefone);
		
		textField_TelefoneCadastrar = new JTextField();
		textField_TelefoneCadastrar.setBounds(109, 133, 86, 20);
		frmAgenda.getContentPane().add(textField_TelefoneCadastrar);
		textField_TelefoneCadastrar.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(270, 90, 46, 14);
		frmAgenda.getContentPane().add(lblNome_1);
		
		JButton btnOk = new JButton("Adicionar");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String n =textField_NomeCadastrar.getText();
				String t = textField_TelefoneCadastrar.getText();
				Contato c = new Contato(n,t);
				agenda.adicionarContato(c);
				JOptionPane.showMessageDialog(null,"Contato Adicionado\n"+c.toString());
				textField_NomeCadastrar.setText("");
				textField_TelefoneCadastrar.setText("");
				
				
				try { //operação de E/S pode lançar excessões.
				     FileOutputStream fs = new FileOutputStream("foo.ser");//caso não encontre cria novo arquivo chamado foo.ser
				     ObjectOutputStream os = new ObjectOutputStream(fs); //fs encadeado ao fluxo de conexão
				     os.writeObject(agenda);
				     os.close();
				   }catch (Exception e) {
				     e.printStackTrace();
				   }
			}
		});
		btnOk.setBounds(73, 175, 89, 23);
		frmAgenda.getContentPane().add(btnOk);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n = textField_NomePesquisar.getText();
				Contato c = agenda.pesquisaContatoPeloNome(n);
				if(c != null){
				JOptionPane.showMessageDialog(null,"Contato encontrado\n"+c.toString());
				}else{
					JOptionPane.showMessageDialog(null,"Contato inexistente");
				}
				textField_NomePesquisar.setText("");
			}
		});
		btnNewButton.setBounds(291, 132, 99, 23);
		frmAgenda.getContentPane().add(btnNewButton);
		
		JLabel lblNome_2 = new JLabel("Nome");
		lblNome_2.setBounds(520, 90, 46, 14);
		frmAgenda.getContentPane().add(lblNome_2);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textField_NomeRemover.getText();
				try{
					Contato c = agenda.pesquisaContatoPeloNome(nome);
					agenda.removerContatoPeloNome(nome);
					JOptionPane.showMessageDialog(null,"Contato removido\n"+c.toString());
				}catch(RuntimeException lol){
					JOptionPane.showMessageDialog(null, lol.getMessage());
				}
				textField_NomeRemover.setText("");
				try { //operação de E/S pode lançar excessões.
				     FileOutputStream fs = new FileOutputStream("foo.ser");//caso não encontre cria novo arquivo chamado foo.ser
				     ObjectOutputStream os = new ObjectOutputStream(fs); //fs encadeado ao fluxo de conexão
				     os.writeObject(agenda);
				     os.close();
				   }catch (Exception o) {
				     o.printStackTrace();
				   }
				
			}
		});
		btnRemover.setBounds(538, 132, 89, 23);
		frmAgenda.getContentPane().add(btnRemover);
		
		JButton btnNewButton_1 = new JButton("Listar todos contatos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TabelaContatos t = new TabelaContatos();
				JTable j1 = t.gerarTabela(agenda);
				
				//
				
				JOptionPane.showMessageDialog(null, j1, "Contatos",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnNewButton_1.setBounds(225, 263, 216, 35);
		frmAgenda.getContentPane().add(btnNewButton_1);
	}
}
