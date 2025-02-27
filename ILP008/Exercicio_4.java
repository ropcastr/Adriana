import java.util.Scanner;

public class Exercicio_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 4 - Custo do carro");
        System.out.print("Informe o custo de fábrica: ");
        float custoFabrica = scanner.nextFloat();
        float custoFinal = custoFabrica + (custoFabrica * 0.28f) + (custoFabrica * 0.45f);
        System.out.printf("Custo final: %.2f\n\n", custoFinal);
    }
}
