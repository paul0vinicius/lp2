package lp2.lab04;

import java.util.Scanner;

/**
 * Programa que gera uma progressao aritmetica e uma serie de Fibonacci cujo tamanho eh n.
 * O programa ainda verifica as series criadas e imprime na tela.
 * 
 * @author Paulo Vinicius Soares
 * @version 1.0 - 10 de Outubro de 2014
 */

public class ExplorandoOMundoDasSeries {
	private static Scanner entrada;
	private static final String MENSAGEM_ERRO1 = "Você não pode ver o termo de uma P.A que não existe. Crie uma P.A digitando (1) no Menu Principal.";
	private static final String MENSAGEM_ERRO2 = "Você não pode ver o termo de uma Série que não existe. Crie uma Série de Fibonacci digitando (2) no Menu Principal.";
	private static final String MENSAGEM_ERRO3 = "\nInsira um número menor ou igual ao tamanho da série. O tamanho máximo é ";
	
	public static void main (String args[]) {
		entrada = new Scanner(System.in);
		boolean sair = false;
		ProgressaoAritmetica PA = null;
		Fibonacci fb = null;
		
		while (!sair) {
			int opcao = telaOpcoesUsuario();
			
			switch (opcao) {
			
				case 1:
					PA = criaPA();
					break;
			
				case 2:
					fb = criaFibonacci();
					break;
				
				case 3:
					if (PA == null) {
						System.err.println(MENSAGEM_ERRO1);
					}
					else{
					System.out.println("Insira o termo a ser consultado da P.A.");
					int n = entrada.nextInt();
					System.out.println("O termo A(" + n + ") da P.A de razão " + PA.getRazao() + " é: " + PA.termo(n));
					}
					break;
			
				case 4:
					if (fb == null) {
						System.err.println(MENSAGEM_ERRO2);
					}
					else{
					System.out.println("Insira o termo a ser consultado da Série.");
					int n = entrada.nextInt();
					
					if ((n > fb.tamanhoSerie())) {
						System.err.println(MENSAGEM_ERRO3 + fb.tamanhoSerie() + "."); 
					}
					else {
						System.out.println("O termo S(" + n + ")" + " é: " + fb.termo(n));
					}
					}
					break;
			
				case 5:
					if (PA == null) {
						System.err.println(MENSAGEM_ERRO1);
					}
					else if (fb == null) {
						System.err.println(MENSAGEM_ERRO2);
					}
					else{
						System.out.println(MENSAGEM_ERRO3 + fb.tamanhoSerie() + ".");
						int n = entrada.nextInt();
					
						if ((n > fb.tamanhoSerie())) {
							System.err.println(MENSAGEM_ERRO3 + fb.tamanhoSerie()); 
					}
						else {
							System.out.println("Os termos da P.A: " + PA.geraTermos(n));
							System.out.print("Os termos da Série de Fibonacci: " + fb.imprimeTermos(n));
							PA.limpaLista();
							fb.limpaLista();
						}
					}
					break;
			
				case 6:
					System.out.println("Obrigado por utilizar nosso programa.\n");
					sair = true;
					break;
				
				default:
					System.err.println("Insira um valor entre 1 e 6! Leia novamente as instruções.");
					break;
			}
		}
		
	}
		
	/**
	 * Exibe o menu para o usuario interagir com o programa.
	 * @return A opcao do menu que o usuario escolheu.
	 */
		private static int telaOpcoesUsuario () {
			entrada = new Scanner(System.in);
			
			System.out.println("\n------------ PROGRESSÃO ARITMÉTICA & SÉRIE DE FIBONACCI ------------\n");
			System.out.println("Escolha uma das opções abaixo: ");
			System.out.println();
			System.out.println("1. Cria/Altera uma Progressão Aritmética.");
			System.out.println("2. Cria/Altera uma Série de Fibonacci.");
			System.out.println("3. Ver um termo 'n' da P.A.");
			System.out.println("4. Ver um termo 'n' da Série de Fibonacci.");
			System.out.println("5. Ver os termos de 1 à n na P.A e na Série de Fibonacci.");
			System.out.println("6. Sair.");
			
			int opcao = entrada.nextInt();
			return opcao;
		}
		
		/**
		 * Cria um objeto ProgressaoAritmetica na memoria contendo o primeiro termo e a razao.
		 * @return O objeto criado para o main
		 */
		private static ProgressaoAritmetica criaPA () {
			System.out.println("Insira o primeiro termo da P.A e, em seguida, insira a razão. ");
			int primeiroTermo = entrada.nextInt();
			int razao = entrada.nextInt();
			ProgressaoAritmetica progressao = new ProgressaoAritmetica(primeiroTermo, razao);
			System.out.println("Criada com sucesso!");
			
			return progressao;
			}
			
		/**
		 * Cria um objeto Fibonacci na memoria contendo n termos da serie.
		 * @return O objeto criado para o main
		 */
		private static Fibonacci criaFibonacci () {
			System.out.println("Insira a quantidade de termos na série.");
			int n = entrada.nextInt();
			Fibonacci fb = new Fibonacci(n);
			System.out.println("Criada com sucesso!");
			
			return fb;
			}
	}