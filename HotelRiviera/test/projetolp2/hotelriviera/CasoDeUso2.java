/*
 * Caso de Uso2: Criar, pesquisar, atualizar e remover Servi�os
 O hotel disponibiliza um conjunto de servi�os para seus h�spedes. Esses servi�os s�o inclu�dos nos
 contratos e englobam os quartos (servi�o b�sico) e servi�os adicionais como aluguel de carros, 
 babysitter e restaurante.

 Todo servi�o possui uma descri��o e pre�o por unidade, podendo ter ainda a descri��o por cada unidade
 do servi�o. Cada tipo de servi�o ter� suas especifidades. Por exemplo, os quartos podem ou n�o ter cama 
 adicional (o que influencia no pre�o do quarto) e ainda comportar quantidade de pessoas diferentes. Note 
 que existem diferentes categorias de quartos (PRESIDENCIAL, LUXO_SIMPLES, LUXO_DUPLO, LUXO_TRIPLO, 
 EXECUTIVO_SIMPLES, EXECUTIVO_SIMPLES, EXECUTIVO_DUPLO, EXECUTIVO_TRIPLO), os quartos presidenciais 
 comportam at� 4 pessoas e os demais at� 3 pessoas. 

 Um servi�o adicional � o aluguel de carros. Nesse caso, tem-se a identifica��o do carro e a quantidade 
 de di�rias. As di�rias dependem da categoria do aluguel (AUTOMOVEL_LUXO, AUTOMOVEL_EXECUTIVO) e as
 taxas extras referentes ao seguro e tanque cheio. Note que essas faixas s�o fixas.

 Deve ser poss�vel criar, pesquisar, atualizar e remover servi�os do hotel.
 */

