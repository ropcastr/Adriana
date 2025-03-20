public class Fracao {
    private int numerador;
    private int denominador;

    // Construtor
    public Fracao(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("Denominador não pode ser zero!");
        }
        this.numerador = numerador;
        this.denominador = denominador;
        simplificar(); // Simplifica a fração ao criá-la
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
        // Garante que o denominador seja positivo
        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }
    }

    // Operação de soma
    public Fracao somar(Fracao outra) {
        int novoNumerador = this.numerador * outra.denominador + outra.numerador * this.denominador;
        int novoDenominador = this.denominador * outra.denominador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    // Operação de subtração
    public Fracao subtrair(Fracao outra) {
        int novoNumerador = this.numerador * outra.denominador - outra.numerador * this.denominador;
        int novoDenominador = this.denominador * outra.denominador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    // Operação de multiplicação
    public Fracao multiplicar(Fracao outra) {
        int novoNumerador = this.numerador * outra.numerador;
        int novoDenominador = this.denominador * outra.denominador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    // Operação de divisão
    public Fracao dividir(Fracao outra) {
        if (outra.numerador == 0) {
            throw new IllegalArgumentException("Divisão por zero!");
        }
        int novoNumerador = this.numerador * outra.denominador;
        int novoDenominador = this.denominador * outra.numerador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    // Método toString para exibir a fração no formato simplificado
    @Override
    public String toString() {
        return numerador + "/" + denominador;
    }
}