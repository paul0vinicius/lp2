package lp2.lab05;

/**
 * Classe de Testes para a classe Robo.
 * @author Paulo Vinicius da Silva Soares
 */
import org.junit.*;

public class TestaRobo {
	private Robo robo;
	private Sala sala;
	private final int ENERGIA = 10;
	private final int NUM_LINHAS = 10;
	private final int NUM_COLUNAS = 10;
	
	
	@Before
	public void criaObjetos () throws Exception {
		sala = new Sala(NUM_LINHAS, NUM_COLUNAS);
		robo = new Robo(ENERGIA, sala);
	}
	
	@Test
	public void testaCriaRobo () throws Exception {
		try {
			new Robo(-10, sala);
			Assert.fail("A energia do robô não pode ser negativa.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "A energia do robô não pode ser negativa. Por favor, insira um valor acima de 0.", e.getMessage());
		}
		
		try {
			new Robo(0, sala);
			Assert.fail("A energia do robô não pode ser igual à zero.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "A energia do robô não pode ser igual à zero. Por favor, insira um valor acima de 0.", e.getMessage());
		}
		
		try {
			sala = null;
			new Robo(ENERGIA, sala);
			Assert.fail("A sala não pode ser um elemento 'null', o objeto tem que ser criado.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "A sala ainda não foi criada. Por favor, crie uma sala.", e.getMessage());
		}
		
		try {
			sala = new Sala(NUM_LINHAS, NUM_COLUNAS);
			for (int i = 0; i < sala.getNumPosicoesHorizontais(); i++) {
				for (int j = 0; j < sala.getNumPosicoesVerticais(); j++) {
					sala.inserirObstaculo(i, j);
				}
			}
			new Robo(ENERGIA, sala);
			Assert.fail("A sala deve estar ocupada, dessa forma, não há espaço para o robô.");
			
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "A sala está totalmente ocupada. Esvazie a sala antes de colocar o robô.", e.getMessage());
		}
	}
		
		@Test
		public void testaPosicaoInicialRobo () throws Exception {
			Assert.assertTrue(robo.getPosicao()[0] == 0 && robo.getPosicao()[1] == 0);
			Assert.assertFalse(sala.isPosicaoLivre(0, 0));
		}
		
		@Test
		public void testaSalaComObstaculoPosicaoInicial () throws Exception {
			sala.inserirObstaculo(0, 0);
			robo = new Robo(ENERGIA, sala);
			Assert.assertTrue(robo.getPosicao()[0] == 0 && robo.getPosicao()[1] == 1);
		}
		
		@Test
		public void testaMovimentos () throws Exception {
			Assert.assertFalse(robo.andarParaCima());
			Assert.assertFalse(robo.andarParaEsquerda());
			Assert.assertTrue(robo.getEnergia() == ENERGIA);
			Assert.assertTrue(robo.getPassos() == 0);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 1 && robo.getPosicao()[1] == 0);
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertTrue(robo.getPosicao()[0] == 1 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.andarParaCima());
			Assert.assertTrue(robo.getPosicao()[0] == 0 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.andarParaEsquerda());
			Assert.assertTrue(robo.getPosicao()[0] == 0 && robo.getPosicao()[1] == 0);
		}
		
		@Test
		public void testaObstaculos () throws Exception {
			sala.inserirObstaculo(1, 0);
			sala.inserirObstaculo(0, 2);
			Assert.assertFalse(robo.andarParaBaixo());
			Assert.assertTrue(robo.getEnergia() == ENERGIA);
			Assert.assertTrue(robo.getPosicao()[0] == 0 && robo.getPosicao()[1] == 0);
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertTrue(robo.getPosicao()[0] == 0 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.getPassos() == 1 && robo.getEnergia() == 9);
			Assert.assertFalse(robo.andarParaDireita());
			Assert.assertTrue(robo.getPassos() == 1 && robo.getEnergia() == 9);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 1 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.getPassos() == 2 && robo.getEnergia() == 8);
			Assert.assertFalse(robo.andarParaEsquerda());
			Assert.assertTrue(robo.getPosicao()[0] == 1 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.getPassos() == 2 && robo.getEnergia() == 8);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 2 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.getPassos() == 3 && robo.getEnergia() == 7);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 3 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.getPassos() == 4 && robo.getEnergia() == 6);
		}
		
		@Test
		public void testaPosicaoEnergiaPassos () throws Exception {
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 1 && robo.getPosicao()[1] == 0);
			Assert.assertTrue(robo.getPassos() == 1 && robo.getEnergia() == 9);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 2 && robo.getPosicao()[1] == 0);
			Assert.assertTrue(robo.getPassos() == 2 && robo.getEnergia() == 8);
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertTrue(robo.getPosicao()[0] == 2 && robo.getPosicao()[1] == 1);
			Assert.assertTrue(robo.getPassos() == 3 && robo.getEnergia() == 7);
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertTrue(robo.getPosicao()[0] == 2 && robo.getPosicao()[1] == 2);
			Assert.assertTrue(robo.getPassos() == 4 && robo.getEnergia() == 6);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 3 && robo.getPosicao()[1] == 2);
			Assert.assertTrue(robo.getPassos() == 5 && robo.getEnergia() == 5);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 4 && robo.getPosicao()[1] == 2);
			Assert.assertTrue(robo.getPassos() == 6 && robo.getEnergia() == 4);
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertTrue(robo.getPosicao()[0] == 4 && robo.getPosicao()[1] == 3);
			Assert.assertTrue(robo.getPassos() == 7 && robo.getEnergia() == 3);
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertTrue(robo.getPosicao()[0] == 4 && robo.getPosicao()[1] == 4);
			Assert.assertTrue(robo.getPassos() == 8 && robo.getEnergia() == 2);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 5 && robo.getPosicao()[1] == 4);
			Assert.assertTrue(robo.getPassos() == 9 && robo.getEnergia() == 1);
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.getPosicao()[0] == 6 && robo.getPosicao()[1] == 4);
			Assert.assertTrue(robo.getPassos() == 10 && robo.getEnergia() == 0);
			Assert.assertFalse(robo.andarParaBaixo());
			Assert.assertFalse(robo.andarParaCima());
			Assert.assertFalse(robo.andarParaEsquerda());
			Assert.assertFalse(robo.andarParaDireita());
		}
		
		@Test
		public void testaNasQuinas () throws Exception {
			sala = new Sala(3,3);
			robo = new Robo(ENERGIA, sala);
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertTrue(robo.andarParaDireita());
			Assert.assertFalse(robo.andarParaDireita());
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertTrue(robo.andarParaBaixo());
			Assert.assertFalse(robo.andarParaBaixo());
			Assert.assertTrue(robo.andarParaEsquerda());
			Assert.assertTrue(robo.andarParaEsquerda());
			Assert.assertFalse(robo.andarParaEsquerda());
		}
		
		@Test
		public void testaEquals () throws Exception {
			Sala sala2 = new Sala(NUM_LINHAS, NUM_COLUNAS);
			Robo outroRobo = new Robo(5, sala2);
			
			Assert.assertTrue(robo.equals(outroRobo));
			sala2.inserirObstaculo(5, 0);
			Assert.assertFalse(robo.equals(outroRobo));
			sala.inserirObstaculo(5, 0);
			Assert.assertTrue(robo.equals(outroRobo));
			robo.andarParaDireita();
			Assert.assertFalse(robo.equals(outroRobo));
			outroRobo.andarParaDireita();
			Assert.assertTrue(robo.equals(outroRobo));
			sala.inserirObstaculo(9, 9);
			Assert.assertFalse(robo.equals(outroRobo));
			sala2.inserirObstaculo(9, 9);
			Assert.assertTrue(robo.equals(outroRobo));
			robo.andarParaEsquerda();
			Assert.assertFalse(robo.equals(outroRobo));
			outroRobo.andarParaEsquerda();
			Assert.assertTrue(robo.equals(outroRobo));
			robo.andarParaBaixo();
			Assert.assertFalse(robo.equals(outroRobo));
			outroRobo.andarParaBaixo();
			Assert.assertTrue(robo.equals(outroRobo));
			outroRobo.andarParaCima();
			Assert.assertFalse(robo.equals(outroRobo));
			robo.andarParaCima();
			Assert.assertTrue(robo.equals(outroRobo));
			
		}
		
		@Test
		public void testaInserirObstaculosNoRobo () throws Exception {
			Assert.assertFalse(sala.inserirObstaculo(0, 0));
			robo.andarParaBaixo();
			robo.andarParaBaixo();
			robo.andarParaBaixo();
			Assert.assertTrue(sala.inserirObstaculo(0, 0));
			Assert.assertFalse(sala.inserirObstaculo(3, 0));
		}
}
