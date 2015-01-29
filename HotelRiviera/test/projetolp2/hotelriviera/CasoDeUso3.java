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
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.crypto.Data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CasoDeUso3 {

	private Contrato contrato1;
	private Contrato contrato2;
	
	@Before
	public void CriarContratos() throws Exception{
		Hospede hospede1 = new Hospede ("Jorge Ferreira Amaral",
										"Av. Campinas, 567, Tambau, Joao Pessoa - PB",
										"234.674.897-45",
										"(83) 8546-5435",
										new GregorianCalendar(1990, 01, 22));
		
		Hospede hospede2 = new Hospede ("Caio Lima Albuqerque",
										"Rua Florencia, 134, Boa Viagem, Reife - PE",
										"675.976.453-76",
										"(82) 3546-5876",
										new GregorianCalendar(1990, 02, 28));
		
		Quarto quarto1 = new LuxoTriplo(3);
		Quarto quarto2 = new ExecutivoTriplo(1);
		
		contrato1 = new Contrato(hospede1, "2314-4313-3123-1234", 7, quarto1);
		contrato2 = new Contrato(hospede2, "2314-5455-3198-1094", 12, quarto2);
		
		Babysitter babysitter1 = new Babysitter(false, 5);
		AluguelCarro aluguel1 = new AluguelCarro(new CarroExecutivo(false, true));
		contrato1.adicionaAdicionais(babysitter1);
		contrato2.adicionaAdicionais(aluguel1);
	}
	
	@Test
	public void testaCriarContratos() throws Exception {
		
	}
	
	
}

