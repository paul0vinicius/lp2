package lp2.lab08;

import java.util.*;

/**
 * Representa uma opiniao sobre o produto. Esta contem uma nota, comentario e sua data.
 * @author Paulo Vinicius da Silva Soares - Matricula: 114110478
 * @version 1.4 - 04/12/2014
 */
public class Opiniao {
	private int notaProduto;
	private String comentario;
	private GregorianCalendar dataDoComentario;
	
	/**
	 * Construtor da classe, recebe os parametros necessarios a construcao da classe.
	 * @param nota A nota do produto, que deve ser um inteiro entre -2 e 2 (inclusive).
	 * @param comentario O comentario pode conter ate 140 caracteres.
	 * @throws Exception Caso o comentario seja uma string vazia ou null e caso a nota seja invalida.
	 */
	public Opiniao (int nota, String comentario) throws Exception {
		if (comentario == null) throw new Exception("O comentário não pode ser uma string vazia ou nulo.");
		lancaExcecoes(nota, comentario);
		this.notaProduto = nota;
		this.comentario = comentario;
		this.dataDoComentario = new GregorianCalendar();
	}
	
	/**
	 * 
	 * @return A nota do produto.
	 */
	public int getNotaProduto () {
		return notaProduto;
	}
	
	/**
	 * 
	 * @return O comentario.
	 */
	public String getComentario () {
		return comentario;
	}

	/**
	 * 
	 * @return A data registrada do comentario.
	 */
	public Date getDataDoComentario () {
		return dataDoComentario.getTime();
	}
	
	/**
	 * Retorna uma string no formato: "Nota: X \n
	 * Comentario: X".
	 */
	@Override
	public String toString () {
		return "Nota: " + getNotaProduto() + "\nComentário: " + getComentario();
	}
	
	/**
	 * True: Caso o comentario, nota e a data sejam iguais; False: Caso contrario.
	 */
	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof Opiniao)) return false;
		Opiniao outraOpiniao = (Opiniao) obj;
		return outraOpiniao.getNotaProduto() == getNotaProduto() 
				&& outraOpiniao.getComentario().equals(getComentario())
				&& outraOpiniao.getDataDoComentario().equals(getDataDoComentario());
	}
	
	/**
	 * Testa as excecoes do construtor da classe.
	 * @param nota A nota da opiniao.
	 * @param comentario Comentario da opiniao.
	 * @throws Exception Caso a nota seja invalida (fora do intervalo) ou o comentario ultrapasse
	 * os 140 caracteres.
	 */
	private void lancaExcecoes (int nota, String comentario) throws Exception {
		if (!(nota >= -2 && nota <= 2)) {
			throw new Exception("A nota deve estar entre -2 e 2.");
		}
		if (comentario.length() > 140) {
			throw new Exception("Os caracteres não devem ultrapassar a quantidade de 140.");
		}
		if (comentario == "") {
			throw new Exception("O comentário não pode ser uma string vazia ou nulo.");
		}
	}
}
