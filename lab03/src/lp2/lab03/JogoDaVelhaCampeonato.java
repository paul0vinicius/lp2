package lp2.lab03;

import java.util.Scanner;

/* Autor: Paulo Vinícius da Silva Soares
 * Ciência da Computação - Programação II - Turma: 2014.2
 * Jogo da Velha Modularizado - Professor: Gustavo */

public class JogoDaVelhaCampeonato {
	private static Scanner entrada;
	private static String[] jogadores = new String[4];
	private static int[] jogadoresPartida = new int[2];
	private static int[] vitorias = new int[4];
	private static int[] posicoes = { 4, 3, 2, 1 };
	private static final int PRIMEIRO_JOGADOR = 0;
	private static final int SEGUNDO_JOGADOR = 1;

	public static void main(String args[]) throws Exception {
		boolean jogar = true;
		String vencedorPartida = "SEM VENCEDOR";

		jogadores = IdentificaJogadores();

		jogadoresPartida = EscolheJogadores();

		while (jogar) {
			vencedorPartida = JogoDaVelha(jogadores[jogadoresPartida[PRIMEIRO_JOGADOR]],jogadores[jogadoresPartida[SEGUNDO_JOGADOR]]);
			if (!(vencedorPartida.equals("SEMVENCEDOR"))) {
				if (vencedorPartida.equals(jogadores[jogadoresPartida[PRIMEIRO_JOGADOR]])) {
					vitorias[jogadoresPartida[PRIMEIRO_JOGADOR]]++;
				} else {
					vitorias[jogadoresPartida[SEGUNDO_JOGADOR]]++;
				}
				jogar = JogarNovamente();
				if (jogar) {
					jogadoresPartida = EscolheJogadores();
				} else {
					OrdenaLista(jogadores, vitorias);
					for (int i = 3; i >= 0; i--) {
						System.out.println(posicoes[i] + "º lugar: " + jogadores[i] + " - " + vitorias[i] + " vitória(s)");
					}
				}
			}
		}
	}

	private static int[] EntradaDeDados(int jogadas, String jogador1, String jogador2) {
		entrada = new Scanner(System.in);
		int[] coordenada = new int[2];
		if ((jogadas % 2) == 1) {
			System.out.print("PLAYER 2 - " + jogador2 + "\nLINHA: ");
		} else {
			System.out.print("PLAYER 1 - " + jogador1 + "\nLINHA: ");
		}
		coordenada[0] = entrada.nextInt();
		while (!(coordenada[0] >= 1 && coordenada[0] <= 3)) {
			System.err.print("Jogada inválida, tente novamente. Digite apenas números entre 1 e 3, inclusive.\n");
			if ((jogadas % 2) == 1) {
				System.out.print("PLAYER 2 - " + jogador2 + "\nLINHA: ");
			} else {
				System.out.print("PLAYER 1 - " + jogador1 + "\nLINHA: ");
			}
			coordenada[0] = entrada.nextInt();
		}
		System.out.print("COLUNA: ");
		coordenada[1] = entrada.nextInt();
		while (!(coordenada[1] >= 1 && coordenada[1] <= 3)) {
			System.err.print("Jogada inválida, tente novamente. Digite apenas números entre 1 e 3, inclusive.\n");
			System.out.print("COLUNA: ");
			coordenada[1] = entrada.nextInt();
		}
		return coordenada;
	}

	private static String Vencedor(char[][] tabuleiro, int jogadas, String jogador1, String jogador2) {
		final char O = 'O';
		final char X = 'X';

		if (VerificaVencedor(O, jogador2, tabuleiro)) {
			return jogador2;
		}
		if (VerificaVencedor(X, jogador1, tabuleiro)) {
			return jogador1;
		}

		if (jogadas == 9) {
			for (int i = 0; i < 3; i++) {
				System.out.print(tabuleiro[i][0] + "|" + tabuleiro[i][1] + "|"
						+ tabuleiro[i][2]);
				if (i < 3 - 1) {
					System.out.print("\n------\n");
				}
			}
			System.out.print("\nPARTIDA EMPATADA\n");
			return "EMPATE";
		}
		return "SEMVENCEDOR";
	}

