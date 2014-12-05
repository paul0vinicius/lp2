package lp2.lab04;

public class JogoDaVelhaOO {
	private char[][] tabuleiro = new char[3][3];
	
	public JogoDaVelhaOO () {
	}
	
	public void criaTabuleiro () {
		for(int i = 0; i < 3; i++)
	        for(int j = 0; j < 3; j++)
	            tabuleiro[i][j]=' ';
	}
	
	public void atualizaTabuleiro () {
			System.out.print("  JOGO DA VELHA \n");
	        for(int i = 0; i < 3; i++){
	        	System.out.print(tabuleiro[i][0]+"|"+tabuleiro[i][1]+"|"+tabuleiro[i][2]);
	            if(i < 2){
	            	System.out.print("\n------\n");
	            }
		}
	}
}
