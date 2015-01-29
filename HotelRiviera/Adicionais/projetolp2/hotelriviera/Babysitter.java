package projetolp2.hotelriviera;

public class Babysitter implements Adicional {
	private double precoServico;
	private int horasServico;
	private final static double SERVICO_HORA_NORMAL = 25;
	private final static double SERVICO_HORA_DOBRADA = SERVICO_HORA_NORMAL * 2;
	
	public Babysitter (boolean horaDobrada, int horasServico) throws Exception {
		if (horasServico < 0 || horasServico > 12) throw new Exception("O número de horas não pode ser negativo ou acima de 12.");
		this.horasServico = horasServico;
		if (horaDobrada) precoServico = SERVICO_HORA_DOBRADA;
		else precoServico = SERVICO_HORA_NORMAL;
	}

	public double getPrecoServico() {
		return precoServico * horasServico;
	}

	@Override
	public double getValorServico() {
		return getPrecoServico();
	}

	@Override
	public double getCobranca() {
		return getPrecoServico();
	}
	
	@Override
	public String toString() {
		return " - Babysitter - \nValor por hora: " + precoServico + "\nValor total de serviço: " + getCobranca();
	}
}
