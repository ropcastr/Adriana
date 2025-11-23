package fatec.gov.br.e2.imagens.doodle;

public class RobotDb {
    private Integer id;
    private int nivel;
    private int vida;
    private int pontos;

    public RobotDb(Integer id,int nivel,int vida,int pontos){ this.id=id; this.nivel=nivel; this.vida=vida; this.pontos=pontos; }
    public RobotDb(int nivel,int vida,int pontos){ this(null,nivel,vida,pontos); }
    public Integer getId(){ return id; } public void setId(Integer id){ this.id=id; }
    public int getNivel(){ return nivel; } public void setNivel(int nivel){ this.nivel=nivel; }
    public int getVida(){ return vida; } public void setVida(int vida){ this.vida=vida; }
    public int getPontos(){ return pontos; } public void setPontos(int pontos){ this.pontos=pontos; }
    @Override public String toString(){ return "RobotDb{"+"id="+id+", nivel="+nivel+", vida="+vida+", pontos="+pontos+'}'; }
}

