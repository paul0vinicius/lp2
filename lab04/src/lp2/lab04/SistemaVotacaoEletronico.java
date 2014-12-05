package lp2.lab04;

import java.util.ArrayList;
import java.util.Scanner;
import lp2.lab04.Eleitores;

/**
 * 
 * @author Paulo Vinicius Soares
 * @version 1.0 - 10 de Outubro de 2014
 *
 */
public class SistemaVotacaoEletronico {
	static ArrayList<Eleitores> eleitores = new ArrayList<> ();
	private static Scanner entradaString;
	private static Scanner entradaInt;
	
	public static void main(String[] args) {
		entradaString = new Scanner(System.in);
		entradaInt = new Scanner(System.in);
		boolean sair = false;
		
		System.out.println("------ Sistema de Votação Eletrônico ------\n");
		while (!sair){
			System.out.println("\nPara novo cadastro, pressione 1.\nPara ver todos os usuários cadastrados, pressione 2.\nPara consultar um único usuário, pressione 3.");
			System.out.print("Pressione -1 para sair\n");
			int opcao = entradaInt.nextInt();
			
			switch (opcao) {
			case -1:
				sair = true;
				break;
			case 1:
				System.out.println("Insira sua idade: ");
				int idade = entradaInt.nextInt();
				if (idade >= 16) {
					System.out.println("Você está apto à fazer o título. \nInsira seu nome: ");
					String nome = entradaString.nextLine();
					System.out.println("Insira os 11 dígitos do seu CPF: ");
					String cpf = entradaString.nextLine();
					System.out.println("Insira os dígitos do seu Título de Eleitor: ");
					String tituloEleitoral = entradaString.nextLine();
					Eleitores eleitor = new Eleitores(nome, cpf, tituloEleitoral, idade);
					eleitores.add(eleitor);
					System.out.println("\nEleitor cadastrado com sucesso:");
					System.out.println(eleitor);
				}
				else{
					System.out.println("Você tem menos de 16 anos, ou seja, não está apto à votar.\n");
				}
				break;
				
			case 2:
				imprimeEleitoresCadastrados (eleitores);
				break;
			
			case 3:
				if (eleitores.size() == 0) {
					System.out.println("Não há usuários cadastrados ainda. ");
				}
				else{
					System.out.println("Insira o nome do usuário: ");
					String nome = entradaString.nextLine();
					System.out.println(consultaIndividual(nome, eleitores));
				}
				break;
			
			default:
				System.err.println("\nOpção Inválida!");
			}
		}
	}
	
	private static void imprimeEleitoresCadastrados (ArrayList<Eleitores> eleitores) {
		for (int i = 0; i < eleitores.size(); i++){
			System.out.println("---------- USUÁRIO " + (i + 1) + " ----------");
			System.out.println(eleitores.get(i));
			System.out.println();
		}
	}
	
	private static String consultaIndividual (String nome, ArrayList<Eleitores> eleitores) {
		for (int i = 0; i < eleitores.size(); i++){
			if (eleitores.get(i).getName().equals(nome)) {
				return eleitores.get(i).toString();
			}
		}
		return "Usuário não encontrado.\n";
	}

}
