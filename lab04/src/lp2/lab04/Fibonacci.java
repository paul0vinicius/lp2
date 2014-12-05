package lp2.lab04;

import java.util.ArrayList;

/**
 * Classe que gera uma serie de Fibonacci com tamanho 'n' definido pelo usuario.
 * @author Paulo Vinicius Soares
 * @version 1.0 - 10 de Outubro de 2014
 */
public class Fibonacci {
	private final double PRIMEIRO_TERMO = 1;
	private final double SEGUNDO_TERMO = 1;
	private ArrayList<Double> fibonacciSerie = new ArrayList<> ();
	private ArrayList<Double> saidaUsuario = new ArrayList<> ();
	
	public Fibonacci(int n) {
		this.fibonacciSerie.add(PRIMEIRO_TERMO);
		this.fibonacciSerie.add(SEGUNDO_TERMO);
		geraSerieFibonacci(n);
	}
	
	/**
	 * Cria um ArrayList contendo os numeros da sequencia.
	 * @param n tamanho do ArrayList
	 * @return O ArrayList com os elementos da serie
	 */
	private ArrayList<Double> geraSerieFibonacci (int n) {
		for (int i = 2; i < n; i++) fibonacciSerie.add(fibonacciSerie.get(i-2) + fibonacciSerie.get(i-1));
		
		return fibonacciSerie;
	}
	
	/**
	 * Calcula o tamanho do ArrayList e disponibiliza para o usuario.
	 * @return O tamanho do ArrayList
	 */
	public int tamanhoSerie () {
		return fibonacciSerie.size(); 
	}
	
	/**
	 * Adiciona elementos em um ArrayList para imprimir os termos de 1 a n
	 * @param n a quantidade de numeros
	 * @return A string formatada e pronta para ser exibida ao usuario
	 */
	public String imprimeTermos (int n) {
		for (int i = 0; i < n; i++){
			saidaUsuario.add(fibonacciSerie.get(i));
		}
		return saidaUsuario.toString();
	}
	
	/**
	 * Acessa diretamente um elemento n da sequencia
	 * @param n o elemento que se quer exibir
	 * @return o numero correspondente a posicao n na serie
	 */
	public double termo (int n) {
		return fibonacciSerie.get(n-1);
	}
	
	/**
	 * Limpa o ArrayList de impressao de dados para o usuario
	 */
	public void limpaLista () {
		saidaUsuario.clear();
	}
}