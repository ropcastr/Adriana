public class Caneta {
    private String cor;
    private String tipo;
    private int carga;

    public Caneta(String cor, String tipo, int carga) {
        this.cor = cor;
        this.tipo = tipo;
        this.carga = carga;
    }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getCarga() { return carga; }
    public void setCarga(int carga) { this.carga = carga; }

    public void escrever() {
        if (carga > 0) {
            System.out.println("A caneta " + tipo + " está escrevendo!");
            carga -= 10;
        } else {
            System.out.println("A caneta " + tipo + " está sem carga!");
        }
    }

    public void recarregar() {
        this.carga = 100;
        System.out.println("A caneta " + tipo + " foi recarregada!");
    }

    public void mudarCor(String novaCor) {
        this.cor = novaCor;
        System.out.println("A cor da caneta " + tipo + " foi alterada para " + novaCor + "!");
    }
}