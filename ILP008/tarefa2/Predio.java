public class Predio {
    private double altura;
    private int numeroAndares;
    private String cor;

    public Predio(double altura, int numeroAndares, String cor) {
        this.altura = altura;
        this.numeroAndares = numeroAndares;
        this.cor = cor;
    }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public int getNumeroAndares() { return numeroAndares; }
    public void setNumeroAndares(int numeroAndares) { this.numeroAndares = numeroAndares; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public void construirAndar() {
        numeroAndares++;
        System.out.println("Um novo andar foi construído! Total de andares: " + numeroAndares);
    }

    public void pintar(String novaCor) {
        this.cor = novaCor;
        System.out.println("O prédio agora é " + novaCor + "!");
    }

    public void demolir() {
        System.out.println("O prédio foi demolido!");
    }
}