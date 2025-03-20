public class TestaFracao {
    public static void main(String[] args) {
        Fracao f1 = new Fracao(1, 2); // 1/2
        Fracao f2 = new Fracao(1, 3); // 1/3
        Fracao f3 = new Fracao(3, 4); // 3/4
        Fracao f4 = new Fracao(2, 3); // 2/3
        Fracao f5 = new Fracao(1, 4); // 1/4
        Fracao f6 = new Fracao(3, 5); // 3/5

        System.out.println("Soma: " + f1 + " + " + f2 + " = " + f1.somar(f2)); // 1/2 + 1/3 = 5/6
        System.out.println("Subtração: " + f3 + " - " + f1 + " = " + f3.subtrair(f1)); // 3/4 - 1/2 = 1/4
        System.out.println("Multiplicação: " + f4 + " * " + f5 + " = " + f4.multiplicar(f5)); // 2/3 * 1/4 = 1/6
        System.out.println("Divisão: " + f6 + " ÷ " + f4 + " = " + f6.dividir(f4)); // 3/5 ÷ 2/3 = 9/10
    }
}