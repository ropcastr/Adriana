package fatec.gov.br.e1.imagens.vikmuniz;

public class Camera {
    private String modelo;
    private String lente;
    private int resolucao;

    public Camera(String modelo, String lente, int resolucao) {
        this.modelo = modelo;
        this.lente = lente;
        this.resolucao = resolucao;

    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLente() {
        return lente;
    }

    public void setLente(String lente) {
        this.lente = lente;
    }

    public int getResolucao() {
        return resolucao;
    }

    public void setResolucao(int resolucao) {
        this.resolucao = resolucao;
    }

    // Método da classe
    public String capturarImagem() {
        return "A câmera " + modelo + " está capturando uma imagem exclusiva com resolução de " + resolucao + " MP.";
    }
}