public class Flor {
    private String cor;
    private double altura;
    private String tipo;

    public Flor(String cor, double altura, String tipo) {
        this.cor = cor;
        this.altura = altura;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    // Métodos
    public void crescer() {
        System.out.println("A flor " + tipo + " está crescendo!");
        altura += 0.5;
    }

    public void florescer() {
        System.out.println("A flor " + tipo + " está florescendo com a cor " + cor + "!");
    }

    public void mudarCor(String novaCor) {
        this.cor = novaCor;
        System.out.println("A cor da flor " + tipo + " foi alterada para " + novaCor + "!");
    }
}