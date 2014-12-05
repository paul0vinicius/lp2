package lab02;

import java.util.*;
import java.lang.*;

public class PedraPapelTesoura {

	private static Scanner entrada;

	public static void main(String[] args) {
		final int n = 3;
		final int SAIR = -1;
		int jogador1, jogador2, res;
		Dictionary dict = new Hashtable();
		dict.put(1, "PEDRA");
		dict.put(2, "PAPEL");
		dict.put(3, "TESOURA");
		
		while(true){
		Random r = new Random();
		entrada = new Scanner(System.in);
		System.out.print("Pedra (1), Papel (2), Tesoura (3)? ");
		jogador1 = entrada.nextInt();
		if(jogador1 == SAIR){
			break; // condição de saída.
		}
		while(!(jogador1 >= 1 && jogador1 <= n)){
			System.out.print("O número deve estar entre 1 e " + n + ". ");
			jogador1 = entrada.nextInt();
		}
		jogador2 = r.nextInt(n+1);
		System.out.println("Pedra (1), Papel (2), Tesoura (3)? " + jogador2);
		while(!(jogador2 >= 1 && jogador2 <= n)){
			System.out.println("O número deve estar entre 1 e " + n + ". ");
			jogador2 = r.nextInt(n+1);
		}
		System.out.println(dict.get(jogador1) + "-" + dict.get(jogador2));
		res = Math.abs((n + jogador1 - jogador2) % 3);
		
		if(res > 0 && res <= n/2){
			System.out.println("Jogador 1 Ganhou!");
		}
		else if(res == 0){
			System.out.println("Empate!");
		}
		else{
			System.out.println("Jogador 2 Ganhou!");
		}
		
		}
	}

}
