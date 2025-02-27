import java.util.Scanner;

public class Exercicio_13 {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 13 - Ordem crescente");
        System.out.print("VInforme un múmero qualquer para o valor 1: ");
        float v1 = scanner.nextFloat();
        System.out.print("Informe un múmero qualquer para o valor 2: ");
        float v2 = scanner.nextFloat();
        System.out.printf("Valores em ordem crescente: %.2f | %.2f\n\n", Math.min(v1, v2), Math.max(v1, v2));

	}

}