import java.util.Scanner;


public class Exercicio_15 {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 15 - Salário com horas extras");
        System.out.print("Informe a quantidade de horas trabalhadas no mês: ");
        float horas = scanner.nextFloat();
        System.out.print("Informe o valor do salário por hora: ");
        float salHora = scanner.nextFloat();
        float horasNormais = Math.min(horas, 160); // 40h * 4 semanas
        float horasExtras = Math.max(0, horas - 160); // Se hrs menor que 160, 0h extras
        float salarioTotal = (horasNormais * salHora) + (horasExtras * salHora * 1.5f);
        System.out.printf("Salário total: %.2f\n\n", salarioTotal);
	}
}