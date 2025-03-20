public class Cadeira {
    private String material;
    private String cor;
    private double altura;

    public Cadeira(String material, String cor, double altura) {
        this.material = material;
        this.cor = cor;
        this.altura = altura;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public void ajustarAltura(double novaAltura) {
        this.altura = novaAltura;
        System.out.println("A altura da cadeira foi ajustada para " + novaAltura + "!");
    }

    public void pintar(String novaCor) {
        this.cor = novaCor;
        System.out.println("A cadeira agora é " + novaCor + "!");
    }

    public void quebrar() {
        System.out.println("A cadeira de " + material + " quebrou!");
    }
}