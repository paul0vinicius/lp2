package lp2.lab06;

import org.junit.*;

/**
 * Testes da classe CD.
 * A classe Sala foi utilizada nesse LAB para testar o metodo equals das classes CD e CDTeca.
 * @author Paulo Vinicius Soares.
 * @version 1.4 - 21/11/2014
 *
 */
public class TestaCD {
	private CD cd;
	private CD cd2;
	private CD cd3;
	private static final int NUM_FAIXAS_CONSTRUTOR = 10;
	private static final int NUM_FAIXAS_CONSTRUTOR_ZERO = 0;
	private static final int NUM_FAIXAS_CONSTRUTOR_NEGATIVO = -1;
	
	@Test
	public void testaConstrutor () throws Exception {
		
		try {
			new CD(null, "Fernando e Sorocaba", NUM_FAIXAS_CONSTRUTOR);
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		try {
			new CD("", "Fernando e Sorocaba", NUM_FAIXAS_CONSTRUTOR);
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		try {
			new CD("Parachutes", null, NUM_FAIXAS_CONSTRUTOR);
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		try {
			new CD("Parachutes", "", NUM_FAIXAS_CONSTRUTOR);
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		try {
			new CD("X&Y", "Coldplay", NUM_FAIXAS_CONSTRUTOR_ZERO);
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O valor de M não pode ser menor que 1.", e.getMessage());
		}
		
		try {
			new CD("X&Y", "Coldplay", NUM_FAIXAS_CONSTRUTOR_NEGATIVO);
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O valor de M não pode ser menor que 1.", e.getMessage());
		}
		
		try {
			new CD("Mylo Xyloto", "");
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		try {
			new CD("Mylo Xyloto", null);
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		try {
			new CD("", "Coldplay");
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		try {
			new CD(null, "Coldplay");
			Assert.fail("Deve lançar exceção.");
		} catch (Exception e) {
			Assert.assertEquals("O título do CD não pode ser vazio, assim como o seu autor.", e.getMessage());
		}
		
		cd = new CD("X&Y", "Coldplay", NUM_FAIXAS_CONSTRUTOR);
		Assert.assertEquals(NUM_FAIXAS_CONSTRUTOR, cd.getM());
		Assert.assertEquals("Coldplay", cd.getAutorCD());
		Assert.assertEquals("X&Y", cd.getTituloCD());
		Assert.assertEquals("", cd.getTrilhaPrincipal());
		
		for (int i = 1; i <= cd.getM(); i++) {
			Assert.assertEquals("", cd.getFaixa(i));
		}
	}
	
	@Test
	public void testaConstrutorDefault () throws Exception {
		cd2 = new CD("Dollhouse", "Melanie Martinez");
		Assert.assertEquals(NUM_FAIXAS_CONSTRUTOR, cd2.getM());
		Assert.assertEquals("Dollhouse", cd2.getTituloCD());
		Assert.assertEquals("Melanie Martinez", cd2.getAutorCD());
	}
	
	@Test
	public void testaEstaVazio () throws Exception {
		cd = new CD("Humbug", "Arctic Monkeys", 13);
		for (int i = 1; i <= cd.getM(); i++) {
			Assert.assertEquals("", cd.getFaixa(i));
		}
	}
	
	@Test
	public void testaCadastraCD () throws Exception {
		cd = new CD("Humbug", "Arctic Monkeys", 3);
		Assert.assertTrue(cd.cadastroFaixasCD("My Propeller"));
		Assert.assertTrue(cd.cadastroFaixasCD("Secret Door"));
		Assert.assertTrue(cd.cadastroFaixasCD("Potion Approaching"));
		
		try {
			cd.setTrilhaPrincipal("Pretty Visitors");
		} catch (Exception e) {
			Assert.assertEquals("A trilha não existe no CD, então não pode ser escolhida como trilha principal.", e.getMessage());
		}
		
		cd.setTrilhaPrincipal("My Propeller");
		Assert.assertEquals("Humbug", cd.getTituloCD());
		Assert.assertEquals("Arctic Monkeys", cd.getAutorCD());
		Assert.assertEquals("My Propeller", cd.getFaixa(1));
		Assert.assertTrue(cd.getTrilhaPrincipal().equals("My Propeller"));
		Assert.assertEquals("Secret Door", cd.getFaixa(2));
		Assert.assertEquals("Potion Approaching", cd.getFaixa(3));
		Assert.assertEquals(null, cd.getFaixa(4));
		Assert.assertFalse(cd.cadastroFaixasCD("Cornerstone"));
	}
	
	@Test
	public void testaEquals () throws Exception {
		Sala sala = new Sala(10, 10);
		cd = new CD("The New Classic", "Iggy Azalea", 16);
		cd2 = new CD("1000 forms of Fear", "Sia", 15);
		cd3 = new CD("Matangi", "M.I.A", 15);
		Assert.assertFalse(cd.equals(sala));
		Assert.assertFalse(cd.equals(cd2));
		Assert.assertFalse(cd.equals(cd3));
		Assert.assertFalse(cd2.equals(cd3));
		cd2 = new CD("The New Classic", "Iggy Azalea", 16);
		Assert.assertTrue(cd.equals(cd2));
		Assert.assertFalse(cd.equals(cd3));
		Assert.assertFalse(cd3.equals(cd2));	
	}
	
	@Test
	public void testaToString () throws Exception {
		cd = new CD("Melophobia", "Cage the Elephant", 10);
		cd2 = new CD("1000 forms of Fear", "Sia", 15);
		cd3 = new CD("Matangi", "M.I.A", 15);
		Assert.assertEquals("Melophobia - Autor/Artista: Cage the Elephant", cd.toString());
		Assert.assertEquals("1000 forms of Fear - Autor/Artista: Sia", cd2.toString());
		Assert.assertEquals("Matangi - Autor/Artista: M.I.A", cd3.toString());
	}
	
	@Test
	public void testaCompareTo () throws Exception {
		cd = new CD("Ultraviolence", "Lana del Rey", 15);
		cd2 = new CD("Hideaway", "Kiesza", 1);
		cd3 = new CD("Matangi", "M.I.A", 15);
		Assert.assertTrue(cd.compareTo(cd2) > 0);
		Assert.assertTrue(cd.compareTo(cd3) < 0);
		Assert.assertTrue(cd2.compareTo(cd) < 0);
		Assert.assertTrue(cd2.compareTo(cd3) < 0);
		Assert.assertTrue(cd3.compareTo(cd) > 0);
		Assert.assertTrue(cd3.compareTo(cd2) > 0);
		cd3 = new CD("Ultraviolence", "Lana del Rey", 15);
		Assert.assertEquals(0, cd3.compareTo(cd));
	}
}
