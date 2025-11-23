package fatec.gov.br.e2.imagens.doodle;

public class AlienDb {
    private Integer id;
    private String tipo;
    private int forca;
    private int vida;

    public AlienDb(Integer id, String tipo, int forca, int vida){ this.id=id; this.tipo=tipo; this.forca=forca; this.vida=vida; }
    public AlienDb(String tipo,int forca,int vida){ this(null,tipo,forca,vida); }
    public Integer getId(){ return id; } public void setId(Integer id){ this.id=id; }
    public String getTipo(){ return tipo; } public void setTipo(String tipo){ this.tipo=tipo; }
    public int getForca(){ return forca; } public void setForca(int forca){ this.forca=forca; }
    public int getVida(){ return vida; } public void setVida(int vida){ this.vida=vida; }
    @Override public String toString(){ return "AlienDb{"+"id="+id+", tipo='"+tipo+'\''+", forca="+forca+", vida="+vida+'}'; }
}

