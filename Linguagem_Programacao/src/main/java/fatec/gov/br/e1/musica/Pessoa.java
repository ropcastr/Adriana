package fatec.gov.br.e1.musica;

public class Pessoa {
    private String nome;
    private int idade;
    private String reputacao;

    // Construtor
    public Pessoa(String nome, int idade, String reputacao) {
        this.nome = nome;
        this.idade = idade;
        this.reputacao = reputacao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getReputacao() {
        return reputacao;
    }

    public void setReputacao(String reputacao) {
        this.reputacao = reputacao;
    }

    // Métodos da classe
    public void dirigirCarro() {
        System.out.println(nome + " de " + idade + "anos, está dirigindo o carro em um pega.");
    }

    public void tocar() {

        System.out.println("Quando o " + reputacao + " tocava o violão, conquistava as meninas!");
    }

    public String PessoaInfo() {
        return nome + " tinha " + idade + " anos, e era conhecido como: " + reputacao;
    }
}
