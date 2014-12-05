package lp2.lab08;

import org.junit.*;
import java.util.*;

/**
 * Testes para a classe Produto.
 * @author Paulo Vinicius da Silva Soares - Matricula: 114110478
 * @version 1.4 - 04/12/2014
 */
public class TestaProduto {
	private Opiniao opiniao1, opiniao2, opiniao3, opiniao4, opiniao5;
	private Produto produtoTeste;
	private final String TITULO_PRODUTO = "Crocs";
	private Estrategia estrategiaSimples = new EstrategiaSimples();
	private Estrategia estrategiaSazonal = new EstrategiaSazonal();
	private final String COMENTARIO1 = "Amei, achei digno de Oscar!";
	private final String COMENTARIO2 = "Interessante e inovador. Bastante útil na cozinha.";
	private final String COMENTARIO3 = "Nada impressionante.";
	private final String COMENTARIO4 = "Um lixo, pelo amor de Deus! RETIREM ESSE PRODUTO AGORA DAS PRATELEIRAS.";
	private final String COMENTARIO5 = "Horrível, pior produto que já vi.";
	private final int NUMERO_OPINIOES = 4;
	private int notaProduto;
	
	@Before
	public void criaObjetos () throws Exception {
		produtoTeste = new Produto(TITULO_PRODUTO);
		setNotaProduto(2);
		opiniao1 = new Opiniao(notaProduto, COMENTARIO1);
		
		Thread.sleep(2000);
		
		setNotaProduto(1);
		opiniao2 = new Opiniao(notaProduto, COMENTARIO2);
		
		Thread.sleep(2000);
		
		setNotaProduto(0);
		opiniao3 = new Opiniao(notaProduto, COMENTARIO3);
		
		Thread.sleep(2000);
		
		setNotaProduto(-1);
		opiniao4 = new Opiniao(notaProduto, COMENTARIO4);
		
		Thread.sleep(2000);
		
		setNotaProduto(-2);
		opiniao5 = new Opiniao(notaProduto, COMENTARIO5);
	}
	
	@Test
	public void testaAdicionaOpinioes () {
		adicionaOpinioes();
		Assert.assertEquals(NUMERO_OPINIOES, produtoTeste.getOpinioesProduto().size());
		Assert.assertEquals("Crocs", produtoTeste.getNome());
	}
	
	@Test
	public void testaExcecoes () {
		try {
			new Produto("");
			Assert.fail("Deve lançar exceção porque o nome do produto não pode ser uma String vazia ou null.");
		} catch (Exception e) {
			Assert.assertEquals("O nome do produto não pode ser vazio ou nulo.", e.getMessage());
		}
		
		try {
			new Produto(null);
			Assert.fail("Deve lançar exceção porque o nome do produto não pode ser uma String vazia ou null.");
		} catch (Exception e) {
			Assert.assertEquals("O nome do produto não pode ser vazio ou nulo.", e.getMessage());
		}
		
		try {
			produtoTeste.setFormaVisualizacao(estrategiaSimples);
			produtoTeste.getComentarios();
			Assert.fail("Deve retornar null, a lista de comentários está vazia.");
		} catch (Exception e) {
			Assert.assertEquals("Não existem comentários relevantes.", e.getMessage());
		}
		
		try {
			produtoTeste.setFormaVisualizacao(estrategiaSazonal);
			produtoTeste.getComentarios();
			Assert.fail("Deve retornar null, a lista de comentários está vazia.");
		} catch (Exception e) {
			Assert.assertEquals("Não existem comentários relevantes.", e.getMessage());
		}
	}
	
	@Test
	public void testaExcecoesNotaNaMosca () {
		Assert.assertEquals(0, produtoTeste.getNotaNaMosca(), 0.01);
		produtoTeste.setFormaVisualizacao(estrategiaSazonal);
		Assert.assertEquals(0, produtoTeste.getNotaNaMosca(), 0.01);
		produtoTeste.adicionaOpinioes(opiniao1);
		Assert.assertEquals(2, produtoTeste.getNotaNaMosca(), 0.01);
		produtoTeste.setFormaVisualizacao(estrategiaSimples);
		Assert.assertEquals(2, produtoTeste.getNotaNaMosca(), 0.01);
	}
	
