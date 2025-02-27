import java.util.Scanner;

public class Exercicio_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 8 - Positivo ou Negativo");
        System.out.print("Digite um valor qualquer: ");
        float valor = scanner.nextFloat();
        System.out.println(valor >= 0 ? "Positivo\n" : "Negativo\n");
    }
}
