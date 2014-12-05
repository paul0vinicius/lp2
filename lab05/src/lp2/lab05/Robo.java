package lp2.lab05;

/**
 * A classe cria um robo que se movimenta dentro de uma sala. Os movimentos incluem: Direita, esquerda,
 * cima e baixo. Para usar essa classe você precisa ter um objeto 'Sala' criado.
 * 
 * @author Paulo Vinicius da Silva Soares
 * @version 1.2 - 10/11/2014
 */
public class Robo {
	final static public String ESPACO_LIVRE = "- ";
	final static public String ESPACO_COM_OBSTACULO = "X ";
	final static public String ESPACO_COM_ROBO = "R ";
	private int energia;
	private int passos;
	private Sala salaDoRobo;
	private int linhaRobo, colunaRobo;
	int[] coordenadas = new int[2];
	
	
	public Robo (int energia, Sala salaRecebida) throws Exception {
		if (energia < 0) {
			throw new Exception("A energia do robô não pode ser negativa. Por favor, insira um valor acima de 0.");
		}
		else if (energia == 0) {
			throw new Exception("A energia do robô não pode ser igual à zero. Por favor, insira um valor acima de 0.");
		}
		if (salaRecebida == null) {
			throw new Exception("A sala ainda não foi criada. Por favor, crie uma sala.");
		}
		if (verificaSalaCheia(salaRecebida)) {
			throw new Exception("A sala está totalmente ocupada. Esvazie a sala antes de colocar o robô.");
		}
		this.energia = energia;
		this.passos = 0;
		this.salaDoRobo = salaRecebida;
		coordenadas = getCoordenadas();
		this.linhaRobo = coordenadas[0];
		this.colunaRobo = coordenadas[1];
	}
	
	private int[] getCoordenadas () throws Exception {
		int[] coordenada = new int[2];
		if (salaDoRobo.isVazia()) {
			salaDoRobo.setPosicao(0, 0, Sala.OCUPADO);
			coordenada[0] = 0;
			coordenada[1] = 0;
		}
		else {
			boolean achouLivre = false;
			for (int i = 0; i < salaDoRobo.getNumPosicoesHorizontais(); i++) {
				for (int j = 0; j < salaDoRobo.getNumPosicoesVerticais(); j++) {
					if (salaDoRobo.getSala()[i][j] == Sala.LIVRE) {
						coordenada[1] = j;
						salaDoRobo.setPosicao(i, j, Sala.OCUPADO);
						achouLivre = true;
						break;
					}
				}
				if (achouLivre) {
					coordenada[0] = i;
					break;
				}
			}
		}
		return coordenada;
	}
	
	private boolean verificaSalaCheia (Sala salaRecebida) {
		for (int i = 0; i < salaRecebida.getNumPosicoesHorizontais(); i++) {
			for (int j = 0; j < salaRecebida.getNumPosicoesVerticais(); j++) {
				if (salaRecebida.getSala()[i][j] != Sala.OBSTACULO) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Verifica a posicao do robo na sala.
	 * @return Uma lista contendo a posicao [linha, coluna] do robo.
	 */
	public int[] getPosicao () {
		int[] position = new int[2];
		position[0] = linhaRobo;
		position[1] = colunaRobo;
		return position;
	}
	
	/**
	 * Verifica a energia restante para a movimentacao do robo.
	 * @return A energia restante.
	 */
	public int getEnergia () {
		return energia;
	}
	
	/**
	 * Verifica quantos passos foram dados durante o jogo.
	 * @return O numero de passos dados.
	 */
	public int getPassos () {
		return passos;
	}
	
	/**
	 * Verifica o objeto 'Sala' associado ao robo.
	 * @return O objeto 'Sala'.
	 */
	public Sala getSalaDoRobo () {
		return salaDoRobo;
	}
	
	/**
	 * Movimenta o robo para cima, se possivel.
	 * @return True: Se a movimentacao for efetivada com sucesso; False: Se não houver movimento.
	 * @throws Exception: Se a coordenada inserida for invalida.
	 */
	public boolean andarParaCima () throws Exception {
		if (linhaRobo == 0) return false;
		if (salaDoRobo.isPosicaoLivre((linhaRobo - 1), colunaRobo) && energia > 0) {
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.LIVRE);
			passos++;
			linhaRobo--;
			energia--;
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.OCUPADO);
			return true;
		}
		return false;
	}
	
	/**
	 * Movimenta o robo para baixo, se possivel.
	 * @return True: Se a movimentacao for efetivada com sucesso; False: Se não houver movimento.
	 * @throws Exception: Se a coordenada inserida for invalida.
	 */
	public boolean andarParaBaixo () throws Exception {
		if (linhaRobo == (salaDoRobo.getNumPosicoesHorizontais() - 1)) return false;
		if (salaDoRobo.isPosicaoLivre((linhaRobo + 1), colunaRobo) && energia > 0) {
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.LIVRE);
			passos++;
			linhaRobo++;
			energia--;
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.OCUPADO);
			return true;
		}
		return false;
	}
	
	/**
	 * Movimenta o robo para a esquerda, se possivel.
	 * @return True: Se a movimentacao for efetivada com sucesso; False: Se não houver movimento.
	 * @throws Exception: Se a coordenada inserida for invalida.
	 */
	public boolean andarParaEsquerda () throws Exception {
		if (colunaRobo == 0) return false;
		if (salaDoRobo.isPosicaoLivre(linhaRobo, (colunaRobo - 1)) && energia > 0) {
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.LIVRE);
			passos++;
			colunaRobo--;
			energia--;
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.OCUPADO);
			return true;
		}
		return false;
	}
	
	/**
	 * Movimenta o robo para a esquerda, se possivel.
	 * @return True: Se a movimentacao for efetivada com sucesso; False: Se não houver movimento.
	 * @throws Exception: Se a coordenada inserida for invalida.
	 */
	public boolean andarParaDireita () throws Exception {
		if (colunaRobo == (salaDoRobo.getNumPosicoesVerticais() - 1)) return false;
		if (salaDoRobo.isPosicaoLivre(linhaRobo, (colunaRobo + 1)) && energia > 0) {
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.LIVRE);
			passos++;
			colunaRobo++;
			energia--;
			salaDoRobo.setPosicao(linhaRobo, colunaRobo, Sala.OCUPADO);
			return true;
		}
		return false;
	}
	
	/**
	 * Compara o robo com outro, previamente criado, e afirma se os dois são iguais ou não.
	 * @param outroRobo
	 * @return True: Se forem iguais; False: Se forem diferentes.
	 */
	public boolean equals (Robo outroRobo) {
		if (salaDoRobo.equals(outroRobo.getSalaDoRobo())) {
			if (getPosicao()[0] == outroRobo.getPosicao()[0] && getPosicao()[1] == outroRobo.getPosicao()[1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Desenha na interface do usuario a sala e o robo onde estes se encontram na matriz. 
	 */
	public void desenhaRobo () {
		for (int i = 0; i < salaDoRobo.getNumPosicoesHorizontais(); i++) {
			for (int j = 0; j < salaDoRobo.getNumPosicoesVerticais(); j++) {
				if (salaDoRobo.getSala()[i][j] == Sala.LIVRE){
					System.out.print(ESPACO_LIVRE);
				}
				else if (salaDoRobo.getSala()[i][j] == Sala.OBSTACULO) {
					System.out.print(ESPACO_COM_OBSTACULO);
				}
				else System.out.print(ESPACO_COM_ROBO);
			}
			System.out.println();
		}
		System.out.println("\n");
	}
}
