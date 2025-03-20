import java.util.Scanner;

public class TestaClasse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        // Criando um objeto de cada classe
        Flor flor = new Flor("Vermelha", 0.3, "Rosa");
        Pessoa pessoa = new Pessoa("João", 25, "Engenheiro");
        Carro carro = new Carro("Toyota", "Corolla", 2020, "Azul");
        Livro livro = new Livro("Dom Quixote", "Cervantes", 500);
        Arvore arvore = new Arvore("Pinheiro", 5.0, 10);
        Cadeira cadeira = new Cadeira("Madeira", "Marrom", 0.8);
        Computador computador = new Computador("Dell", 16, "i7");
        Cachorro cachorro = new Cachorro("Labrador", 3, "Amarelo");
        Predio predio = new Predio(50.0, 10, "Cinza");
        Caneta caneta = new Caneta("Azul", "Esferográfica", 100);

        do {
            System.out.println("\n=== Menu de Testes ===");
            System.out.println("1. Testar Flor");
            System.out.println("2. Testar Pessoa");
            System.out.println("3. Testar Carro");
            System.out.println("4. Testar Livro");
            System.out.println("5. Testar Árvore");
            System.out.println("6. Testar Cadeira");
            System.out.println("7. Testar Computador");
            System.out.println("8. Testar Cachorro");
            System.out.println("9. Testar Prédio");
            System.out.println("10. Testar Caneta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    flor.crescer();
                    flor.florescer();
                    break;
                case 2:
                    pessoa.falar();
                    pessoa.andar();
                    break;
                case 3:
                    carro.acelerar();
                    carro.frear();
                    break;
                case 4:
                    livro.abrir();
                    livro.fechar();
                    break;
                case 5:
                    arvore.crescer();
                    arvore.darFruto();
                    break;
                case 6:
                    cadeira.ajustarAltura(0.9);
                    cadeira.pintar("Branca");
                    break;
                case 7:
                    computador.ligar();
                    computador.desligar();
                    break;
                case 8:
                    cachorro.latir();
                    cachorro.correr();
                    break;
                case 9:
                    predio.construirAndar();
                    predio.pintar("Azul");
                    break;
                case 10:
                    caneta.escrever();
                    caneta.recarregar();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}