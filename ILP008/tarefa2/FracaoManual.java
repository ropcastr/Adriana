import java.util.Scanner;

public class FracaoManual {
    private int numerador;
    private int denominador;

    // Construtor
    public FracaoManual(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("Denominador não pode ser zero!");
        }
        this.numerador = numerador;
        this.denominador = denominador;
        simplificar();
    }

    // Getters
    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    // Método para calcular o MDC (Máximo Divisor Comum)
    private int mdc(int a, int b) {
        if (b == 0) return Math.abs(a);
        return mdc(b, a % b);
    }

    // Método para simplificar a fração
    private void simplificar() {
        int mdc = mdc(numerador, denominador);
        numerador /= mdc;
        denominador /= mdc;
        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }
    }

    // Operação de soma
    public FracaoManual somar(FracaoManual outra) {
        int novoNumerador = this.numerador * outra.denominador + outra.numerador * this.denominador;
        int novoDenominador = this.denominador * outra.denominador;
        return new FracaoManual(novoNumerador, novoDenominador);
    }

    // Operação de subtração
    public FracaoManual subtrair(FracaoManual outra) {
        int novoNumerador = this.numerador * outra.denominador - outra.numerador * this.denominador;
        int novoDenominador = this.denominador * outra.denominador;
        return new FracaoManual(novoNumerador, novoDenominador);
    }

    // Operação de multiplicação
    public FracaoManual multiplicar(FracaoManual outra) {
        int novoNumerador = this.numerador * outra.numerador;
        int novoDenominador = this.denominador * outra.denominador;
        return new FracaoManual(novoNumerador, novoDenominador);
    }

    // Operação de divisão
    public FracaoManual dividir(FracaoManual outra) {
        if (outra.numerador == 0) {
            throw new IllegalArgumentException("Divisão por zero!");
        }
        int novoNumerador = this.numerador * outra.denominador;
        int novoDenominador = this.denominador * outra.numerador;
        return new FracaoManual(novoNumerador, novoDenominador);
    }

    // Método toString para exibir a fração
    @Override
    public String toString() {
        return numerador + "/" + denominador;
    }

    // Método main com menu interativo
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Calculadora de Frações ===");
            System.out.println("1. Somar");
            System.out.println("2. Subtrair");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("0. Sair");
            System.out.print("Escolha uma operação: ");
            opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.println("Saindo...");
                break;
            }

            if (opcao < 1 || opcao > 4) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }

            // Entrada da primeira fração
            System.out.println("\nDigite a primeira fração:");
            System.out.print("Numerador: ");
            int num1 = scanner.nextInt();
            System.out.print("Denominador: ");
            int den1 = scanner.nextInt();

            // Validação do denominador
            if (den1 == 0) {
                System.out.println("Erro: Denominador não pode ser zero!");
                continue;
            }

            FracaoManual f1 = new FracaoManual(num1, den1);

            // Entrada da segunda fração
            System.out.println("\nDigite a segunda fração:");
            System.out.print("Numerador: ");
            int num2 = scanner.nextInt();
            System.out.print("Denominador: ");
            int den2 = scanner.nextInt();

            // Validação do denominador
            if (den2 == 0) {
                System.out.println("Erro: Denominador não pode ser zero!");
                continue;
            }

            FracaoManual f2 = new FracaoManual(num2, den2);
            FracaoManual resultado = null;

            // Executa a operação escolhida
            try {
                switch (opcao) {
                    case 1: // Soma
                        resultado = f1.somar(f2);
                        System.out.println(f1 + " + " + f2 + " = " + resultado);
                        break;
                    case 2: // Subtração
                        resultado = f1.subtrair(f2);
                        System.out.println(f1 + " - " + f2 + " = " + resultado);
                        break;
                    case 3: // Multiplicação
                        resultado = f1.multiplicar(f2);
                        System.out.println(f1 + " * " + f2 + " = " + resultado);
                        break;
                    case 4: // Divisão
                        resultado = f1.dividir(f2);
                        System.out.println(f1 + " ÷ " + f2 + " = " + resultado);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (true);

        scanner.close();
    }
}