package lp2.lab07;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um caminhoneiro
 * @author Paulo Vinicius da Silva Soares - Matrícula: 114110478
 * @version 1.5 - 30/11/2014
 */
public class Caminhoneiro extends Rodoviarios {
	private static final int IMPOSTO_FIXO = 500;
	private double toneladasAno;
	private static double somaRiquezasCam;
	private static double limiarRiquezaCam;
	private static List<Caminhoneiro> caminhoneiros = new ArrayList<Caminhoneiro>();
	
	/**
	 * Constroi um contribuinte caminhoneiro a partir dos parametros passados.
	 * @param nomeContribuinte O nome do contribuinte.
	 * @param numeroContribuinte O numero de cadastro do contribuinte.
	 * @param toneladasAno As toneladas carregadas durante um ano.
	 * @param quilometros_percorridos Os quilometros percorridos em um ano.
	 * @param valorCarro O valor do carro, se possuir.
	 * @param valorCasa O valor da casa, se possuir.
	 * @throws Exception Caso algum dos valores passados sejam negativos ou
	 * strings vazias.
	 */
	public Caminhoneiro (String nomeContribuinte, String numeroContribuinte, double toneladasAno, double quilometros_percorridos, double valorCarro, double valorCasa) throws Exception {
		super(nomeContribuinte, numeroContribuinte, quilometros_percorridos, valorCarro, valorCasa);
		if (verificaSeENegativo(toneladasAno)) {
			throw new Exception("Os quilômetros percorridos e as toneladas não podem ser negativas.");
		}
		this.toneladasAno = toneladasAno;
		calculaTributacao();
		descontoTributacao();
		if (!verificaSeExiste(nomeContribuinte, numeroContribuinte)) {
			caminhoneiros.add(this);
			numeroDeContribuintes++;
		}
	}

	@Override
	protected void calculaTributacao() {
		if (toneladasAno <= 10) this.tributacao = IMPOSTO_FIXO;
		else this.tributacao = IMPOSTO_FIXO + (toneladasAno - 10) * 100;
	}
	
	/**
	 * Retorna true se o nome e numero de contribuinte forem iguais.
	 */
	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof Caminhoneiro)) {
			return false;
		}
		Caminhoneiro outroCaminhoneiro = (Caminhoneiro) obj;
		return outroCaminhoneiro.getNomeContribuinte().equals(getNomeContribuinte()) && outroCaminhoneiro.getNumeroContribuinte().equals(getNumeroContribuinte());
	}
	
	private boolean verificaSeENegativo (double numero) {
		if (numero < 0) {
			return true;
		}
		return false;
	}
	
	private boolean verificaSeExiste (String nome, String numero) {
		for (Caminhoneiro a: caminhoneiros) {
			if (a.getNomeContribuinte().equals(nome) || a.getNumeroContribuinte().equals(numero)) {
				return true;
			}
		}
		return false;
	}
	
	private static void atualizaLimiarRiqueza () {
		somaRiquezasCam = 0.0;
		limiarRiquezaCam = 0.0;
		for (Caminhoneiro a: caminhoneiros) {
			somaRiquezasCam += (a.sinaisRiqueza);
		}
		limiarRiquezaCam = (somaRiquezasCam / caminhoneiros.size()) * 1.5;
	}
	
	/**
	 * Atualiza a lista de caminhoneiros e consequentemente o limiar de riqueza
	 * destes. Caso ele esteja acima do limiar, sera atribuido um boolean true a ele.
	 * Caso contrario, sera atribuido um boolean false.
	 */
	public static void atualizaContribuintesLimiarRiqueza () {
		atualizaLimiarRiqueza();
		for (Caminhoneiro a: caminhoneiros) {
			if (a.sinaisRiqueza >= limiarRiquezaCam) {
				a.setRiquezaExcessiva(true);
			}
		}
	}
	
	/**
	 * 
	 * @return O numero de caminhoneiros cadastrados.
	 */
	public static int getNumeroDeCaminhoneiros () {
		return caminhoneiros.size();
	}
	
	/**
	 * 
	 * @return A lista estatica da classe contendo os objetos.
	 */
	public static List<Caminhoneiro> getCaminhoneiros () {
		return caminhoneiros;
	}
	
	/**
	 * 
	 * @return O limiar de riqueza da classe.
	 */
	public static double getLimiarRiqueza () {
		return limiarRiquezaCam;
	}
	
	/**
	 * Efetua uma consulta na lista de caminhoneiros e verifica se ha um contribuinte
	 * com os parametros passados.
	 * @param nome Nome de contribuinte
	 * @param numeroContr Numero de contribuinte
	 * @return Uma string contendo os dados do contribuinte, caso seja encontrado.
	 * Caso contrario retorna uma String contendo "Pessoa nao encontrada."
	 */
	public static String consultaContribuinte (String nome, String numeroContr) {
		for (Caminhoneiro a: caminhoneiros) {
			if (nome.equals(a.getNomeContribuinte()) && numeroContr.equals(a.getNumeroContribuinte())) {
				return "Nome: " + a.getNomeContribuinte() + " - Número de contribuinte: " + a.getNumeroContribuinte() +
						"\nTributação bruta: R$ " + a.getTributacao() + "\nDescontos: R$ " + a.getDescontos() + "\nTributação Total: R$ " + a.getTributacaoTotal() + "\n";
			}
		}
		return "Pessoa não encontrada.";
	}
}
