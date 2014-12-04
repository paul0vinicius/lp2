package lp2.lab03;

import java.util.Scanner;
import java.util.Map;

public class JogoDaVelhaCampeonato {
	private static Scanner entrada;
	public static void main(String args[]) throws Exception{
		boolean jogar = true;
		String[] jogadores = new String[4];
		String vencedorPartida = "SEMVENCEDOR", vencedorPartida2 = "SEMVENCEDOR";
		jogadores = IdentificaJogadores();
		int[] vitorias = new int[4];
		int[] posicoes = {4,3,2,1};
		while(jogar){
		vencedorPartida = JogoDaVelha(jogadores[0], jogadores[1]);
		vencedorPartida2 = JogoDaVelhaSegundaRodada(jogadores[2], jogadores[3]);
		if(!(vencedorPartida.equals("SEMVENCEDOR"))){
			if(vencedorPartida.equals(jogadores[0])){
				vitorias[0]++;
			}
			else{
				vitorias[1]++;
			}
		if(!(vencedorPartida2.equals("SEMVENCEDOR"))){
			if(vencedorPartida2.equals(jogadores[2])){
				vitorias[2]++;
			}
			else{
				vitorias[3]++;
			}
			jogar = JogarNovamente();
			}
		}
	}
		OrdenaLista(jogadores, vitorias);
		for(int i = 3; i >= 0; i--){
			System.out.println(posicoes[i] + "º lugar: " + jogadores[i] + " - " + vitorias[i] + " vitória(s)");
		}
}
	
	private static int[] EntradaDeDados(int jogadas, Scanner sc, String jogador1, String jogador2){
		int[] coordenada = new int[2];
        if((jogadas % 2) == 1){
        	System.out.print("PLAYER 2 - " + jogador2 + "\nLINHA: ");
        }
        else{
        	System.out.print("PLAYER 1 - "+ jogador1 +"\nLINHA: ");
        }
			coordenada[0] = sc.nextInt();
		while(!(coordenada[0] >= 1 && coordenada[0] <= 3)){
			System.err.print("Jogada inválida, tente novamente. Digite apenas números entre 1 e 3, inclusive.\n");
			if((jogadas % 2) == 1){
				System.out.print("PLAYER 2 - " + jogador2 + "\nLINHA: ");
	        }
	        else{
	        	System.out.print("PLAYER 1 - "+ jogador1 +"\nLINHA: ");
	        }
			coordenada[0] = sc.nextInt();
		}
        System.out.print("COLUNA: ");
        coordenada[1] = sc.nextInt();
        while(!(coordenada[1] >= 1 && coordenada[1] <= 3)){
			System.err.print("Jogada inválida, tente novamente. Digite apenas números entre 1 e 3, inclusive.\n");
			 System.out.print("COLUNA: ");
		     coordenada[1] = sc.nextInt();
        }
        return coordenada;
	}
	
