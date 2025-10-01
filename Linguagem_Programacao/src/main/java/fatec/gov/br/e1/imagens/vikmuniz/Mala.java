package fatec.gov.br.e1.imagens.vikmuniz;

public class Mala {
    private String tamanho;
    private String material;
    private float peso;

    public Mala() {
        this.tamanho = "";
        this.material = "";
        this.peso = 0.0f;
    }

    public Mala(String tamanho, String material, float peso) {
        this.tamanho = tamanho;
        this.material = material;
        this.peso = peso;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void abrir(Vencedor vencedor) {
        System.out.println("A Mala de " + material + " está sendo aberta por " + vencedor.getNome());
    }
}
