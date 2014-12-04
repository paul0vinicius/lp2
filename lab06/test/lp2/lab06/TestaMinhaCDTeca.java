package lp2.lab06;

import java.util.ArrayList;

import org.junit.*;

/**
 * Testes da classe CDTeca.
 * A classe sala foi utilizada nesse LAB para testar o metodo equals das classes CD e CDTeca.
 * @author Paulo Vinicius da Silva Soares
 * @version 1.4 - 21/11/2014
 *
 */
public class TestaMinhaCDTeca {
	private MinhaCDTeca cdTeca;
	private MinhaCDTeca cdTeca2;
	private CD cdMarina;
	private CD cdRihanna;
	private CD cdColdplay;
	private ArrayList<CD> listaCDs;
	private ArrayList<CD> listaCDs2;
	
	private void criaListaCDs () {
		listaCDs = new ArrayList<CD>();
		listaCDs2 = new ArrayList<CD>();
		listaCDs.add(cdMarina);
		listaCDs.add(cdColdplay);
		listaCDs2.add(cdRihanna);
		listaCDs2.add(cdColdplay);
	}
	
	@Before
	public void criaObjetos () throws Exception {
		cdTeca = new MinhaCDTeca();
		cdMarina = new CD("Electra Heart", "Marina and the Diamonds", 15);
		cdRihanna = new CD("Loud", "Rihanna", 15);
		cdColdplay = new CD("X&Y", "Coldplay", 11);
	}
	
	@Test
	public void testaConstrutor () {
			Assert.assertEquals(0, cdTeca.numeroDeCDs());
	}
	
	@Test
	public void testaAdicionaCD () throws Exception {
		try {
			cdTeca.adicionaCD(null);
			Assert.fail("Deve retornar exceção, pois o cd não pode ser null");
		} catch (Exception e) {
			Assert.assertEquals("O CD inserido não pode ser null", e.getMessage());
		}
		cdTeca.adicionaCD(cdMarina);
		cdTeca.adicionaCD(cdColdplay);
		cdTeca.adicionaCD(cdRihanna);
		Assert.assertEquals(3, cdTeca.numeroDeCDs());
	}
	
	@Test
	public void testaAdicionaCDs () throws Exception {
		criaListaCDs();
		
		try {
			cdTeca.adicionaCDs(null);
			Assert.fail("Deve retornar exceção, pois a lista não pode ser null.");
		} catch (Exception e) {
			Assert.assertEquals("A lista de CDs não pode ser null.", e.getMessage());
		}
		cdTeca.adicionaCDs(listaCDs);
		Assert.assertEquals(2, cdTeca.numeroDeCDs());
		cdTeca.adicionaCDs(listaCDs2);
		Assert.assertEquals(4, cdTeca.numeroDeCDs());
	}
	
	@Test
	public void testaPesquisaCD () throws Exception {
		cdTeca.adicionaCD(cdMarina);
		cdTeca.adicionaCD(cdColdplay);
		cdTeca.adicionaCD(cdRihanna);
		Assert.assertEquals(cdTeca.pesquisaCD("Electra Heart"), cdMarina);
		Assert.assertEquals(cdTeca.pesquisaCD("X&Y"), cdColdplay);
		Assert.assertEquals(cdTeca.pesquisaCD("Loud"), cdRihanna);
		Assert.assertEquals(cdTeca.pesquisaCD("Viva La Vida"), null);
	}
	
	@Test
	public void testaRemoveCD () throws Exception {
		Assert.assertEquals(null, cdTeca.removeCD("Electra Heart"));
		cdTeca.adicionaCD(cdMarina);
		Assert.assertEquals(1, cdTeca.numeroDeCDs());
		Assert.assertEquals(cdMarina, cdTeca.removeCD("Electra Heart"));
		Assert.assertEquals(0, cdTeca.numeroDeCDs());
	}
	
	@Test
	public void testaRemoveCDs () throws Exception {
		criaListaCDs();
		
		try {
			cdTeca.removeCDs(listaCDs);
			Assert.fail("Deve lançar exceção, a lista não pode ser removida da CDTeca porque esta se encontra vazia.");
		} catch (Exception e) {
			Assert.assertEquals("Não existem CDs na CDTeca, então não há como remover CDs.", e.getMessage());
		}
		cdTeca.adicionaCD(cdMarina);
		cdTeca.adicionaCD(cdColdplay);
		
		Assert.assertTrue(cdTeca.removeCDs(listaCDs2));
		Assert.assertEquals(1, cdTeca.numeroDeCDs());
		Assert.assertFalse(cdTeca.removeCDs(listaCDs2));
		cdTeca.removeCDs(listaCDs);
		Assert.assertEquals(0, cdTeca.numeroDeCDs());
	}
	
	@Test
	public void testaEquals() throws Exception {
		cdTeca2 = new MinhaCDTeca();
		Sala sala = new Sala(10,10);
		Assert.assertFalse(cdTeca2.equals(sala));
		Assert.assertFalse(cdTeca.equals(sala));
		Assert.assertTrue(cdTeca.equals(cdTeca2));
		cdTeca.adicionaCD(cdColdplay);
		Assert.assertFalse(cdTeca.equals(cdTeca2));
		cdTeca2.adicionaCD(cdColdplay);
		Assert.assertTrue(cdTeca.equals(cdTeca2));
	}
	
	@Test
	public void testaToString () throws Exception {
		cdTeca.adicionaCD(cdMarina);
		cdTeca.adicionaCD(cdColdplay);
		cdTeca.adicionaCD(cdRihanna);
		Assert.assertEquals("---------- A CDTeca possui os seguintes CDs: ----------\n\n"
				+ "Electra Heart - Autor/Artista: Marina and the Diamonds\n"
				+ "X&Y - Autor/Artista: Coldplay\n"
				+ "Loud - Autor/Artista: Rihanna\n", cdTeca.toString());
	}
	
	@Test
	public void testaCDTecaOrdenadaPorArtista () throws Exception {
		criaListaCDs();
		cdTeca.adicionaCDs(listaCDs);
		Assert.assertEquals("---------- A CDTeca possui os seguintes CDs: ----------\n\n"
				+ "X&Y - Autor/Artista: Coldplay\n"
				+ "Electra Heart - Autor/Artista: Marina and the Diamonds\n", cdTeca.cdTecaOrdenadaPorArtista());
		cdTeca.adicionaCDs(listaCDs2);
		Assert.assertEquals("---------- A CDTeca possui os seguintes CDs: ----------\n\n"
				+ "X&Y - Autor/Artista: Coldplay\n"
				+ "X&Y - Autor/Artista: Coldplay\n"
				+ "Electra Heart - Autor/Artista: Marina and the Diamonds\n"
				+ "Loud - Autor/Artista: Rihanna\n", cdTeca.cdTecaOrdenadaPorArtista());
	}
}
