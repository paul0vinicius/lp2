package lp2.lab06;

/**
 * A classe cria uma sala onde podem ser implementados obstaculos e tambem pode servir
 * para a movimentacao de um robo.
 * 
 * A classe sala foi utilizada nesse LAB para testar o metodo equals das classes CD e CDTeca.
 * 
 * @author Paulo Vinicius da Silva Soares
 * @version 1.3 - 10/11/2014
 */
public class Sala {
	final public static char OCUPADO = 'R';
	final public static char LIVRE = ' ';
	final public static char OBSTACULO = 'X';
	private char[][] sala;
	private int numero_linhas;
	private int numero_colunas;
	
	public Sala (int num_linhas, int num_colunas) throws Exception {
		if (num_linhas <= 0 || num_colunas <= 0) {
			throw new Exception("Parametros incorretos para a criacao da sala. As dimensoes da sala devem ser representadas por valores positivos maiores que zero");
		}
		this.sala = new char[num_linhas][num_colunas];
			for (int i = 0; i < num_linhas; i++) {
				for (int j = 0; j < num_colunas; j++) {
					sala[i][j] = LIVRE;
				}
			}
			this.numero_linhas = num_linhas;
			this.numero_colunas = num_colunas;
		}
	
	/**
	 * Verifica o numero de posicoes horizontais.
	 * @return O numero de linhas da sala.
	 */
	public int getNumPosicoesHorizontais () {
		return numero_linhas;
	}
	
	/**
	 * Verifica o numero de posicoes verticais.
	 * @return o numero de colunas da sala.
	 */
	public int getNumPosicoesVerticais() {
		return numero_colunas;
	}
	
	/**
	 * Verifica a sala criada, tais quais os obstaculos e espacos livres.
	 * @return Uma matriz de char contendo os espacos livres e ocupados da sala.
	 */
	public char[][] getSala () {
		return sala;
	}
	
	/**
	 * Verifica se a sala se encontra vazia (sem obstaculos em qualquer coordenada).
	 * @return True: Se a sala estiver vazia; False: Se a sala possuir um obstaculo.
	 */
	public boolean isVazia () {
		for (int i = 0; i < numero_linhas; i++){
			for (int j = 0; j < numero_colunas; j++) {
				if (sala[i][j] != LIVRE) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Insere um obstaculo na posicao (linha, coluna) dada na entrada.
	 * @param linha
	 * @param coluna
	 * @return True: Se o obstaculo foi inserido com sucesso; False: Se a coordenada dada na entrada for invalida.
	 * @throws Exception: Se as linhas e colunas estiverem fora das coordenadas da sala.
	 */
	public boolean inserirObstaculo (int linha, int coluna) throws Exception {
		if (!(posicaoValida(linha, coluna))) {
			return false;
		}
			if (isPosicaoLivre(linha, coluna)) {
				sala[linha][coluna] = OBSTACULO;
				return true;
			}
			return false;
	}
	
	/**
	 * Verifica se a posicao inserida esta dentro do intervalo [0, tamanho da sala).
	 * @param linha
	 * @param coluna
	 * @return True: Se estiver no intervalo; False: Se estiver fora do intervalo.
	 */
	public boolean posicaoValida (int linha, int coluna) {
		if ((linha < 0 || linha >= numero_linhas) || (coluna < 0 || coluna >= numero_colunas)) return false;
		
		return true;
	}
	
	/**
	 * Verifica se a posicao dada na entrada esta livre. 
	 * @param linha
	 * @param coluna
	 * @return True: Se a posicao estiver livre; False: Se a posicao estiver ocupada.
	 * @throws Exception: Se a posicao inserida nao existir.
	 */
	public boolean isPosicaoLivre (int linha, int coluna) throws Exception {
		if (!(posicaoValida(linha, coluna))) {
			throw new Exception("Posicao inexistente.");
		}
		
		if (sala[linha][coluna] == LIVRE) return true;
		
		return false;
	}
	
	/**
	 * Define a posicao dada como livre ou ocupada a partir dos parametros.
	 * @param linha
	 * @param coluna
	 * @param statusPosicao
	 * @return True: Se a posicao estiver livre e se queira colocar um obstaculo nessa
	 * posicao. Se a posicao estiver ocupada e se queira deixar a posicao livre; 
	 * False: Se a posicao estiver ocupada e se queira inserir um obstaculo.
	 * @throws Exception: Se a entrada inserida for invalida.
	 */
	public boolean setPosicao (int linha, int coluna, char statusPosicao) {
		if ((linha < 0 || linha >= numero_linhas) || (coluna < 0 || coluna >= numero_colunas)) {
			return false;
		}
		
		if (statusPosicao == LIVRE) {
			sala[linha][coluna] = LIVRE;
			return true;
		}
		else {
			sala[linha][coluna] = OCUPADO;
			return true;
		}
	}
	
	/**
	 * Compara uma sala com outra previamente criada.
	 * @param outraSala
	 * @return True: Se as salas apresentarem os mesmos obstaculos; False: Se apresentarem
	 * obstaculos diferentes ou tamanhos diferentes.
	 */
	public boolean equals (Sala outraSala) {
		if (outraSala.getNumPosicoesHorizontais() != numero_linhas || outraSala.getNumPosicoesVerticais() != numero_colunas) {
			return false;
		}
		else {
			for (int i = 0; i < numero_linhas; i++) {
				for (int j = 0; j < numero_colunas; j++) {
					if (sala[i][j] != outraSala.sala[i][j]) {
						return false;
					}
				}
			}
			return true;
		}
	}
}