	private static char[][] CriaTabuleiro(char[][] tabuleiro) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				tabuleiro[i][j] = ' ';
		return tabuleiro;
	}

	private static boolean EfetuaJogada(int jogada, char[][] tabuleiro, int i,
			int j) {
		if (tabuleiro[i - 1][j - 1] == ' ') {
			if ((jogada % 2) == 1)
				tabuleiro[i - 1][j - 1] = 'O';
			else
				tabuleiro[i - 1][j - 1] = 'X';
			return true;
		} else {
			System.out.println("Jogue novamente em um espaço vazio.");
			return false;
		}
	}

	private static boolean VerificaVencedor(char O, String jogador, char[][] tabuleiro) {
		if ((tabuleiro[0][0] == O && tabuleiro[0][1] == O && tabuleiro[0][2] == O)
				|| (tabuleiro[1][0] == O && tabuleiro[1][1] == O && tabuleiro[1][2] == O)
				|| (tabuleiro[2][0] == O && tabuleiro[2][1] == O && tabuleiro[2][2] == O)
				|| (tabuleiro[0][0] == O && tabuleiro[1][0] == O && tabuleiro[2][0] == O)
				|| (tabuleiro[0][1] == O && tabuleiro[1][1] == O && tabuleiro[2][1] == O)
				|| (tabuleiro[0][2] == O && tabuleiro[1][2] == O && tabuleiro[2][2] == O)
				|| (tabuleiro[0][0] == O && tabuleiro[1][1] == O && tabuleiro[2][2] == O)
				|| (tabuleiro[0][2] == O && tabuleiro[1][1] == O && tabuleiro[2][0] == O)) {
			for (int i = 0; i < 3; i++) {
				System.out.print(tabuleiro[i][0] + "|" + tabuleiro[i][1] + "|"
						+ tabuleiro[i][2]);
				if (i < 3 - 1) {
					System.out.print("\n------\n");
				}
			}
			System.out.println("\n" + jogador + ", VOCE VENCEU!!!\n");
			return true;

		}
		return false;
	}

	private static void AtualizaTabuleiro(char[][] tabuleiro) {
		System.out.print("JOGO DA VELHA \n");
		for (int i = 0; i < 3; i++) {
			System.out.print(tabuleiro[i][0] + "|" + tabuleiro[i][1] + "|"
					+ tabuleiro[i][2]);
			if (i < 2) {
				System.out.print("\n------\n");
			}
		}
	}

	private static String JogoDaVelha(String jogador1, String jogador2) {
		int jogadas = 0;
		int[] coordenada;
		char tabuleiro[][] = new char[3][3];
		String vencedor = "SEMVENCEDOR";
		boolean JOGADA_VALIDA;

		tabuleiro = CriaTabuleiro(tabuleiro);

		while (true) {
			if (jogadas == 9 || !(vencedor.equals("SEMVENCEDOR"))) {
				return vencedor;
			}
			AtualizaTabuleiro(tabuleiro);
			System.out.println("\nINSIRA AS COORDENADAS");
			coordenada = EntradaDeDados(jogadas, jogador1, jogador2);
			JOGADA_VALIDA = EfetuaJogada(jogadas, tabuleiro, coordenada[0],
					coordenada[1]);
			if (JOGADA_VALIDA) {
				jogadas++;
			}

			if (jogadas >= 5) {
				vencedor = Vencedor(tabuleiro, jogadas, jogador1, jogador2);
			}
		}
	}

	private static String[] IdentificaJogadores() {
		String[] jogadores = new String[4];
		entrada = new Scanner(System.in);
		for (int i = 0; i < 4; i++) {
			System.out.println("Insira o nome do jogador " + (i + 1) + ": ");
			jogadores[i] = entrada.next();
		}
		return jogadores;
	}

	private static boolean JogarNovamente() {
		String jogar;
		System.out.println("\nDeseja jogar novamente? (S/N)");
		jogar = entrada.next();
		if (jogar.equals("S") || jogar.equals("s")) {
			return true;
		}
		return false;
	}

	private static void OrdenaLista(String[] jogadores, int[] vitorias) {
			boolean swapped = true;
			int k = 0, minimo = 0;
			String pivo = " ";
			while (swapped){
				swapped = false;
				for(int i = 0; i < (vitorias.length - (k-2)); i++){
					if(vitorias[i] > vitorias[i+1]){
						minimo = vitorias[i];
						vitorias[i] = vitorias[i+1];
						vitorias[i+1] = minimo;
						pivo = jogadores[i];
						jogadores[i] = jogadores[i+1];
						jogadores[i+1] = pivo;
						swapped = true;
					}
				k++;
				}
			}
				}

	private static int[] EscolheJogadores() {
		int[] jogadoresRodada = new int[2];
		entrada = new Scanner(System.in);
		System.out.println("Selecione o primeiro jogador da partida (Um número entre 1 e 4). ");
		jogadoresRodada[0] = entrada.nextInt();
		while (!(jogadoresRodada[0] >= 1 && jogadoresRodada[0] <= 4)) {
			System.err.print("Digite apenas números entre 1 e 4, inclusive.\n");
		jogadoresRodada[0] = entrada.nextInt();
		}
		System.out.println("Selecione o segundo jogador da partida (Um número entre 1 e 4). ");
		jogadoresRodada[1] = entrada.nextInt();
		while (!(jogadoresRodada[1] >= 1 && jogadoresRodada[1] <= 4 && (jogadoresRodada[1] != (jogadoresRodada[0])))) {
			System.err.print("Digite apenas números entre 1 e 4, inclusive. O jogador 1 não pode ser igual ao jogador 2. \n");
		jogadoresRodada[1] = entrada.nextInt();
		}
		jogadoresRodada[0]--;
		jogadoresRodada[1]--;
		return jogadoresRodada;
	}

}
