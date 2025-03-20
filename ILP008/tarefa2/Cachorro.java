public class Cachorro {
    private String raca;
    private int idade;
    private String cor;

    public Cachorro(String raca, int idade, String cor) {
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
    }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public void latir() {
        System.out.println("O cachorro " + raca + " está latindo!");
    }

    public void correr() {
        System.out.println("O cachorro " + raca + " está correndo!");
    }

    public void mudarCor(String novaCor) {
        this.cor = novaCor;
        System.out.println("A cor do cachorro " + raca + " foi alterada para " + novaCor + "!");
    }
}