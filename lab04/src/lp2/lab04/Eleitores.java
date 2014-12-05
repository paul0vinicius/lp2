package lp2.lab04;

/**
 * Classe que cria um eleitor armazenando seu nome, cpf, o titulo eleitoral e sua idade.
 * @author Paulo Vinicius Soares
 * @version 1.0 - 10 de Outubro de 2014
 */
public class Eleitores {
	private String nome;
	private String cpf;
	private String tituloEleitoral;
	private int idade;
	
	public Eleitores (String nome, String cpf, String tituloEleitoral, int idade) {
		this.nome = nome;
		this.cpf = cpf;
		this.tituloEleitoral = tituloEleitoral;
		this.idade = idade;
	}
	
	/**
	 * 
	 * @return O nome do eleitor
	 */
	public String getName () {
		return this.nome;
	}
	
	/**
	 * Facilita a saida de dados na hora da impressao das informacoes do eleitor.
	 */
	public String toString() {
		return "Nome: " + this.nome + " \nCPF: " + this.cpf + " \nTítulo Eleitoral: " + this.tituloEleitoral + " \nIdade: " + this.idade;
	}
}
