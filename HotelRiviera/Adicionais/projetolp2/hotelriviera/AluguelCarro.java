package projetolp2.hotelriviera;

public class AluguelCarro implements Adicional {
	Carro carro;
	
	public AluguelCarro (Carro carro) {
		this.carro = carro;
	}
	@Override
	public double getValorServico() {
		return getCobranca();
	}

	@Override
	public double getCobranca() {
		return carro.totalAPagar();
	}
	
	@Override
	public String toString(){
		return " - Aluguel de carro - \n" + carro + "\nCobranca Total: R$" + getCobranca();
	}
}
