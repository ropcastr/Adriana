package fatec.gov.br.e1.musica;

public class Violao {
    private int cordas;
    private String madeira;
    private String timbre;

    // Construtor
    public Violao(int cordas, String madeira, String timbre) {
        this.cordas = cordas;
        this.madeira = madeira;
        this.timbre = timbre;
    }

    // Getters e Setters
    public int getCordas() {
        return cordas;
    }

    public void setCordas(int cordas) {
        this.cordas = cordas;
    }

    public String getMadeira() {
        return madeira;
    }

    public void setMadeira(String madeira) {
        this.madeira = madeira;
    }

    public String getTimbre() {
        return timbre;
    }

    public void setTimbre(String timbre) {
        this.timbre = timbre;
    }

    // Método da classe
    public void produzirSomParaConquistar() {
        System.out.println("O violão de" + madeira + ", " + cordas + " cordas e timbre " + timbre + ", está produzindo som para conquistar meninas.");
    }
}
