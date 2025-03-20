import java.util.Scanner;


public class Exercicio_6 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Exercício 6 - Conversão Fahrenheit para Celsius
		System.out.println("Exercício 6 - Fahrenheit para Celsius");
		System.out.println("Temperatura em Fahrenheit: ");
		float fahrenheit = scanner.nextFloat();
		float celsius = (fahrenheit - 32) * 5 / 9;
		System.out.printf("Temperatura em Celsius: %.2f°C\n\n", celsius);

	}

}