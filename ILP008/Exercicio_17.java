import java.util.Scanner;


public class Exercicio_17 {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 17 - Média LP1");
        System.out.print("Informe a Nota da P1: ");
        float p1 = scanner.nextFloat();
        System.out.print("Informe a Nota do E1: ");
        float e1 = scanner.nextFloat();
        System.out.print("Informe a Nota do E2: ");
        float e2 = scanner.nextFloat();
        System.out.print("Informe a Nota do API: ");
        float api = scanner.nextFloat();
        System.out.print("Informe a Nota X: ");
        float x = scanner.nextFloat();
        System.out.print("Informe a Nota da SUB: ");
        float sub = scanner.nextFloat();

        // Calculo da média por partes, facilitando a leitura. Mesmo porque estava dando mensagem de multiplas ocorrencias
        float mediaParcial = p1 * 0.6f + ((e1 + e2) / 2) * 0.4f;
        float mediaFinal = (mediaParcial * 0.5f) + (Math.max((mediaParcial - 5.9f), 0) /
                           (mediaParcial - 5.9f)) * (api * 0.5f) + x + (sub * 0.3f);

        System.out.printf("Média final LP1: %.2f\n", mediaFinal);

        scanner.close();
	}
}

