public class Computador {
    private String marca;
    private int memoria;
    private String processador;

    public Computador(String marca, int memoria, String processador) {
        this.marca = marca;
        this.memoria = memoria;
        this.processador = processador;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getMemoria() { return memoria; }
    public void setMemoria(int memoria) { this.memoria = memoria; }
    public String getProcessador() { return processador; }
    public void setProcessador(String processador) { this.processador = processador; }

    public void ligar() {
        System.out.println("O computador " + marca + " foi ligado!");
    }

    public void desligar() {
        System.out.println("O computador " + marca + " foi desligado!");
    }

    public void aumentarMemoria(int novaMemoria) {
        this.memoria = novaMemoria;
        System.out.println("A memória do computador " + marca + " foi aumentada para " + novaMemoria + " GB!");
    }
}