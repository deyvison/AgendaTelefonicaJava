package agenda.sistema.principal;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class Agenda implements Serializable {

	 List<Contato> contatos;
	
	public Agenda(){
		this.contatos = new LinkedList<Contato>();
	}
	
	public void adicionarContato(Contato c){
		
		validarContato(c);
		this.contatos.add(c);
	}
	private void validarContato(Contato c1) {
		// TODO Auto-generated method stub
		for(Contato c : contatos){
			if(c.getTelefone().equals(c1.getTelefone())){
				throw new RuntimeException("Já existe um contato com este telefone");
			}else if(c.getNome().equals(c1.getNome())){
				throw new RuntimeException("Já existe um contato com este nome");
			}
			
		}
		
	}

	

	

	public void removerContatoPeloNome(String nome){
		boolean removeu = false;
		for(Contato c : this.contatos){
			if(c.getNome().equals(nome)){
				this.contatos.remove(c);
				removeu = true;
			}
		}
		if(!removeu){
			throw new RuntimeException("Contato inexistente");
		}
		
		
		
	}
	public void removerContatoPeloTelefone(String telefone){
		this.contatos.remove(this.pesquisaContatoPeloTelefone(telefone));
	}
	public Contato pesquisaContatoPeloNome(String nome){
		for(Contato c : this.contatos){
			if(c.getNome().equals(nome)){
				return c;
			}
		}
		return null;
		
	}
	public Contato pesquisaContatoPeloTelefone(String telefone){
		for(Contato c : this.contatos){
			if(c.getTelefone().equals(telefone)){
				return c;
			}
		}
		throw new RuntimeException("Contato Inexistente");
		
	}
	public List<Contato> visualizarTodosOsContatos(){
		return this.contatos;
	}
	public List<Contato> pesquisaContatoComDeterminadaPalavra(String parte){
		List<Contato> retorno = new LinkedList<Contato>();
		
		for(Contato c : this.contatos){
			if(c.getNome().contains(parte)){
				retorno.add(c);
			}
		}
		return retorno;
		
	}
}