	@Test
	public void testaSelecionaComentariosRelevantes () throws Exception {
		produtoTeste.setFormaVisualizacao(estrategiaSimples);
		produtoTeste.adicionaOpinioes(opiniao1);
		Assert.assertEquals("Comentário mais relevante: "
				+ "\nNota: 2 || Comentário: Amei, achei digno de Oscar!", produtoTeste.getComentarios());
		produtoTeste.setFormaVisualizacao(estrategiaSazonal);
		Assert.assertEquals("Comentário mais relevante: "
				+ "\nNota: 2 || Comentário: Amei, achei digno de Oscar!", produtoTeste.getComentarios());
		adicionaOpinioes();
		produtoTeste.setFormaVisualizacao(estrategiaSimples);
		Assert.assertEquals("Comentário com avaliação mais positiva: "
				+ "\nNota: 2 || Comentário: Amei, achei digno de Oscar!"
				+ "\n\nComentário com avaliação mais negativa: "
				+ "\nNota: -2 || Comentário: Horrível, pior produto que já vi.\n", produtoTeste.getComentarios());
		Assert.assertEquals(0, produtoTeste.getNotaNaMosca(), 0.01);
		
		produtoTeste.setFormaVisualizacao(estrategiaSazonal);
		Assert.assertEquals(-1, produtoTeste.getNotaNaMosca(), 0.01);
		Assert.assertEquals("Comentários mais relevantes: "
				+ "\nNota: -2 || Comentário: Horrível, pior produto que já vi. || Data do comentário: " + produtoTeste.getOpinioesRelevantes().get(0).getDataDoComentario()
			+ "\nNota: -1 || Comentário: Um lixo, pelo amor de Deus! RETIREM ESSE PRODUTO AGORA DAS PRATELEIRAS. || Data do comentário: " + produtoTeste.getOpinioesRelevantes().get(1).getDataDoComentario() + "\n", produtoTeste.getComentarios());		
	}
	
	@Test
	public void testaNotasIguais () throws Exception {
		setNotaProduto(2);
		Opiniao opiniao = new Opiniao(notaProduto, COMENTARIO1);
		Opiniao opiniaoMesmaNota = new Opiniao(notaProduto, COMENTARIO2);
		Opiniao opiniaoMesmaNota2 = new Opiniao(notaProduto, COMENTARIO3);
		
		produtoTeste.adicionaOpinioes(opiniao);
		produtoTeste.adicionaOpinioes(opiniaoMesmaNota);
		produtoTeste.adicionaOpinioes(opiniaoMesmaNota2);
		System.out.println();
		Assert.assertEquals("Comentário com avaliação mais positiva: "
				+ "\nNota: 2 || Comentário: Amei, achei digno de Oscar!"
				+ "\n\nComentário com avaliação mais negativa: "
				+ "\nNota: 2 || Comentário: Interessante e inovador. Bastante útil na cozinha.\n", produtoTeste.getComentarios());
	}
	
	@Test
	public void testaNotasMenoresPrimeiro () throws Exception {
		adicionaOpinioesInvertidas();
		System.out.println(produtoTeste.getComentarios());
	}
	
	@Test
	public void testaGetListaOpinioesSimples () {
		ArrayList<Opiniao> opinioesRelevantes = new ArrayList<Opiniao>();
		opinioesRelevantes.add(opiniao2);
		opinioesRelevantes.add(opiniao5);
		adicionaOpinioes();
		List<Opiniao> opinioesProdutoTeste = produtoTeste.getOpinioesRelevantes();
		Assert.assertTrue(opinioesRelevantes.equals(opinioesProdutoTeste));
		
	}
	
	private void adicionaOpinioes() {
		produtoTeste.adicionaOpinioes(opiniao2);
		produtoTeste.adicionaOpinioes(opiniao3);
		produtoTeste.adicionaOpinioes(opiniao4);
		produtoTeste.adicionaOpinioes(opiniao5);
	}
	
	private void adicionaOpinioesInvertidas () {
		produtoTeste.adicionaOpinioes(opiniao5);
		produtoTeste.adicionaOpinioes(opiniao4);
		produtoTeste.adicionaOpinioes(opiniao3);
		produtoTeste.adicionaOpinioes(opiniao2);
	}
	
	private void setNotaProduto (int nota) {
		notaProduto = nota;
	}

}
