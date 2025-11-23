package fatec.gov.br.e2.calculomedia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultadoMedia {
    private Integer id; // PK no banco
    private final double e1;
    private final double e2;
    private final double p1;
    private final double x;
    private final double api;
    private final double sub;
    private final double base;
    private final double mediaFinal;
    private final String status;
    private final LocalDateTime timestamp;

    public ResultadoMedia(Integer id, double e1, double e2, double p1, double x, double api, double sub, double base, double mediaFinal, String status, LocalDateTime timestamp) {
        this.id = id; this.e1 = e1; this.e2 = e2; this.p1 = p1; this.x = x; this.api = api; this.sub = sub; this.base = base; this.mediaFinal = mediaFinal; this.status = status; this.timestamp = timestamp == null ? LocalDateTime.now() : timestamp;
    }
    public ResultadoMedia(double e1,double e2,double p1,double x,double api,double sub,double base,double mediaFinal,String status){
        this(null,e1,e2,p1,x,api,sub,base,mediaFinal,status,null);
    }
    public Integer getId(){return id;} public void setId(Integer id){this.id=id;}
    public double getE1(){return e1;} public double getE2(){return e2;} public double getP1(){return p1;} public double getX(){return x;} public double getApi(){return api;} public double getSub(){return sub;} public double getBase(){return base;} public double getMediaFinal(){return mediaFinal;} public String getStatus(){return status;}
    public String getTimestamp(){return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));}
}