	private static String Vencedor(char[][] tabuleiro, int jogadas, String jogador1, String jogador2){
		final char O = 'O';
		final char X = 'X';
		
		if((tabuleiro[0][0]==O && tabuleiro[0][1]==O && tabuleiro[0][2]==O)||
		           (tabuleiro[1][0]==O && tabuleiro[1][1]==O && tabuleiro[1][2]==O)||
		           (tabuleiro[2][0]==O && tabuleiro[2][1]==O && tabuleiro[2][2]==O)||
		           (tabuleiro[0][0]==O && tabuleiro[1][0]==O && tabuleiro[2][0]==O)||
		           (tabuleiro[0][1]==O && tabuleiro[1][1]==O && tabuleiro[2][1]==O)||
		           (tabuleiro[0][2]==O && tabuleiro[1][2]==O && tabuleiro[2][2]==O)||
		           (tabuleiro[0][0]==O && tabuleiro[1][1]==O && tabuleiro[2][2]==O)||
		           (tabuleiro[0][2]==O && tabuleiro[1][1]==O && tabuleiro[2][0]==O))
		        {
		        	for(int i = 0; i < 3; i++)
			        {
			        	System.out.print(tabuleiro[i][0]+"|"+tabuleiro[i][1]+"|"+tabuleiro[i][2]);
			            if(i<3-1)
			            {
			            	System.out.print("\n------\n");
			            }
			        }
		        	System.out.println("\n" + jogador2 + ", VOCE VENCEU!!!\n");
		        	return jogador2;
		        }
		        if((tabuleiro[0][0]==X && tabuleiro[0][1]==X && tabuleiro[0][2]==X)||
		           (tabuleiro[1][0]==X && tabuleiro[1][1]==X && tabuleiro[1][2]==X)||
		           (tabuleiro[2][0]==X && tabuleiro[2][1]==X && tabuleiro[2][2]==X)||
		           (tabuleiro[0][0]==X && tabuleiro[1][0]==X && tabuleiro[2][0]==X)||
		           (tabuleiro[0][1]==X && tabuleiro[1][1]==X && tabuleiro[2][1]==X)||
		           (tabuleiro[0][2]==X && tabuleiro[1][2]==X && tabuleiro[2][2]==X)||
		           (tabuleiro[0][0]==X && tabuleiro[1][1]==X && tabuleiro[2][2]==X)||
		           (tabuleiro[0][2]==X && tabuleiro[1][1]==X && tabuleiro[2][0]==X))
		        {
		        	for(int i = 0; i < 3; i++)
			        {
			        	System.out.print(tabuleiro[i][0]+"|"+tabuleiro[i][1]+"|"+tabuleiro[i][2]);
			            if(i<3-1)
			            {
			            	System.out.print("\n------\n");
			            }
			        }
		        	System.out.print("\n" + jogador1 + ", VOCE VENCEU!!!\n");
		        	return jogador1;
		        }

		        if(jogadas == 9)
		        {
		        	for(int i = 0; i < 3; i++)
			        {
			        	System.out.print(tabuleiro[i][0]+"|"+tabuleiro[i][1]+"|"+tabuleiro[i][2]);
			            if(i < 3-1)
			            {
			            	System.out.print("\n------\n");
			            }
			        }
		        	System.out.print("\nPARTIDA EMPATADA\n");
		        	return "EMPATE";
		        }
				return "SEMVENCEDOR";
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
	private static String JogoDaVelha(String jogador1, String jogador2) {
		Scanner entrada = new Scanner(System.in);
		int jogadas = 0;
		int[] coordenada;
		char tabuleiro[][] = new char[3][3];
		String vencedor = "SEMVENCEDOR";
		boolean JOGADA_VALIDA;
		
		tabuleiro = CriaTabuleiro(tabuleiro);
		
		while(true){
			if(jogadas == 9 || !(vencedor.equals("SEMVENCEDOR"))){
				return vencedor;
			}
			AtualizaTabuleiro(tabuleiro);
			System.out.println("\nINSIRA AS COORDENADAS");
			coordenada = EntradaDeDados(jogadas, entrada, jogador1, jogador2);
			JOGADA_VALIDA = EfetuaJogada(jogadas, tabuleiro, coordenada[0], coordenada[1]);
			if(JOGADA_VALIDA){
				jogadas++;
			}
			
			if(jogadas >= 5){
				vencedor = Vencedor(tabuleiro, jogadas, jogador1, jogador2);
			}
		}
	}
	
	private static String[] IdentificaJogadores(){
		String[] jogadores = new String[4];
		entrada = new Scanner(System.in);
		for(int i = 0; i < 4; i++){
			System.out.println("Insira o nome do jogador "+ (i+1) +": ");
			jogadores[i] = entrada.next();
		}
		return jogadores;
	}
	
	private static boolean JogarNovamente(){
		String jogar;
		System.out.println("\nDeseja jogar novamente? (S/N)");
		jogar = entrada.next();
		if(jogar.equals("S") || jogar.equals("s")){
			return true;
		}
		return false;
	}
	
	private static String JogoDaVelhaSegundaRodada(String jogador3, String jogador4) {
		Scanner entrada = new Scanner(System.in);
		int jogadas = 0;
		int[] coordenada;
		char tabuleiro[][] = new char[3][3];
		String vencedor = "SEMVENCEDOR";
		boolean JOGADA_VALIDA;
		
		tabuleiro = CriaTabuleiro(tabuleiro);
		
		while(true){
			if(jogadas == 9 || !(vencedor.equals("SEMVENCEDOR"))){
				return vencedor;
			}
			AtualizaTabuleiro(tabuleiro);
			System.out.println("\nINSIRA AS COORDENADAS");
			coordenada = EntradaDeDadosP3P4(jogadas, entrada, jogador3, jogador4);
			JOGADA_VALIDA = EfetuaJogada(jogadas, tabuleiro, coordenada[0], coordenada[1]);
			if(JOGADA_VALIDA){
				jogadas++;
			}
			
			if(jogadas >= 5){
				vencedor = Vencedor(tabuleiro, jogadas, jogador3, jogador4);
			}
		}
	}
	private static int[] EntradaDeDadosP3P4(int jogadas, Scanner sc, String jogador1, String jogador2){
		int[] coordenada = new int[2];
        if((jogadas % 2) == 1){
        	System.out.print("PLAYER 4 - " + jogador2 + "\nLINHA: ");
        }
        else{
        	System.out.print("PLAYER 3 - "+ jogador1 +"\nLINHA: ");
        }
			coordenada[0] = sc.nextInt();
		while(!(coordenada[0] >= 1 && coordenada[0] <= 3)){
			System.err.print("Jogada inválida, tente novamente. Digite apenas números entre 1 e 3, inclusive.\n");
			if((jogadas % 2) == 1){
				System.out.print("PLAYER 4 - " + jogador2 + "\nLINHA: ");
	        }
	        else{
	        	System.out.print("PLAYER 3 - "+ jogador1 +"\nLINHA: ");
	        }
			coordenada[0] = sc.nextInt();
		}
        System.out.print("COLUNA: ");
        coordenada[1] = sc.nextInt();
        while(!(coordenada[1] >= 1 && coordenada[1] <= 3)){
			System.err.print("Jogada inválida, tente novamente. Digite apenas números entre 1 e 3, inclusive.\n");
			 System.out.print("COLUNA: ");
		     coordenada[1] = sc.nextInt();
        }
        return coordenada;
	}
	private static void OrdenaLista(String[] jogadores, int[] vitorias){
		    for(int fixo = 1; fixo < vitorias.length; fixo++) {
		        for (int var = fixo; var >= 1 && vitorias[var] < vitorias[var - 1]; var--) {
		            vitorias[var] += vitorias[var - 1];
		            vitorias[var - 1] = vitorias[var] - vitorias[var - 1];
		            vitorias[var] -= vitorias[var - 1];
		        }
		    }
	}
}
