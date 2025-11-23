package fatec.gov.br.e1.calculomedia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultadoMedia {
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

    public ResultadoMedia(double e1, double e2, double p1, double x, double api, double sub, double base, double mediaFinal, String status) {
        this.e1 = e1; this.e2 = e2; this.p1 = p1; this.x = x; this.api = api; this.sub = sub; this.base = base; this.mediaFinal = mediaFinal; this.status = status; this.timestamp = LocalDateTime.now();
    }
    public double getE1() { return e1; }
    public double getE2() { return e2; }
    public double getP1() { return p1; }
    public double getX() { return x; }
    public double getApi() { return api; }
    public double getSub() { return sub; }
    public double getBase() { return base; }
    public double getMediaFinal() { return mediaFinal; }
    public String getStatus() { return status; }
    public String getTimestamp() { return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }

    public String toCsvLine() {
        return String.join(";",
                String.valueOf(e1), String.valueOf(e2), String.valueOf(p1), String.valueOf(x),
                String.valueOf(api), String.valueOf(sub), String.valueOf(base), String.valueOf(mediaFinal), status, getTimestamp());
    }

    public static String csvHeader() {
        return "E1;E2;P1;X;API;SUB;BASE;MEDIA_FINAL;STATUS;TIMESTAMP";
    }
}

