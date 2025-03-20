public class Arvore {
    private String especie;
    private double altura;
    private int idade;

    public Arvore(String especie, double altura, int idade) {
        this.especie = especie;
        this.altura = altura;
        this.idade = idade;
    }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public void crescer() {
        System.out.println("A árvore " + especie + " está crescendo!");
        altura += 1.0;
    }

    public void darFruto() {
        System.out.println("A árvore " + especie + " está dando frutos!");
    }

    public void mudarEspecie(String novaEspecie) {
        this.especie = novaEspecie;
        System.out.println("A espécie da árvore foi alterada para " + novaEspecie + "!");
    }
}