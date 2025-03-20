package fatec.gov.br.e1.imagens.vikmuniz;

public class Fotografo {
    private String nome;
    private String tipoCamera;
    private String anosExperiencia;

    // Construtor
    public Fotografo(String nome, String tipoCamera, String anosExperiencia) {
        this.nome = nome;
        this.tipoCamera = tipoCamera;
        this.anosExperiencia = anosExperiencia;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoCamera() {
        return tipoCamera;
    }

    public void setTipoCamera(String tipoCamera) {
        this.tipoCamera = tipoCamera;
    }

    public String getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(String anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    // Método da classe
    public void tirarFoto() {
        System.out.println(nome + " está tirando uma foto exclusiva com sua " + tipoCamera + ".");
    }
}