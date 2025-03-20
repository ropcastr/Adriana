import java.util.Scanner;

public class Exercicio_11 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 11 - Pode votar");
        System.out.print("Informe o ano atual: ");
        int anoAtual = scanner.nextInt();
        System.out.print("Informe o ano de nascimento: ");
        int anoNasc = scanner.nextInt();
        int idade = anoAtual - anoNasc;
        System.out.println(idade >= 16 ? "Pode votar\n" : "Não pode votar\n");

    }
}
