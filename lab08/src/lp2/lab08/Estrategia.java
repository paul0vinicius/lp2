package lp2.lab08;

import java.util.*;

/**
 * Implementa uma interface de estrategia para a visualizacao de um determinado produto. Este produto
 * contem uma opiniao, uma nota e uma data como atributos. A exibicao desses dados depende da forma como a estrategia
 * eh selecionada.
 * @author Paulo Vinicius da Silva Soares - Matricula: 114110478
 * @version 1.3 - 04/12/2014
 *
 */
public interface Estrategia {
	
	/**
	 * Retorna a media das opinioes do produto.
	 * @param opinioes ArrayList contendo todas as opinioes sobre o produto.
	 * @return Um double contendo a media das notas do produto.
	 */
	public double calculaNotaNaMosca (List<Opiniao> opinioes);
	
	/**
	 * Seleciona os comentarios mais relevantes do ArrayList de opinioes.
	 * @param opinioes ArrayList contendo todas as opinioes sobre o produto.
	 * @return Uma String contendo os comentarios mais relevantes formatados
	 * no modelo "Nota: X || Comentario: X" ou "Nota: X || Comentario: X || Data do comentario: X" 
	 */
	public String selecionaComentariosRelevantes (List<Opiniao> opinioes);
	
	/**
	 * Retorna a lista contendo as opinioes mais relevantes.
	 * @param opinioes ArrayList contendo todas as opinioes sobre o produto.
	 * @return Um ArrayList contendo as opinioes mais relevantes do produto.
	 */
	public List<Opiniao> getOpinioesMaisRelevantes (List<Opiniao> opinioes);
	
}
