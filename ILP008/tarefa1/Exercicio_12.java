import java.util.Scanner;

public class Exercicio_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 12 - Maior valor");
        System.out.print("Informe un múmero qualquer para o valor 1: ");
        float v1 = scanner.nextFloat();
        System.out.print("Informe un múmero qualquer para o valor 2: ");
        float v2 = scanner.nextFloat();
        System.out.printf("Maior: %.2f\n\n", Math.max(v1, v2));
    }
}
