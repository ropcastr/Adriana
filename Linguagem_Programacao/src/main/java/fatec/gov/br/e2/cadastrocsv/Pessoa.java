package fatec.gov.br.e2.cadastrocsv;

/**
 * Entidade Pessoa persistida em banco SQLite.
 */
public class Pessoa {
    private Integer id; // autoincrement no banco
    private double altura;
    private double massa;
    private String nome;
    private String sexo;

    public Pessoa(Integer id, String nome, String sexo, double altura, double massa) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.altura = altura;
        this.massa = massa;
    }

    public Pessoa(String nome, String sexo, double altura, double massa) {
        this(null, nome, sexo, altura, massa);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public double getMassa() { return massa; }
    public void setMassa(double massa) { this.massa = massa; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String escreverAfazeres() { return "eu, " + nome + ", andei alguns passos"; }
    public boolean sorrir() { return true; }
    public String comer(String oque) { return nome + "  comeu  " + oque; }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", altura=" + altura +
                ", massa=" + massa +
                '}';
    }
}

