package agenda.sistema.principal;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaContatos {
	
	public TabelaContatos(){
		
		
		//JOptionPane.showMessageDialog(null,jtable);  
	}
	
	public JTable gerarTabela(Agenda agenda){
		String[][] dados = new String [agenda.contatos.size()][2];  
	             
		String[] colunas = new String []{"Nome","Telefone"};  
	
		
		for(int i = 0; i< dados.length ; i++){
			Contato c = agenda.contatos.get(i);
			dados[i][0] = c.getNome();
			dados[i][1] = c.getTelefone();
			
			
		}
		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);  
	
		JTable jtable = new JTable( modelo );  
		return jtable;
	}
	
}
