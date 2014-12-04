import java.util.Scanner;

public class Pessoa {

	private String nome;
	private String cpf;
	
	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String checkName(){
		return nome;
	}
	
	public String checkCPF(){
		return cpf;
	}
	
	public void setCPF(String cpf){
		this.cpf = cpf;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Scanner input = new Scanner(System.in);
		String saida;
	
		Pessoa pessoa1 = new Pessoa("João", "113");
		Pessoa pessoa2 = new Pessoa("João", "113");
		
		pessoa1.setCPF("114");
		System.out.println(pessoa1.cpf);
		
	}

}
