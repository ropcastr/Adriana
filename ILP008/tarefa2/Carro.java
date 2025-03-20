public class Carro {
    private String marca;

    private String modelo;
    private int ano;
    private String cor;

    public Carro(String marca, String modelo, int ano, String cor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public void acelerar() {
        System.out.println("O " + marca + modelo + " está acelerando!");
    }

    public void frear() {
        System.out.println("O " + marca + modelo + " está freando!");
    }

    public void mudarCor(String novaCor) {
        this.cor = novaCor;
        System.out.println("O " + marca + modelo + " agora é " + novaCor + "!");
    }
}