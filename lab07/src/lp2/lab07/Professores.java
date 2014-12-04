package lp2.lab07;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um professor.
 * @author Paulo Vinicius da Silva Soares - Matrícula: 114110478
 * @version 1.5 - 30/11/2014
 */
public class Professores extends Contribuinte {
	private final static double TRIBUTACAO_UM_SALARIO = 0.05;
	private final static double TRIBUTACAO_ATE_CINCO_SALARIOS = 0.1;
	private final static double TRIBUTACAO_ACIMA_CINCO_SALARIOS = 0.2;
	private final static double SALARIO_MINIMO_BRUTO = 724.0;
	private double salario;
	private double despesaMaterialDidatico;
	private static double somaRiquezasProf;
	private static double limiarRiquezaProf;
	private static List<Professores> professores = new ArrayList<Professores>();
	
	/**
	 * Constroi um contribuinte professor a partir dos parametros passados.
	 * @param nomeContribuinte O nome do contribuinte.
	 * @param numeroContribuinte O numero de cadastro do contribuinte.
	 * @param salarioMensal O salario recebido em um mes.
	 * @param despesas As despesas com material didatico.
	 * @param valorCarro O valor do carro, se possuir.
	 * @param valorCasa O valor da casa, se possuir.
	 * @throws Exception Caso algum dos valores passados sejam negativos ou
	 * strings vazias.
	 */
	public Professores(String nomeContribuinte, String numeroContribuinte, double salarioMensal, double despesas, double valorCarro, double valorCasa) throws Exception {
		super(nomeContribuinte, numeroContribuinte, valorCarro, valorCasa);
		if (salarioMensal < 0 || despesas < 0) throw new Exception("Os valores não podem ser negativos.");
		this.salario = salarioMensal;
		this.despesaMaterialDidatico = despesas;
		calculaTributacao();
		descontoTributacao();
		if (!verificaSeExiste(nomeContribuinte, numeroContribuinte)) {
			professores.add(this);
			numeroDeContribuintes++;
		}
	}

	@Override
	protected void calculaTributacao() {
		if (salario / SALARIO_MINIMO_BRUTO <= 1) {
			tributacao = salario * TRIBUTACAO_UM_SALARIO;
		}
		else if (salario / SALARIO_MINIMO_BRUTO > 1 && salario / SALARIO_MINIMO_BRUTO <= 5) {
			tributacao = salario * TRIBUTACAO_ATE_CINCO_SALARIOS;
		}
		else tributacao = salario * TRIBUTACAO_ACIMA_CINCO_SALARIOS;
	}

	@Override
	protected void descontoTributacao() {
		descontos = despesaMaterialDidatico * 0.5;
	}
	
	private static void atualizaLimiarRiqueza () {
		somaRiquezasProf = 0.0;
		limiarRiquezaProf = 0.0;
		for (Professores a: professores) {
			somaRiquezasProf += (a.sinaisRiqueza);
		}
		limiarRiquezaProf = (somaRiquezasProf / professores.size()) * 1.5;
	}
	
	/**
	 * Atualiza a lista de professores e consequentemente o limiar de riqueza
	 * destes. Caso ele esteja acima do limiar, sera atribuido um boolean true a ele.
	 * Caso contrario, sera atribuido um boolean false.
	 */
	public static void atualizaContribuintesLimiarRiqueza () {
		atualizaLimiarRiqueza();
		for (Professores a: professores) {
			if (a.sinaisRiqueza >= limiarRiquezaProf) {
				a.setRiquezaExcessiva(true);
			}
		}
	}
	
	private boolean verificaSeExiste (String nome, String numero) {
		for (Professores a: professores) {
			if (a.getNomeContribuinte().equals(nome) || a.getNumeroContribuinte().equals(numero)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return O salario mensal
	 */
	public double getSalario () {
		return salario;
	}
	
	/**
	 * 
	 * @return As despesas mensais em material didatico.
	 */
	public double getDespesas () {
		return despesaMaterialDidatico;
	}
	
	/**
	 * 
	 * @return O limiar de riqueza da classe.
	 */
	public static double getLimiarRiqueza () {
		return limiarRiquezaProf;
	}
	
	/**
	 * 
	 * @return A lista estatica da classe contendo os objetos.
	 */
	public static List<Professores> getProfessores () {
		return professores;
	}
	
	/**
	 * Retorna true se o nome e numero de contribuinte forem iguais.
	 */
	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof Professores)) {
			return false;
		}
		Professores outroTaxista = (Professores) obj;
		return outroTaxista.getNomeContribuinte().equals(getNomeContribuinte()) && outroTaxista.getNumeroContribuinte().equals(getNumeroContribuinte());
	}
	
	/**
	 * Efetua uma consulta na lista de professores e verifica se ha um contribuinte
	 * com os parametros passados.
	 * @param nome Nome de contribuinte
	 * @param numeroContr Numero de contribuinte
	 * @return Uma string contendo os dados do contribuinte, caso seja encontrado.
	 * Caso contrario retorna uma String contendo "Pessoa nao encontrada."
	 */
	public static String consultaContribuinte (String nome, String numeroContr) {
		for (Professores a: professores) {
			if (nome.equals(a.getNomeContribuinte()) && numeroContr.equals(a.getNumeroContribuinte())) {
				return "Nome: " + a.getNomeContribuinte() + " - Número de contribuinte: " + a.getNumeroContribuinte() +
						"\nTributação bruta: R$ " + a.getTributacao() + "\nDescontos: R$ " + a.getDescontos() + "\nTributação Total: R$ " + a.getTributacaoTotal() + "\n";
			}
		}
		return "Pessoa não encontrada.";
	}

}
