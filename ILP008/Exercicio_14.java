import java.util.Scanner;


public class Exercicio_14 {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 14 - Duração do jogo de Xadrez");
        System.out.print("Informe a hora de início: ");
        int horaInicio = scanner.nextInt();
        System.out.print("Informe a hora de fim: ");
        int horaFim = scanner.nextInt();
        int duracao = (horaFim < horaInicio) ? (24 - horaInicio + horaFim) : (horaFim - horaInicio);
        System.out.println("Duração da partida: " + duracao + " horas\n");
	}
}