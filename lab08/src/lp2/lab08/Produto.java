package lp2.lab08;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o produto de um supermercado. 
 * @author Paulo Vinicius da Silva Soares - Matricula: 114110478
 * @version 1.4 - 04/12/2014
 */
public class Produto {
	private String nomeProduto;
	protected List<Opiniao> opinioesProduto;
	protected List<Opiniao> opinioesMaisRelevantesSazonal;
	protected double notaNaMosca;
	protected String[] comentariosMaisRelevantes;
	private Estrategia formaAvaliar = new EstrategiaSimples();
	
	/**
	 * Construtor da classe.
	 * @param nome Recebe o nome do produto.
	 * @throws Exception Caso o nome do produto seja invalido (null ou string vazia).
	 */
	public Produto (String nome) throws Exception {
		lancaExcecoesNome(nome);
		opinioesProduto = new ArrayList<Opiniao>();
		opinioesMaisRelevantesSazonal = new ArrayList<Opiniao>();
		this.nomeProduto = nome;
		this.comentariosMaisRelevantes = new String[2];
	}
	
	/**
	 * Adiciona um Objeto Opiniao a lista de opinioes.
	 * @param opiniao Opiniao fornecida sobre o produto.
	 */
	public void adicionaOpinioes (Opiniao opiniao) {
		opinioesProduto.add(opiniao);
	}
	
	/**
	 * @return A nota naMosca baseado na lista de opinioes e no modo de visualizacao do produto.
	 */
	public double getNotaNaMosca () {
		return formaAvaliar.calculaNotaNaMosca(opinioesProduto);
	}
	
	/**
	 * Exibe os comentarios mais relevantes sobre o produto de acordo com o modo de visualizacao.
	 * @return Uma String contendo os comentarios.
	 * @throws Exception Caso a lista de comentarios esteja vazia.
	 */
	public String getComentarios () throws Exception {
		String comentarios = formaAvaliar.selecionaComentariosRelevantes(opinioesProduto);
		if (comentarios == null) {
			throw new Exception("Não existem comentários relevantes.");
		}
		return comentarios;
	}
	
	/**
	 * @return O nome do produto.
	 */
	public String getNome () {
		return nomeProduto;
	}
	
	/**
	 * @return A Lista de opinioes relevantes.
	 */
	public List<Opiniao> getOpinioesRelevantes () {
		return formaAvaliar.getOpinioesMaisRelevantes(opinioesProduto);
	}
	
	/**
	 * @return A lista de todas as opinioes do produto.
	 */
	public List<Opiniao> getOpinioesProduto () {
		return opinioesProduto;
	}
	
	/**
	 * Define a forma de visualizacao do produto: Sazonal ou simples.
	 * @param novaEstrategia Objeto Estrategia que define a exibicao.
	 */
	public void setFormaVisualizacao (Estrategia novaEstrategia) {
		formaAvaliar = novaEstrategia;
	}
	
	/**
	 * Testa excecoes para o parametro nome caso este seja uma string vazia ou null.
	 * @param nome O nome do produto.
	 * @throws Exception Caso este seja uma string vazia ou null.
	 */
	private void lancaExcecoesNome (String nome) throws Exception {
		if (nome == "" || nome == null) {
			throw new Exception("O nome do produto não pode ser vazio ou nulo.");
		}
	}
	
	
	
}
