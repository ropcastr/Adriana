package fatec.gov.br.e1.imagens.vikmuniz;

public class Trilhos {
    private float comprimento;
    private String material;
    private float bitola;

    public Trilhos(float comprimento, String material, float bitola) {
        this.comprimento = comprimento;
        this.material = material;
        this.bitola = bitola;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getBitola() {
        return bitola;
    }

    public void setBitola(float bitola) {
        this.bitola = bitola;
    }

    public String suportarTrem() {
        return "Trilhos de " + material + ", " + bitola + "mm de bitola e extensão de " + comprimento + "km estão suportando trem a caminho";
    }

}
