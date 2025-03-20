package fatec.gov.br.e1.video;

import java.util.ArrayList;
import java.util.List;

public class Ingrediente {
    private String nome;
    private double quantidade;
    private String unidade;
    private List<Utensilio> utensilios = new ArrayList<>();

    public Ingrediente(String nome, double quantidade, String unidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public List<Utensilio> getUtensilios() {
        return utensilios;
    }

    public void setUtensilios(List<Utensilio> utensilios) {
        this.utensilios = utensilios;
    }

    public void limpar() {
        String quantidadeStr = (quantidade == (int) quantidade) ? String.valueOf((int) quantidade) : String.valueOf(quantidade);
        System.out.println(nome + " (" + quantidadeStr + " " + unidade + ") foi lavado após o molho.");
    }

    public void cortar() {
        String quantidadeStr = (quantidade == (int) quantidade) ? String.valueOf((int) quantidade) : String.valueOf(quantidade);
        System.out.println(nome + " (" + quantidadeStr + " " + unidade + ") foi cortado em tiras.");
    }

    public void temperar() {
        System.out.println(nome + " foi temperada com bicarbonato de sódio e água para velveting.");
    }

    public void adicionarUtensilio(Utensilio utensilio) {
        if (!utensilios.contains(utensilio)) {
            utensilios.add(utensilio);
            utensilio.adicionarIngrediente(this);
        }
    }
}