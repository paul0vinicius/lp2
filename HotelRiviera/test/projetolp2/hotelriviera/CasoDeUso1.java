/*
    Caso de Uso1: Criar, pesquisar e atualizar Hospedes
    
	Um hospede representa um cliente do hotel, contendo informa��es do mesmo, 
	tais como nome, cpf e data de nascimento. Cada hospede estar� associado a
	um dos contratos gerenciados pelo hotel.

	Deve ser poss�vel criar, pesquisar, atualizar informa��es de um h�spede.
 */

package projetolp2.hotelriviera;

import org.junit.*;

import java.util.*;

public class CasoDeUso1 {
	
	public Contrato contrato1;
	public Contrato contrato2;
	GregorianCalendar dataHospede1 = new GregorianCalendar(1990, 01, 22);
	GregorianCalendar dataHospede2 = new GregorianCalendar(1990, 02, 28);
	
	
	@Before
	public void CriarContratos() throws Exception{
		Hospede hospede1 = new Hospede ("Jorge Ferreira Amaral",
										"Av. Campinas, 567, Tambau, Joao Pessoa - PB",
										"234.674.897-45",
										"(83) 8546-5435",
										new GregorianCalendar(1990, 01, 22));
		
		Hospede hospede2 = new Hospede ("Caio ALima Albuqerque",
										"Rua Florencia, 134, Boa Viagem, Reife - PE",
										"675.976.453-76",
										"(82) 3546-5876",
										new GregorianCalendar(1990, 02, 28));
		
		Quarto quarto1 = new LuxoSimples(2);
		Quarto quarto2 = new Presidencial (3);
		
		contrato1 = new Contrato(hospede1, "2314-4313-3123-1234", 7, quarto1);
		contrato2 = new Contrato(hospede2, "2314-5455-3198-1094", 12, quarto2);
	}

	@Test
	public void testaPesquisarInformacoes() {
		Hospede hospede1 = contrato1.getHospede();
		Hospede hospede2 = contrato2.getHospede();
		Quarto quarto1 = contrato1.getQuarto();
		Quarto quarto2 = contrato2.getQuarto();
		
		Assert.assertTrue(hospede1.getNomeHospede().equals("Jorge Ferreira Amaral"));
		Assert.assertTrue(hospede2.getNomeHospede().equals("Caio ALima Albuqerque"));
		Assert.assertTrue(hospede1.getEndereco().equals("Av. Campinas, 567, Tambau, Joao Pessoa - PB"));
		Assert.assertTrue(hospede2.getEndereco().equals("Rua Florencia, 134, Boa Viagem, Reife - PE"));
		Assert.assertTrue(hospede1.getCPF().equals("234.674.897-45"));
		Assert.assertTrue(hospede2.getCPF().equals("675.976.453-76"));
		Assert.assertTrue(hospede1.getTelefoneContato().equals("(83) 8546-5435"));
		Assert.assertTrue(hospede2.getTelefoneContato().equals("(82) 3546-5876"));
		Assert.assertTrue(hospede1.getDataNascimento().equals(dataHospede1));
		Assert.assertTrue(hospede2.getDataNascimento().equals(dataHospede2));
		
		
		Assert.assertTrue(contrato1.getNumeroCartao().equals("2314-4313-3123-1234"));
		Assert.assertTrue(contrato2.getNumeroCartao().equals("2314-5455-3198-1094"));
		Assert.assertEquals(contrato1.getNumeroDias(), 7);
		Assert.assertEquals(contrato2.getNumeroDias(), 12);
		
		Assert.assertEquals(quarto1.getNumeroPessoas(),2); 
		Assert.assertEquals(quarto2.getNumeroPessoas(), 3);
		Assert.assertEquals(quarto1.getValorDiaria(), 520, 1);
		Assert.assertEquals(quarto2.getValorDiaria(), 1200, 1);
	}
	
	public void testaAtualizarInformacoes() throws Exception {
		Hospede novohospede = new Hospede ( "Novolino da Silva e Costa",
											"Av. Nova Morada, 001, Geisel, Joao Pessoa - PB",
											"587.749.123-45",
											"(83) 8875-4387",
											new GregorianCalendar(1990, 02, 28));
		
		contrato1.setHospede(novohospede); // Redefinido o hospede do contrato, atualizando as informacoes
		Hospede hospede = contrato1.getHospede();
		Assert.assertTrue(hospede.getNomeHospede().equals("Novolino da Silva e Costa"));
		Assert.assertTrue(hospede.getEndereco().equals("Av. Nova Morada, 001, Geisel, Joao Pessoa - PB"));
		Assert.assertTrue(hospede.getCPF().equals("587.749.123-45"));
		Assert.assertTrue(hospede.getTelefoneContato().equals("(83) 8875-4387"));
		
		contrato1.setNumeroCartao("4785-7842-5647-8758");
		Assert.assertTrue(contrato1.getNumeroCartao().equals("4785-7842-5647-8758"));
		contrato1.setNumeroDias(9);
		Assert.assertEquals(contrato1.getNumeroDias(), 9);
		
		contrato1.setQuarto(new ExecutivoDuplo(2));
		Assert.assertEquals(contrato1.getQuarto().getNumeroPessoas(), 2, 1); 
		Assert.assertEquals(contrato1.getQuarto().getValorDiaria(), 385, 1);
	}
	
	public void testaCriacaoDeContratos(){
		Assert.assertFalse(contrato1.equals(null));
		Assert.assertFalse(contrato2.equals(null));
	}
}
