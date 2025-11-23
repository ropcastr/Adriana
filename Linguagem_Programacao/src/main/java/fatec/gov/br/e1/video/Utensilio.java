package fatec.gov.br.e1.video;

import java.util.ArrayList;
import java.util.List;

public class Utensilio {
    private String tipo;
    private String material;
    private double tamanho;
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public Utensilio(String tipo, String material, double tamanho) {
        this.tipo = tipo;
        this.material = material;
        this.tamanho = tamanho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void preparar() {
        System.out.println(tipo + " de " + material + " está sendo preparada com óleo e especiarias.");
    }

    public void aquecer() {
        if (material.equalsIgnoreCase("aço") || material.equalsIgnoreCase("ferro")) {
            System.out.println(tipo + " está aquecida a uma temperatura adequada.");
        } else {
            System.out.println(tipo + " não pode ser aquecida diretamente.");
        }
    }

    public void mexer() {
        if (!ingredientes.isEmpty()) {
            System.out.println(tipo + " está sendo usada para mexer " + ingredientes.get(0).getNome() + " regularmente.");
        } else {
            System.out.println(tipo + " está sendo usada para mexer.");
        }
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        if (!ingredientes.contains(ingrediente)) {
            ingredientes.add(ingrediente);
        }
    }
}