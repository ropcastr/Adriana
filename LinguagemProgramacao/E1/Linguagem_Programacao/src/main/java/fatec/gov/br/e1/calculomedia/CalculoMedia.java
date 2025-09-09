package fatec.gov.br.e1.calculomedia;

import java.util.Scanner;

public class CalculoMedia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {

            limparTela();

            System.out.println("\n \u001B[33m Cálculo de Média - Linguagem de Programação: \u001B[0m \n");
            double E1 = lerNota(sc, "Digite a nota da Entrega 1 (E1): ");
            double E2 = lerNota(sc, "Digite a nota da Entrega 2 (E2): ");
            double P1 = lerNota(sc, "Digite a nota da Prova 1 (P1): ");
            double X  = lerNota(sc, "Digite a nota das Atividades Extras (X): ");
            double API = lerNota(sc, "Digite a nota do Aprendizado por Projeto Integrado (API): ");
            double SUB = lerNota(sc, "Digite a nota da Prova Substitutiva (SUB): ");

            double base = (P1 * 0.5) + (E1 * 0.2) + (E2 * 0.3) + X + (SUB * 0.15);

            double parte1 = base * 0.5;

            double parte2 = 0;
            if (base != 5.9) {
                double numerador = Math.max(base - 5.9, 0);
                parte2 = (numerador / (base - 5.9)) * API * 0.5;
            }

            double media = parte1 + parte2;

            if (media >= 6) {
                System.out.printf("\u001B[34mA média final do aluno é: %.2f\nParabéns, você foi aprovado!\u001B[0m\n", media);
            } else {
                System.out.printf("\u001B[31mA média final do aluno é: %.2f\nInfelizmente você foi reprovado!\u001B[0m\n", media);
            }

            continuar = perguntarSeContinua(sc);
        }

        sc.close();
    }

    private static double lerNota(Scanner sc, String mensagem) {
        double valor;
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                return 0; // entrada vazia vira 0
            }

            try {
                valor = Double.parseDouble(entrada);
                if (valor < 0) {
                    System.out.println("Valor inválido! A nota não pode ser negativa.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
            }
        }
    }

    private static boolean perguntarSeContinua(Scanner sc) {
        while (true) {
            System.out.print("\nDeseja calcular outra média? (S/N): ");
            String resposta = sc.nextLine().trim();

            if (resposta.equalsIgnoreCase("S")) {
                return true; // continua
            } else if (resposta.equalsIgnoreCase("N")) {
                System.out.println("Encerrando a aplicação. Até logo!");
                return false; // encerra
            } else {
                System.out.println("Entrada inválida. Digite 'S' para sim ou 'N' para não.");
            }
        }
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
