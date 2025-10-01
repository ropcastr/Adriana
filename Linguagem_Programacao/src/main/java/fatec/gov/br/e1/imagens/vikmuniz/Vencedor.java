package fatec.gov.br.e1.imagens.vikmuniz;

public class Vencedor {
    private String nome;
    private int pontuacao;
    private String tempoVitoria;

    public Vencedor(String nome, int pontuacao, String tempoVitoria) {
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.tempoVitoria = tempoVitoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getTempoVitoria() {
        return tempoVitoria;
    }

    public void setTempoVitoria(String tempoVitoria) {
        this.tempoVitoria = tempoVitoria;
    }

    public String declararVitoria() {
        return nome + " venceu em " + tempoVitoria + " seg, com pontuação " + pontuacao;
    }

}
