import java.util.Scanner;

public class Exercicio_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 2 - Percentual de votos");
        System.out.print("Insira o total de eleitores: ");
        int totalEleitores = scanner.nextInt();
        System.out.print("Insira o total de votos brancos: ");
        int brancos = scanner.nextInt();
        System.out.print("Insira o total de votos nulos: ");
        int nulos = scanner.nextInt();
        int validos = totalEleitores - brancos - nulos;
        float percBrancos = (brancos * 100.0f) / totalEleitores;
        float percNulos = (nulos * 100.0f) / totalEleitores;
        float percValidos = (validos * 100.0f) / totalEleitores;
        System.out.printf("Brancos: %.2f%%\nNulos: %.2f%%\nVálidos: %.2f%%\n\n",
            percBrancos, percNulos, percValidos);
    }
}
