package lp2.lab03;

import java.util.Scanner;

/* Autor: Paulo Vinícius da Silva Soares
 * Ciência da Computação - Programação II - Turma: 2014.2
 * Jogo da Velha Modularizado - Professor: Gustavo */

public class JogoDaVelhaModularizado {
	
	public static void main(String args[]){
		boolean jogar = true;
		while(jogar){
		jogar = JogoDaVelha();
		}
		System.out.println("Obrigado por jogar!");
	}
	
	private static int[] EntradaDeDados(int jogadas, Scanner sc){
		int[] coordenada = new int[2];
        if((jogadas % 2) == 1){
        	System.out.print("PLAYER 2\nLINHA: ");
        }
        else{
        	System.out.print("PLAYER 1\nLINHA: ");
        }
		coordenada[0] = sc.nextInt();
		while(!(coordenada[0] >= 1 && coordenada[0] <= 3)){
			System.out.print("Jogada inválida, tente novamente.\n");
			if((jogadas % 2) == 1){
	        	System.out.print("PLAYER 2\nLINHA: ");
	        }
	        else{
	        	System.out.print("PLAYER 1\nLINHA: ");
	        }
			coordenada[0] = sc.nextInt();
		}
        System.out.print("COLUNA: ");
        coordenada[1] = sc.nextInt();
        while(!(coordenada[1] >= 1 && coordenada[1] <= 3)){
			System.out.print("Jogada inválida, tente novamente.\n");
			 System.out.print("COLUNA: ");
		     coordenada[1] = sc.nextInt();
        }
        return coordenada;
	}
	
	private static boolean Vencedor(char[][] tabuleiro, int jogadas) {
		final char O = 'O';
		final char X = 'X';

		if(VerificaVencedor(X, tabuleiro)){
			System.out.println("\nJOGADOR 1 VENCEU!!!!");
			return true;
		}
		if(VerificaVencedor(O, tabuleiro)){
			System.out.println("\nJOGADOR 2 VENCEU!!!!");
			return true;
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
			return true;
		}
		return false;
	}
	
	private static boolean VerificaVencedor(char O, char[][] tabuleiro) {
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
			return true;

		}
		return false;
	}
	
	private static char[][] CriaTabuleiro(char[][] tabuleiro){
		for(int i = 0; i < 3; i++)
	        for(int j = 0; j < 3; j++)
	            tabuleiro[i][j]=' ';
		return tabuleiro;
	}
	
	private static boolean EfetuaJogada(int jogada, char[][] tabuleiro, int i, int j) {
		if(tabuleiro[i-1][j-1]== ' '){
            if((jogada%2)==1)tabuleiro[i-1][j-1]= 'O';
            else tabuleiro[i-1][j-1]= 'X';
            return true;
		}
		else{
			System.out.println("Jogue novamente em um espaço vazio.");
			return false;
		}
	}
	
	private static void AtualizaTabuleiro(char[][] tabuleiro){
		System.out.print("  JOGO DA VELHA \n");
        for(int i = 0; i < 3; i++){
        	System.out.print(tabuleiro[i][0]+"|"+tabuleiro[i][1]+"|"+tabuleiro[i][2]);
            if(i < 2){
            	System.out.print("\n------\n");
            }
	}
}
	private static boolean JogoDaVelha() {
		Scanner entrada = new Scanner(System.in);
		int jogadas = 0;
		int[] coordenada;
		boolean vencedor = false;
		char tabuleiro[][] = new char[3][3];
		String jogar;
		boolean JOGADA_VALIDA;
		
		tabuleiro = CriaTabuleiro(tabuleiro);
		
		while(true){
			if(jogadas == 9 || vencedor){
				break;
			}
			AtualizaTabuleiro(tabuleiro);
			System.out.println("\nINSIRA AS COORDENADAS");
			coordenada = EntradaDeDados(jogadas, entrada);
			JOGADA_VALIDA = EfetuaJogada(jogadas, tabuleiro, coordenada[0], coordenada[1]);
			if(JOGADA_VALIDA){
				jogadas++;
			}
			
			if(jogadas >= 5){
				vencedor = Vencedor(tabuleiro, jogadas);
			}
		}
		System.out.println("\nDeseja jogar novamente? (S/N)");
		jogar = entrada.next();
		if(jogar.equals("S") || jogar.equals("s")){
			return true;
		}
		return false;
	}
}
