package lp2.lab07;

/**
 * SuperClasse abstrata que abrange dados comuns a um contribuinte, este podendo
 * pertencer a varias categorias trabalhadoras.
 * @author Paulo Vinicius da Silva Soares - Matricula: 114110478
 * @version 1.4 - 30/11/2014
 */
public abstract class Contribuinte {
	public static int numeroDeContribuintes = 0;
	private String nomeContribuinte;
	private String numero;
	private boolean riquezaExcessiva = false;
	private double valorCasa;
	private double valorCarro;
	protected double sinaisRiqueza;
	protected double tributacao;
	protected double descontos;
	
	/**
	 * Constroi um objeto a partir dos dados recebidos.
	 * @param nome O nome do contribuinte.
	 * @param numeroContribuinte O numero de contribuinte.
	 * @param valorCarro O valor do carro, caso possua.
	 * @param valorCasa O valor da casa, caso possua.
	 * @throws Exception Caso os valores sejam negativos ou as Strings vazias.
	 */
	public Contribuinte (String nome, String numeroContribuinte, double valorCarro, double valorCasa) throws Exception {
		 if (!checaString(nome, numeroContribuinte)){
			throw new Exception("O nome não pode ser vazio ou null, nem o número de cadastro.");
		}
		this.valorCarro = valorCarro(valorCarro);
		this.valorCasa = valorCasa(valorCasa);
		this.sinaisRiqueza = this.valorCarro + this.valorCasa;
		this.nomeContribuinte = nome;
		this.numero = numeroContribuinte;
	}
	
	private double valorCarro (double valorCarro) throws Exception {
		if (!checaValor(valorCarro)) {
			throw new Exception("O valor do carro não pode ser negativo.");
		}
		return valorCarro;
	}
	
	private double valorCasa (double valorCasa) throws Exception {
		if (!checaValor(valorCasa)) {
			throw new Exception("O valor da casa não pode ser negativo.");
		}
		return valorCasa;
	}
	
	/**
	 * 
	 * @return O nome do contribuinte.
	 */
	public String getNomeContribuinte () {
		return nomeContribuinte;
	}
	
	/**
	 * 
	 * @return O numero do contribuinte.
	 */
	public String getNumeroContribuinte () {
		return numero;
	}
	
	/**
	 * 
	 * @return O valor da casa associado ao contribuinte.
	 */
	public double getValorcasa () {
		return valorCasa;
	}
	
	/**
	 * 
	 * @return O valor do carro associado ao contribuinte.
	 */
	public double getValorcarro () {
		return valorCarro;
	}
	
	/**
	 * 
	 * @return A soma dos bens (casa e carro) do contribuinte.
	 */
	public double getSinaisRiqueza () {
		return sinaisRiqueza;
	}
	
	/**
	 * 
	 * @return A tributacao bruta.
	 */
	public double getTributacao () {
		return tributacao;
	}
	
	/**
	 * 
	 * @return Os descontos a serem aplicados.
	 */
	public double getDescontos () {
		return descontos;
	}
	
	/**
	 * 
	 * @return A tributacao liquida.
	 */
	public double getTributacaoTotal () {
		if (tributacao - descontos < 0) return 0;
		else return tributacao - descontos;
	}
	
	/**
	 * 
	 * @return O boolean associado a limiar de riqueza.
	 */
	public boolean getAcimaDaLimiar () {
		return riquezaExcessiva;
	}
	
	/**
	 * Altera o status de riqueza excessiva do contribuinte. Caso este esteja acima
	 * da limiar, ele recebe um parametro true e tem seu atributo alterado para
	 * representar esse status.
	 * @param status true, caso esteja acima da limiar de riqueza.
	 */
	public void setRiquezaExcessiva (boolean status) {
		riquezaExcessiva = status;
	}
	
	/**
	 * Retorna o nome e o numero do contribuinte associado.
	 */
	@Override
	public String toString () {
		return "Nome: " + getNomeContribuinte() + " - Número de contribuinte: " + getNumeroContribuinte();
	}
	
	/**
	 * Metodo abstrato que sera definido nas classes filhas onde esta calcula
	 * o valor da tributacao bruta a ser paga.
	 */
	protected abstract void calculaTributacao ();
	
	/**
	 * Metodo abstrato que sera definido nas classes filhas onde esta calcula
	 * o valor do desconto a ser aplicado na tributacao bruta.
	 */
	protected abstract void descontoTributacao ();
	
	private boolean checaString (String nome, String numeroContribuinte) {
		if (nome == null || numeroContribuinte == null || nome.equals("") || numeroContribuinte.equals("")) return false;
		return true;
	}
	
	private boolean checaValor (double valor) {
		if (valor < 0) {
			return false;
		}
		return true;
	}
}
