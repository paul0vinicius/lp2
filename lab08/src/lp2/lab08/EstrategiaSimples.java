package lp2.lab08;

import java.util.*;

/**
 * Estrategia simples de visualizacao de um determinado produto. Essa classe implementa a
 * interface "Estrategia".
 * @author Paulo Vinicius da Silva Soares - Matricula: 114110478
 * @version 1.4 - 04/12/2014
 */
public class EstrategiaSimples implements Estrategia  {
	private Opiniao comentarioMaisRelevantePositivo;
	private Opiniao comentarioMaisRelevanteNegativo;
	
	/**
	 * A nota eh calculada a partir da media aritmetica de todas as opinioes sobre o produto. 
	 * Caso nao haja opinioes cadastradas ainda, o valor retornado eh zero.
	 */
	@Override
	public double calculaNotaNaMosca(List<Opiniao> opinioes) {
		if (opinioes.size() < 1) return 0;
		
		int soma = 0;
		for (Opiniao a: opinioes) {
			soma += a.getNotaProduto();
		}
		int media = soma / opinioes.size();
		return media;
	}

	/**
	 * O metodo retorna uma string contendo as duas opinioes mais recentes, com suas respectivas
	 * notas, comentarios e datas.
	 */
	@Override
	public String selecionaComentariosRelevantes(List<Opiniao> opinioes) {
		if (opinioes.size() < 1) {
			return null;
		}
		if (opinioes.size() == 1) {
			return "Comentário mais relevante: " + "\nNota: " + opinioes.get(0).getNotaProduto()
					+ " || Comentário: " + opinioes.get(0).getComentario();
		}
		
		selecionaOpinioesRelevantes(opinioes);
		
		return "Comentário com avaliação mais positiva: \n" + "Nota: " + comentarioMaisRelevantePositivo.getNotaProduto() + " || Comentário: " + comentarioMaisRelevantePositivo.getComentario()
		+ "\n\nComentário com avaliação mais negativa: \n" + "Nota: " + comentarioMaisRelevanteNegativo.getNotaProduto() + " || Comentário: " + comentarioMaisRelevanteNegativo.getComentario() + "\n";
	}

	/**
	 * Retorna um List contendo as duas opinioes mais recentes sobre o produto.
	 */
	@Override
	public List<Opiniao> getOpinioesMaisRelevantes(List<Opiniao> opinioes) {
		List<Opiniao> opinioesMaisRelevantes = new ArrayList<Opiniao>();
		selecionaComentariosRelevantes(opinioes);
		opinioesMaisRelevantes.add(comentarioMaisRelevantePositivo);
		opinioesMaisRelevantes.add(comentarioMaisRelevanteNegativo);
		return opinioesMaisRelevantes;
	}
	
	/**
	 * Seleciona as tres opinioes mais recentes baseada nas datas dos comentarios.
	 * @param opinioes List contendo as opinioes
	 */
	private void selecionaOpinioesRelevantes (List<Opiniao> opinioes) {
		int maior = opinioes.get(0).getNotaProduto();
		int menor = opinioes.get(1).getNotaProduto();
		comentarioMaisRelevantePositivo = opinioes.get(0);
		comentarioMaisRelevanteNegativo = opinioes.get(1);
		for (Opiniao a: opinioes) {
			if (a.getNotaProduto() > maior) {
				maior = a.getNotaProduto();
				comentarioMaisRelevantePositivo = a;
			}
			else if (a.getNotaProduto() == maior) {
				if (a.getDataDoComentario().before(comentarioMaisRelevantePositivo.getDataDoComentario())) {
					comentarioMaisRelevantePositivo = a;
				}
			}
			
			if (a.getNotaProduto() < menor) {
				menor = a.getNotaProduto();
				comentarioMaisRelevanteNegativo = a;
			}
			else if (a.getNotaProduto() == menor) {
				if (a.getDataDoComentario().before(comentarioMaisRelevanteNegativo.getDataDoComentario())) {
					comentarioMaisRelevanteNegativo = a;
				}
			}
		}
	}
}
