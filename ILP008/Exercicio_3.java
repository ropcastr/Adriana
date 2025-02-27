import java.util.Scanner;

public class Exercicio_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 3 - Reajuste salarial");
        System.out.print("Insira o valor do salário atual: ");
        float salario = scanner.nextFloat();
        System.out.print("Insira o valor do percentual de reajuste: ");
        float reajuste = scanner.nextFloat();
        float novoSalario = salario + (salario * reajuste / 100);
        System.out.printf("Novo salário: %.2f\n\n", novoSalario);
    }
}
