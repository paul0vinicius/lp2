package lp2.lab06;

import java.util.*;

/**
 * Cria uma CDTeca que armazena objetos da Classe CD.
 * @author Paulo Vinicius da Silva Soares
 * @version 1.4 - 21/11/2014
 */
public class MinhaCDTeca {
	private List<CD> colecaoCDs = new ArrayList<CD>();
	
	/**
	 * Inicializa o ArrayList que contem os CDS.
	 */
	public MinhaCDTeca () {
		this.colecaoCDs = new ArrayList<CD>();
	}
	
	/**
	 * Adiciona um CD a CDTeca.
	 * @param cdInserido O CD a ser inserido.
	 * @throws Exception Caso o CD seja 'null'.
	 */
	public void adicionaCD (CD cdInserido) throws Exception {
		if (cdInserido == null) {
			throw new Exception("O CD inserido não pode ser null");
		}
		colecaoCDs.add(cdInserido);
	}
	
	/**
	 * Adiciona CDs a CDTeca a partir de um ArrayList que contem varios CDs.
	 * @param colecaoCDsRecebido O ArrayList contendo os CDs a serem inseridos.
	 * @throws Exception Caso o ArrayList passado seja 'null'.
	 */
	public void adicionaCDs (ArrayList<CD> colecaoCDsRecebido) throws Exception {
		if (colecaoCDsRecebido == null) {
			throw new Exception("A lista de CDs não pode ser null.");
		}
		for (int i = 0; i < colecaoCDsRecebido.size(); i++) {
			colecaoCDs.add(colecaoCDsRecebido.get(i));
		}
	}
	
	/**
	 * Remove CD a partir de um titulo inserido.
	 * @param tituloARemover O titulo do CD que sera removido da colecao.
	 * @return 'null': Se não houver um CD com o titulo passado; Retorna o CD caso
	 * este seja removido.
	 */
	public CD removeCD (String tituloARemover) {
		for (CD a: colecaoCDs) {
			if (a.getTituloCD().equals(tituloARemover)) {
				colecaoCDs.remove(a);
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Recebe um ArrayList de CDs a serem removidos.
	 * @param cdsARemover ArrayList contendo os objetos a serem removidos.
	 * @return True: Se pelo menos um CD for removido da CDTeca;
	 * False: Caso nenhum CD seja removido.
	 * @throws Exception Caso nao tenha sido adicionado nenhum CD a CDTeca e
	 * haja a tentativa de remocao.
	 */
	public boolean removeCDs (ArrayList<CD> cdsARemover) throws Exception {
		if (colecaoCDs.size() == 0) {
			throw new Exception("Não existem CDs na CDTeca, então não há como remover CDs.");
		}
		boolean removeu = false;
		for (CD a: cdsARemover) {
			CD cdremover = pesquisaCD(a.getTituloCD());
			if (cdremover != null) {
				removeCD(a.getTituloCD());
				removeu = true;
			}
		}
		return removeu;
	}
	
	/**
	 * Verifica se o CD está contido na CDTeca
	 * @param tituloPesquisado A String contendo o titulo do CD
	 * @return null: Caso o CD nao esteja na colecao; o CD, caso este
	 * esteja na colecao.
	 */
	public CD pesquisaCD (String tituloPesquisado) {
		for (CD a: colecaoCDs) {
			if (a.getTituloCD().equals(tituloPesquisado)) return a;
		}
		return null;
	}
	
	/**
	 * Verifica a quantidade de CDs na CDTeca.
	 * @return Um inteiro contendo o numero de CDs da CDTeca.
	 */
	public int numeroDeCDs () {
		return colecaoCDs.size();
	}
	
	/**
	 * Ordena os artistas de forma ascendente e os titulos dos CDs como consequencia.
	 * @return A string no formato "Titulo do CD - Artista do CD" onde os artistas estao ordenados
	 * em ordem alfabetica de forma ascendente.
	 */
	public String cdTecaOrdenadaPorArtista () {
		Collections.sort(colecaoCDs);
		return toString();
	}
	
	/**
	 * Retorna um resumo dos CDs contidos na CDTeca incluindo autor, titulo e numero de faixas
	 * de cada CD.
	 */
	@Override
	public String toString () {
		String retorno = new String ();
		for (CD a: colecaoCDs) {
			retorno += (a.toString() + "\n");
		}
		return "---------- A CDTeca possui os seguintes CDs: ----------\n\n" + retorno;
	}
	
	/**
	 * True: Caso a CDTeca contenha os mesmos CDs independente da ordem que
	 * estes aparecam.
	 */
	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof MinhaCDTeca)) return false;
		MinhaCDTeca cdTeca2 = (MinhaCDTeca) obj;
		for (CD a: colecaoCDs) {
			if (cdTeca2.pesquisaCD(a.getTituloCD()) == null) return false;
		}
		return true;
	}
}
