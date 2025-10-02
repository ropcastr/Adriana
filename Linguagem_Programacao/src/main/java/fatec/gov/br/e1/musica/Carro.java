package fatec.gov.br.e1.musica;

public class Carro {
    private String modelo;
    private String cor;
    private String motor;

    // Construtor
    public Carro(String modelo, String cor, String motor) {
        this.modelo = modelo;
        this.cor = cor;
        this.motor = motor;
    }

    // Getters e Setters
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    // Método da classe
    public void roncarEmPega() {
        System.out.println("O " + modelo + " de cor " + cor + " e motor " + motor + ", está roncando em um pega na Asa Sul.");
    }

    public String pilotarCarro() {
        return "Ele tinha um " + modelo + " de cor " + cor + " e motor " + motor + ", era o rei dos pegas.";
    }


}
