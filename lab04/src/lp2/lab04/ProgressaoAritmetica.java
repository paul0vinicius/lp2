package lp2.lab04;

import java.util.ArrayList;

/**
* Classe que cria uma Progressão Aritmetica a partir de dados inseridos pelo usuario.
* @author Paulo Vinicius Soares
* @version 1.0 - 10 de Outubro de 2014 
*/

public class ProgressaoAritmetica {
		
	private int primeiro;
	private int razao;
	private int n;
	private ArrayList<Double> progressaoAritmetica = new ArrayList<> ();

	public ProgressaoAritmetica (int primeiro, int razao) {
		this.primeiro = primeiro;
		this.razao = razao;
		this.n = 1;
	}
	
	/**
	 * 
	 * @return A razao da progressao aritmetica.
	 */
	public int getRazao () {
		
		return this.razao;
	}
	
	/**
	 * 
	 * @return O proximo numero da sequencia, o n eh 1 por padrao.
	 */
	public int proximo () {
		this.n++;
		
		return (this.primeiro + (this.n - 1) * this.razao);
	}
	
	/**
	 * Reinicia a sequencia.
	 * @return O primeiro numero da PA.
	 */
	public int primeiro () {
		this.n = 1;
		
		return this.primeiro;
	}
	
	/**
	 * Efetua consulta de um numero na PA.
	 * @param n O termo n a ser consultado pelo usuario.
	 * @return O numero da PA na posição n correspondente 
	 */
	public int termo (int n) {
		if (n <= 0)  this.n = 1;
		else this.n = n;
		
		return (this.primeiro + (n - 1) * this.razao);
	}
	
	/**
	 * Cria um ArrayList contendo a PA do primeiro ao enesimo termo
	 * @param n o enesimo termo
	 * @return A string contendo os termos da PA
	 */
	public String geraTermos (int n) {
		if (n <= 0) this.n = 1;
		else this.n = n;
		for (int i = 1; i <= this.n; i++) progressaoAritmetica.add((double) (this.primeiro + ((i - 1) * this.razao)));
		
		return progressaoAritmetica.toString(); 
	}
	
	/**
	 * Limpa o ArrayList a fim de deixar pronta para receber uma nova sequencia e exibir na tela.
	 */
	public void limpaLista () {
		progressaoAritmetica.clear();
	}
}
