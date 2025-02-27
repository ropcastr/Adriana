import java.util.Scanner;

public class Exercicio_10 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Exercício 10 - Média do aluno");
        System.out.print("Informe a nota 1: ");
        float nota1 = scanner.nextFloat();
        System.out.print("Informe a nota 2: ");
        float nota2 = scanner.nextFloat();
        float media = (nota1 + nota2) / 2;
        System.out.printf("Média: %.2f - %s\n\n", media, media >= 6 ? "Aprovado" : "Reprovado");

		}

	}