package lp2.lab04;

public class SistemaDeJogoDaVelha {
	static JogoDaVelhaOO jogoDaVelha = new JogoDaVelhaOO();

	public static void main(String[] args) {
		boolean sair = false;
		jogoDaVelha.criaTabuleiro ();
		while (!sair) {
			jogoDaVelha.atualizaTabuleiro();
		}
	}

}
