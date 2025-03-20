import java.util.Scanner;

public class Exercicio_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 1 - Idade em dias");
        System.out.print("Digite a sua idade em anos (exatos): ");
        int anos = scanner.nextInt();
        System.out.print("Digite o restante da sua idade em meses (do último ano até o mês atual) ");
        int meses = scanner.nextInt();
        System.out.print("Digite o restante da sua idade em dias (do fim do último mês até o dia de hoje) ");
        int dias = scanner.nextInt();
        int totalDias = (anos * 365) + (meses * 30) + dias;
        System.out.println("Idade total em dias: " + totalDias + "\n");
    }
}
