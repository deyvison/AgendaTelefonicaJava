package agenda.sistema.principal;

import java.io.Serializable;

public class Contato implements Serializable {
	private String nome;
	private String telefone;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Contato(String nome,String telefone){
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public String toString(){
		return "Nome: "+this.nome+"\nTelefone: "+this.telefone;
	}
}
