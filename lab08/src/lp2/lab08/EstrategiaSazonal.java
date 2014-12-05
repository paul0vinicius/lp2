package lp2.lab08;

import java.util.*;

/**
 * Estrategia sazonal de visualizacao de um determinado produto. Essa classe implementa a
 * interface "Estrategia".
 * @author Paulo Vinicius da Silva Soares - Matricula: 114110478
 * @version 1.4 - 04/12/2014
 */
public class EstrategiaSazonal implements Estrategia {
	private List<Opiniao> opinioesMaisRecentes = new ArrayList<Opiniao>();
	
	/**
	 * A nota eh calculada a partir da media aritmetica das tres opinioes mais recentes
	 * sobre o produto. Caso nao haja opinioes cadastradas ainda, o valor retornado eh zero.
	 */
	@Override
	public double calculaNotaNaMosca (List<Opiniao> opinioes) {
		if (opinioes.size() < 1) return 0;
		selecionaOpinioesMaisRecentes(opinioes);
		
		return calculaMedia(opinioesMaisRecentes);
	}
	
	/**
	 * O metodo retorna uma string contendo as duas opinioes mais recentes, com suas respectivas
	 * notas, comentarios e datas.
	 */
	@Override
	public String selecionaComentariosRelevantes (List<Opiniao> opinioes) {
		if (opinioes.size() < 1) return null;
		selecionaOpinioesMaisRecentes(opinioes);
		if (opinioesMaisRecentes.size() < 2) {
			return "Comentário mais relevante: " + "\nNota: " + opinioesMaisRecentes.get(0).getNotaProduto() + " || Comentário: " + opinioesMaisRecentes.get(0).getComentario();
		}
		
		return "Comentários mais relevantes: " + "\nNota: " + opinioesMaisRecentes.get(0).getNotaProduto() + " || Comentário: " + opinioesMaisRecentes.get(0).getComentario()
				+ " || Data do comentário: " + opinioesMaisRecentes.get(0).getDataDoComentario()
				+ "\nNota: " + opinioesMaisRecentes.get(1).getNotaProduto() + " || Comentário: " + opinioesMaisRecentes.get(1).getComentario()
				+ " || Data do comentário: " + opinioesMaisRecentes.get(1).getDataDoComentario() + "\n";
	}
	
	/**
	 * Retorna um List contendo as duas opinioes mais recentes sobre o produto.
	 */
	@Override
	public List<Opiniao> getOpinioesMaisRelevantes(List<Opiniao> opinioes) {
		selecionaOpinioesMaisRecentes(opinioes);
		return opinioesMaisRecentes;
	}

	/**
	 * Calcula a media aritmetica simples a partir das tres opinioes mais recentes sobre o produto.
	 * @param opinioesMaisRecentes List contendo as opinioes sobre o produto.
	 * @return Um double contendo a media aritmetica.
	 */
	private double calculaMedia (List<Opiniao> opinioesMaisRecentes) {
		int soma = 0;
		for (Opiniao a: opinioesMaisRecentes) {
			soma += a.getNotaProduto();
		}
		
		return soma / opinioesMaisRecentes.size();
	}
	
	/**
	 * Seleciona as tres opinioes mais recentes baseada nas datas dos comentarios.
	 * @param opinioes List contendo as opinioes
	 */
	private void selecionaOpinioesMaisRecentes (List<Opiniao> opinioes) {
		opinioesMaisRecentes.clear();
		List<Opiniao> copiaOpinioes = new ArrayList<Opiniao>(); // faz uma cópia de opiniões porque haverá alteração na lista.
		for (Opiniao a: opinioes) {
			copiaOpinioes.add(a);
		}
		
		if (copiaOpinioes.size() < 4) {
			for (int i = 0; i < copiaOpinioes.size(); i++){
				opinioesMaisRecentes.add(copiaOpinioes.get(i));
			}
		}
		else {
			for (int i = 0; i < 3; i++) {
				Date DataMaisAtual = copiaOpinioes.get(0).getDataDoComentario();
				Opiniao opiniaoMaisAtual = copiaOpinioes.get(0);
				for (Opiniao a: copiaOpinioes) {
					if (a.getDataDoComentario().after(DataMaisAtual)) {
						DataMaisAtual = a.getDataDoComentario();
						opiniaoMaisAtual = a;
					}
				}
				opinioesMaisRecentes.add(opiniaoMaisAtual);
				copiaOpinioes.remove(opiniaoMaisAtual);
			}
		}
	}
}

