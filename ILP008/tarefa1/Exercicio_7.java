import java.util.Scanner;

public class Exercicio_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 7 - Maior que 10");
        System.out.println("Digite um valor: ");
        float valor = scanner.nextFloat();
        System.out.println(valor > 10 ? "É MAIOR QUE 10!" : "NÃO É MAIOR QUE 10!\n");
    }
}
