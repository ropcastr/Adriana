package fatec.gov.br.e1.video;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private String nome;
    private int experiencia;
    private String especialidade;
    private List<Utensilio> utensilios = new ArrayList<>();
    private List<Ingrediente> ingredientes = new ArrayList<>();
    private java.time.LocalDateTime criadoEm = java.time.LocalDateTime.now();

    public Chef(String nome, int experiencia, String especialidade) {
        this.nome = nome;
        this.experiencia = experiencia;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<Utensilio> getUtensilios() {
        return utensilios;
    }

    public void setUtensilios(List<Utensilio> utensilios) {
        this.utensilios = utensilios;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void prepararIngrediente() {
        if (ingredientes.isEmpty()) {
            System.out.println("Chef " + nome + " não tem ingredientes para preparar.");
            return;
        }
        for (Ingrediente ingrediente : ingredientes) {
            ingrediente.cortar();
            ingrediente.temperar();
            System.out.println("Deixando o " + ingrediente.getNome() + " de molho por 10 minutos.");
            ingrediente.limpar();
            System.out.println("Chef " + nome + " preparou o ingrediente " + ingrediente.getNome() + " para velveting.");
        }
    }

    public void cozinhar() {
        if (utensilios.isEmpty() || ingredientes.isEmpty()) {
            System.out.println("Chef " + nome + " não tem utensílios ou ingredientes para cozinhar.");
            return;
        }
        for (Utensilio utensilio : utensilios) {
            utensilio.preparar();
            utensilio.aquecer();
            for (Ingrediente ingrediente : utensilio.getIngredientes()) {
                System.out.println("Adicionando " + ingrediente.getNome() + " à frigideira.");
            }
            utensilio.mexer();
            System.out.println("Chef " + nome + " está cozinhando na " + utensilio.getTipo());
        }
    }

    public void provarComida() {
        System.out.println("Chef " + nome + " está provando a comida: tenderiza e retém a umidade, delicioso!");
    }

    public void adicionarUtensilio(Utensilio utensilio) {
        if (!utensilios.contains(utensilio)) {
            utensilios.add(utensilio);
        }
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        if (!ingredientes.contains(ingrediente)) {
            ingredientes.add(ingrediente);
        }
    }

    public String getCriadoEm() {
        return criadoEm.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}