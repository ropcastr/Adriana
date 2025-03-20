import java.util.Scanner;

public class Exercicio_9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 9 - Custo das maçãs");
        System.out.print("Informe o número de maçãs: ");
        int macas = scanner.nextInt();
        float custoTotal = macas < 12 ? macas * 1.30f : macas * 1.00f;
        System.out.printf("Custo total: %.2f\n\n", custoTotal);
    }
}
