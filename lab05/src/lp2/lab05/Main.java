package lp2.lab05;

import java.util.Scanner;

/**
 * A classe principal onde se cria uma sala, um robo e o usuario pode visualizar a movimentacao
 * do robo na sala atraves de comandos.
 * @author Paulo Vinicius da Silva Soares
 * @version 1.2 - 10/11/2014
 *
 */
public class Main {
	static boolean sair = false;
	static Scanner entrada, entradaString;
	static Robo robo;
	static Sala sala;

	public static void main(String[] args) throws Exception {
		entrada = new Scanner(System.in);
		entradaString = new Scanner(System.in);
		
		System.out.println("--------- MOVIMENTE O ROBÔ ---------\n");
		System.out.println("Primeiro, crie uma sala! Escolha as dimensões da sua sala: ");
		System.out.println("Linhas: ");
		int linhas = entrada.nextInt();
		System.out.println("Colunas: ");
		int colunas = entrada.nextInt();
		
		try {
			sala = new Sala(linhas, colunas);
		} catch (Exception e) {
			while (linhas <=0 || colunas <= 0) {
				System.err.println("As linhas e colunas não podem ser menor que 0.");
				System.out.println("Linhas: ");
				linhas = entrada.nextInt();
				System.out.println("Colunas: ");
				colunas = entrada.nextInt();
			}
		}
		finally {
			sala = new Sala(linhas, colunas);
		}
		
		System.out.print("Você deseja adicionar alguns obstáculos na sua sala?");
		System.out.print(" (Isso pode tornar o jogo um pouco mais emocionante) (1. Sim/ 2. Não).\n");
		int opcao = entrada.nextInt();
		
		if (opcao == 1) {
			boolean sairObstaculos = false;
			while (!sairObstaculos) {
				System.out.println("Onde você quer colocar os obstáculos? ");
				System.out.println("Linha: ");
				int obstLinha = entrada.nextInt();
				System.out.println("Coluna: ");
				int obstColuna = entrada.nextInt();
				sala.inserirObstaculo(obstLinha, obstColuna);
				System.out.println("Deseja inserir mais obstáculos? (1. Sim/ 2. Não)");
				int opcaoObstaculos = entrada.nextInt();
				if (opcaoObstaculos != 1) sairObstaculos = true;
			}
		}
		
		System.out.println("\nAgora você precisa criar um robô. Basta inserir a quantidade de energia que você quer ele tenha.");
		System.out.println("A energia do robô é a quantidade de movimentos que ele pode fazer.");
		int energia = entrada.nextInt();
		
		try {
			robo = new Robo(energia, sala);
		} catch (Exception e) {
			while (energia == 0) {
				System.err.println("A energia do robô não pode ser igual à zero. Por favor, insira um valor acima de 0.");
				energia = entrada.nextInt();
			}
			robo = new Robo(energia, sala);
		}
		
		
		
		System.out.println("Divirta-se!\n");
		
		while (!sair) {
			robo.desenhaRobo();
			
			System.out.print("Posição Atual: " + "(" + robo.getPosicao()[0] + ",");
			System.out.print(" " + robo.getPosicao()[1] + ")");
			System.out.print(" || Energia restante: " + robo.getEnergia());
			System.out.print(" || Passos: " + robo.getPassos() + "\n");
			
			if (robo.getEnergia() == 0) {
				System.out.println("\nVocê está sem energia. O jogo acabou para você.");
				break;
			}
			
			System.out.print("Direita (D), Esquerda (A), Cima (W) ou Baixo (S)?\n");
			
			String key = entradaString.next();
			
			switch (key) {
			case "w":
				if (!(robo.andarParaCima())) {
					System.err.println("Você não pode ir mais para cima, você encontrou um obstaculo ou encostou na parede.");
				}
				break;
			case "s":
				if (!(robo.andarParaBaixo())) {
					System.err.println("Você não pode ir mais para baixo, você encontrou um obstaculo ou encostou na parede.");
				}
				break;
			case "a":
				if (!(robo.andarParaEsquerda())) {
					System.err.println("Você não pode ir mais para a esquerda, você encontrou um obstaculo ou encostou na parede.");
				}
				break;
			case "d":
				if (!(robo.andarParaDireita())) {
					System.err.println("Você não pode ir mais para a direita, você encontrou um obstaculo ou encostou na parede.");
				}
				break;
			default:
				System.out.println("Deseja sair? (1. Sim / 2. Não)");
				int opcaoSaida = entrada.nextInt();
				if (opcaoSaida == 1) {
					sair = true;
					System.out.println("\nObrigado por jogar!");
				}
				break;
			}
		}
	}
}
