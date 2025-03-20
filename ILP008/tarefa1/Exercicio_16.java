import java.util.Scanner;


public class Exercicio_16 {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 16 - Gasto trimestral");
        float jan = 15000, fev = 23000, mar = 17000;
        float totalTrimestre = jan + fev + mar;
        float mediaMensal = totalTrimestre / 3;
        System.out.printf("Total trimestre: R$%.2f\nMédia mensal: R$%.2f\n\n", totalTrimestre, mediaMensal);
	}
}

