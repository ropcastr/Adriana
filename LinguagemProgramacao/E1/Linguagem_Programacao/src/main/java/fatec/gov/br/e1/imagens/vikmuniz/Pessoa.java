package fatec.gov.br.e1.imagens.vikmuniz;

public class Pessoa {
    private String nome;
    private int forca;
    private String posicao;

    public Pessoa(String nome, int forca, String posicao) {
        this.nome = nome;
        this.forca = forca;
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void atacar() {
        System.out.println(nome + " está atacando com força " + forca);
    }

}

