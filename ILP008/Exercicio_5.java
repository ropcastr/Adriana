import java.util.Scanner;


public class Exercicio_5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Exercício 5 - Salário do vendedor");
		System.out.print("Informe o salário fixo: ");
		float salarioFixo = scanner.nextFloat();
		System.out.print("Informe a quantidade de carros vendidos: ");
		int carrosVendidos = scanner.nextInt();
		System.out.print("Informe o valor recebido por carro vendido: ");
		float valorPorCarro = scanner.nextFloat();
		System.out.print("Informe o valor total das vendas: ");
		float totalVendas = scanner.nextFloat();
		float salarioFinal = salarioFixo + (carrosVendidos * valorPorCarro) + (totalVendas * 0.05f);
		System.out.printf("Salário final: %.2f\n\n", salarioFinal);

	}
}