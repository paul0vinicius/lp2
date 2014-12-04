package lp2.lab06;

import java.util.*;

/**
 * A classe representa um CD contendo o Autor/Artista, titulo, a trilha principal e o numero de faixas.
 * @author Paulo Vinicius da Silva Soares
 * @version 1.4 - 21/11/2014
 */
public class CD implements Comparable<CD> {
	private List<String> faixasCD;
	private String tituloCD;
	private String autorCD;
	private int M;
	private String trilhaPrincipal;
	
	/**
	 * Recebe informacoes sobre o CD.
	 * @param titulo O titulo do CD.
	 * @param autor O autor/artista do CD.
	 * @param M O numero de faixas do CD.
	 * @throws Exception Se o autor/artista for 'null' ou string vazia.
	 */
	public CD (String titulo, String autor, int M) throws Exception {
		if (titulo == null || titulo == "" || autor == null || autor == "") {
			throw new Exception("O título do CD não pode ser vazio, assim como o seu autor.");
		}
		if (M <= 0) {
			throw new Exception("O valor de M não pode ser menor que 1.");
		}
		faixasCD = new ArrayList<String>();
		this.tituloCD = titulo;
		this.autorCD = autor;
		this.M = M;
		this.trilhaPrincipal = "";
		adicionaMusicasVaziasCD();
	}
	
	/**
	 * Construtor default que recebe o titulo e o autor do CD. O valor default para o numero de faixas eh 10.
	 * @param titulo O titulo do CD.
	 * @param autor O autor/artista do CD.
	 * @throws Exception Se o autor/artista for 'null' ou string vazia.
	 */
	public CD (String titulo, String autor) throws Exception {
		if (titulo == null || titulo == "" || autor == null || autor == "") {
			throw new Exception("O título do CD não pode ser vazio, assim como o seu autor.");
		}
		faixasCD = new ArrayList<String>();
		this.tituloCD = titulo;
		this.autorCD = autor;
		this.M = 10;
		this.trilhaPrincipal = "";
		adicionaMusicasVaziasCD();
	}
	
	/**
	 * Insere as faixas no CD a partir dos parâmetros. O titulo do CD eh adicionado ao proximo elemento da lista.
	 * @param faixa O titulo da faixa.
	 * @return True: Se a operação for bem sucedida; False: se ocorrer o inverso.
	 */
	public boolean cadastroFaixasCD (String faixa) {
		int ocorrencia = faixasCD.indexOf("");
		if (ocorrencia <= faixasCD.size() && ocorrencia != -1) {
			faixasCD.set(ocorrencia, faixa);
			return true;
		}
		return false;
	}
	
	/**
	 * Seleciona uma faixa para ser a principal do CD.
	 * @param trilhaPrincipal A trilha escolhida para ser a principal.
	 * @throws Exception Caso não exista a trilha no CD.
	 */
	public void setTrilhaPrincipal (String trilhaPrincipal) throws Exception {
		if (!(faixasCD.contains(trilhaPrincipal))) {
			throw new Exception("A trilha não existe no CD, então não pode ser escolhida como trilha principal.");
		}
		this.trilhaPrincipal = trilhaPrincipal;
	}

	/**
	 * Retorna a faixa correspondente ao numero dado na entrada.
	 * @param faixa A faixa varia entre 1 e M. A diferenca entre o indice no ArrayList
	 * e as faixas do CD sao corrigidas na funcao.
	 * @return A faixa cadastrada naquele numero.
	 */
	public String getFaixa (int faixa) {
		faixa--;
		if (faixa >= M || faixa < 0) {
			return null;
		}
		
		return faixasCD.get(faixa);
	}
	
	/**
	 * Retorna a trilha principal registrada.
	 * @return Uma String contendo a trilha principal.
	 */
	public String getTrilhaPrincipal () {
		return trilhaPrincipal;
	}
	
	/**
	 * Retorna o titulo do CD.
	 * @return Uma String contendo o titulo do CD. 
	 */
	public String getTituloCD () {
		return tituloCD;
	}
	
	/**
	 * Retorna o autor do CD.
	 * @return Uma string contendo o autor do CD.
	 */
	public String getAutorCD () {
		return autorCD;
	}
	
	/**
	 * Retorna o numero de faixas 'M' do CD.
	 * @return Um inteiro contendo o numero de faixas.
	 */
	public int getM () {
		return M;
	}
	
	/**
	 * Metodo privado que adiciona as musicas vazias ao CD.
	 */
	private void adicionaMusicasVaziasCD () {
		for (int i = 0; i < M; i++) {
			faixasCD.add(i, "");
		}
	}

	/**
	 * Retorna a ordem de precedencia dos CDs por ordem alfabética do artista/autor.
	 */
	@Override
	public int compareTo (CD cdComparado) {
			return autorCD.compareTo(cdComparado.getAutorCD());
		}

	/**
	 * Retorna uma String contendo as informacoes basicas do CD, como o seu autor, titulo e o numero de faixas.
	 */
	@Override
	public String toString () {
		return tituloCD + " - Autor/Artista: " + autorCD;
	}
	
	/**
	 * True: Caso o titulo e o autor sejam iguais; False: Caso contrario.
	 */
	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof CD)) {
			return false;
		}
		CD cd2 = (CD) obj;
		
		return tituloCD.equals(cd2.getTituloCD()) && autorCD.equals(cd2.getAutorCD());
	}
}