package projetolp2.hotelriviera;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CasoDeUso2 {

	private Contrato contrato1;
	private Contrato contrato2;
	
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
		
		Quarto quarto1 = new LuxoSimples(3);
		Quarto quarto2 = new Presidencial(4);
		
		contrato1 = new Contrato(hospede1, "2314-4313-3123-1234", 7, quarto1);
		contrato2 = new Contrato(hospede2, "2314-5455-3198-1094", 12, quarto2);
	}
	
	@Test
	public void TestarAluguel() {
		AluguelCarro aluguel1 = new AluguelCarro(new CarroExecutivo(false, true));
		contrato1.adicionaAdicionais(aluguel1);
		Assert.assertEquals(contrato1.getAdicionais().get(0).getCobranca(), 160.00, 0.01);
		
		AluguelCarro aluguel2 = new AluguelCarro(new CarroLuxo(true, false));
		contrato2.adicionaAdicionais(aluguel2);
		Assert.assertEquals(contrato2.getAdicionais().get(0).getCobranca(), 250.00, 0.01);
	}
	
	@Test
	public void TestarBabysitter() throws Exception {
		try {
			new Babysitter(false, 15);
			Assert.fail("O número de horas não pode exceder 12.");
		} catch (Exception e) {
			Assert.assertEquals("O número de horas não pode ser negativo ou acima de 12.", e.getMessage());
		}
		try {
			new Babysitter(false, -1);
			Assert.fail("O número de horas não pode ser negativo.");
		} catch (Exception e) {
			Assert.assertEquals("O número de horas não pode ser negativo ou acima de 12.", e.getMessage());
		}
		
		Babysitter babysitter1 = new Babysitter(false, 5);
		contrato1.adicionaAdicionais(babysitter1);
		Assert.assertEquals(contrato1.getAdicionais().get(0).getCobranca(), 125.00, 0.01);
		
		Babysitter babysitter2 = new Babysitter(true, 5);
		contrato2.adicionaAdicionais(babysitter2);
		Assert.assertEquals(contrato2.getAdicionais().get(0).getCobranca(), 250.00, 0.01);
	}
	
	@Test
	public void TestarRefeicoes() {
		Refeicoes refeicoes1 = new Refeicoes();
		refeicoes1.acrescentaRefeicao(64.70);
		refeicoes1.acrescentaRefeicao(32.80);
		contrato1.adicionaAdicionais(refeicoes1);
		Assert.assertEquals(contrato1.getAdicionais().get(0).getCobranca(), 97.50, 0.01);
		
		Refeicoes refeicoes2 = new Refeicoes();
		refeicoes2.acrescentaRefeicao(90.50);
		refeicoes2.acrescentaRefeicao(12.80);
		refeicoes2.acrescentaRefeicao(25.30);
		contrato2.adicionaAdicionais(refeicoes2);
		Assert.assertEquals(contrato2.getAdicionais().get(0).getCobranca(), 128.60, 0.01);
	}
	

	@Test
	public void testaPesquisarServicos() throws Exception{
		AluguelCarro aluguel1 = new AluguelCarro(new CarroExecutivo(false, true));
		contrato1.adicionaAdicionais(aluguel1);
		Babysitter babysitter1 = new Babysitter(false, 5);
		contrato1.adicionaAdicionais(babysitter1);
		Refeicoes refeicoes1 = new Refeicoes();
		refeicoes1.acrescentaRefeicao(64.70);
		refeicoes1.acrescentaRefeicao(32.80);
		contrato1.adicionaAdicionais(refeicoes1);
		
		ArrayList<Adicional> servicos = contrato1.getAdicionais();
		int quantidade_servicos = servicos.size();
		String servico = "Nenhum servico.";
		
		//Busca de AluguelCarro		
		for (int i=0; i < quantidade_servicos; i++){
			if (servicos.get(i) instanceof AluguelCarro)
				servico = servicos.get(i).toString();
		}
		Assert.assertEquals(" - Aluguel de carro - \nCarro Executivo\nSeguro: true\nTanque cheio: false\n"
														+ "Diaria: 60.0\nCobranca Total: R$160.0", servico);
		System.out.println(servico);
		//Busca de Babysitter
		for (int i=0; i < quantidade_servicos; i++){
			if (servicos.get(i) instanceof Babysitter)
				servico = servicos.get(i).toString();
		}
		Assert.assertEquals(" - Babysitter - \nValor por hora: 25.0\nValor total de serviço: 125.0", servico);		
		System.out.println(servico);
		//Busca de Refeicoes
		for (int i=0; i < quantidade_servicos; i++){
			if (servicos.get(i) instanceof Refeicoes)
				servico = servicos.get(i).toString();
		}
		Assert.assertEquals(" - Refeicoes - \nValor total consumido: 97.5", servico);		
		System.out.println(servico);
	}
	
	@Test
	public void testaRemoverServicos() throws Exception{
		AluguelCarro aluguel1 = new AluguelCarro(new CarroExecutivo(false, true));
		contrato1.adicionaAdicionais(aluguel1);
		Babysitter babysitter1 = new Babysitter(false, 5);
		contrato1.adicionaAdicionais(babysitter1);
		Refeicoes refeicoes1 = new Refeicoes();
		refeicoes1.acrescentaRefeicao(64.70);
		refeicoes1.acrescentaRefeicao(32.80);
		contrato1.adicionaAdicionais(refeicoes1);
		
		ArrayList<Adicional> servicos = contrato1.getAdicionais();
		int quantidade_servicos = servicos.size();
		Assert.assertEquals(3, contrato1.getAdicionais().size(), 1);
		for (int i=0; i <= quantidade_servicos - 1; i++){
			System.out.println("i: " + i + " quant: " +quantidade_servicos);
			if (servicos.get(i) instanceof AluguelCarro){
				contrato1.getAdicionais().remove(i);
				break;
			}
		}
		Assert.assertEquals(2, contrato1.getAdicionais().size(), 0.1);
		for (int i=0; i < quantidade_servicos- 2; i++){
			if (servicos.get(i) instanceof Babysitter){
				contrato1.getAdicionais().remove(i);
				break;
			}
		}
		Assert.assertEquals(1, contrato1.getAdicionais().size(), 0.1);
		for (int i=0; i < quantidade_servicos - 3; i++){
			if (servicos.get(i) instanceof Refeicoes) {
				contrato1.getAdicionais().remove(i);
				break;
			}
			
		}
		Assert.assertEquals(1, contrato1.getAdicionais().size());
		try{
			servicos.remove(0);
		}catch(Exception e){
			System.out.println(e.getCause());		
		}
	}

}
