package fatec.gov.br.e1.musica;

public class CurvaDoDiabo {
    private String localizacao;
    private String perigosidade;
    private String nome;

    // Construtor
    public CurvaDoDiabo(String localizacao, String perigosidade, String nome) {
        this.localizacao = localizacao;
        this.perigosidade = perigosidade;
        this.nome = nome;
    }

    // Getters e Setters
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getPerigosidade() {
        return perigosidade;
    }

    public void setPerigosidade(String perigosidade) {
        this.perigosidade = perigosidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método da classe
    public void provocarAcidente() {
        System.out.println("A " + nome + " localizada em " + localizacao + " que é " + perigosidade + ", provoca um acidente de carro com explosão.");
    }

    public String acidentePega() {
        return "E os motores saíram Ligados a mil, para a " + nome + " localizada em " + localizacao + " que é " + perigosidade + ",\nno maior pega que se viu.";
    }
}
