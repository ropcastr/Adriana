package fatec.gov.br.e1.imagens.vikmuniz;

public class Microfone {
    private String marca;
    private float comprimentoCabo;
    private int sensibilidade;

    public Microfone(String marca, float comprimentoCabo, int sensibilidade) {
        this.marca = marca;
        this.comprimentoCabo = comprimentoCabo;
        this.sensibilidade = sensibilidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getComprimentoCabo() {
        return comprimentoCabo;
    }

    public void setComprimentoCabo(float comprimentoCabo) {
        this.comprimentoCabo = comprimentoCabo;
    }

    public int getSensibilidade() {
        return sensibilidade;
    }

    public void setSensibilidade(int sensibilidade) {
        this.sensibilidade = sensibilidade;
    }

    public void capturarAudio() {
        System.out.println("O microfone " + marca + " está capturando o áudio da entrevista exclusiva com sensibilidade de " + sensibilidade + " MP.");
    }
}