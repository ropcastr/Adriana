package fatec.gov.br.e1.imagens.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fatec.gov.br.e1.imagens.vikmuniz.Camera;
import fatec.gov.br.e1.imagens.vikmuniz.Fotografo;
import fatec.gov.br.e1.imagens.vikmuniz.Microfone;
import fatec.gov.br.e1.imagens.vikmuniz.Pessoa;
import fatec.gov.br.e1.imagens.vikmuniz.Trilhos;
import fatec.gov.br.e1.imagens.vikmuniz.Vencedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class VikMunizController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnExibirPapparazzi;

    @FXML
    private Button btnExibirTrackBrawl;

    @FXML
    private Button btnGravarPapparazzi;

    @FXML
    private Button btnGravarTrackBrawl;

    @FXML
    private TextField txtBrawlForca;

    @FXML
    private TextField txtBrawlPosicao;

    @FXML
    private TextField txtBrwalNome;

    @FXML
    private TextField txtCameraModelo;

    @FXML
    private TextField txtExperiencia;

    @FXML
    private TextField txtFotografoNome;

    @FXML
    private TextField txtMicrofoneCompCabo;

    @FXML
    private TextField txtMicrofoneMarca;

    @FXML
    private TextField txtMicrofoneSensibilidade;

    @FXML
    private TextField txtResolucao;

    @FXML
    private TextField txtTipoCamera;

    @FXML
    private TextField txtTipoLente;

    @FXML
    private TextField txtTrilhoBitola;

    @FXML
    private TextField txtTrilhoComprimento;

    @FXML
    private TextField txtTrilhoMaterial;

    @FXML
    private TextField txtVencedorNome;

    @FXML
    private TextField txtVencedorPontuacao;

    @FXML
    private TextField txtVencedorTempoVitoria;


    private static final String DATA_DIR = "src/main/java/fatec/gov/br/e1/imagens/vikmuniz";
    private static final String PAPPARAZZI_CSV = DATA_DIR + "/papparazzi.csv";
    private static final String TRACKBRAWL_CSV = DATA_DIR + "/trackbrawl.csv";


    @FXML
    void exibirAtributosPapparazzi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ei/ui/imagens/PopUpPapparazzi.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("PopUp Paparazzi");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exibirAtributosTrackBrawl(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ei/ui/imagens/PopUpTrackBrawl.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("PopUp TrackBrawl");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gravarAtributosPapparazzi(ActionEvent event) {
        //Validação dos campos na ‘interface’
        List<String> campos = Arrays.asList(
            txtCameraModelo.getText(),
            txtTipoCamera.getText(),
            txtTipoLente.getText(),
            txtResolucao.getText(),
            txtMicrofoneMarca.getText(),
            txtMicrofoneCompCabo.getText(),
            txtMicrofoneSensibilidade.getText(),
            txtFotografoNome.getText(),
            txtExperiencia.getText()
        );
        if (campos.stream().anyMatch(s -> s == null || s.trim().isEmpty())) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
        //Instancia os objetos e captura as saídas dos métodos
        String modelo = txtCameraModelo.getText();
        String lente = txtTipoLente.getText();
        int resolucao = parseOrDefault(txtResolucao.getText(), 0);
        Camera camera = new Camera(modelo, lente, resolucao);
        String cameraMsg = camera.capturarImagem();

        String nome = txtFotografoNome.getText();
        String tipoCamera = txtTipoCamera.getText();
        String anosExperiencia = txtExperiencia.getText();
        Fotografo fotografo = new Fotografo(nome, tipoCamera, anosExperiencia);
        String fotografoMsg = fotografo.tirarFoto();

        String marca = txtMicrofoneMarca.getText();
        float comprimentoCabo = parseOrDefault(txtMicrofoneCompCabo.getText(), 0);
        int sensibilidade = parseOrDefault(txtMicrofoneSensibilidade.getText(), 0);
        Microfone microfone = new Microfone(marca, comprimentoCabo, sensibilidade);
        String microfoneMsg = microfone.capturarAudio();

        //Grava atributos e as saídas dos métodos no CSV
        List<String> registro = Arrays.asList(
            modelo, tipoCamera, lente, String.valueOf(resolucao),
            marca, String.valueOf(comprimentoCabo), String.valueOf(sensibilidade),
            nome, anosExperiencia,
            cameraMsg, fotografoMsg, microfoneMsg
        );
        try (FileWriter writer = new FileWriter(PAPPARAZZI_CSV, true)) {
            writer.append(String.join(",", registro)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gravarAtributosTrackBrawl(ActionEvent event) {
        //Validação dos campos na ‘interface’
        List<String> campos = Arrays.asList(
            txtBrwalNome.getText(),
            txtBrawlForca.getText(),
            txtBrawlPosicao.getText(),
            txtTrilhoBitola.getText(),
            txtTrilhoComprimento.getText(),
            txtTrilhoMaterial.getText(),
            txtVencedorNome.getText(),
            txtVencedorPontuacao.getText(),
            txtVencedorTempoVitoria.getText()
        );
        if (campos.stream().anyMatch(s -> s == null || s.trim().isEmpty())) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
        //Instancia os objetos e captura as saídas dos métodos
        String nome = txtBrwalNome.getText();
        int forca = parseOrDefault(txtBrawlForca.getText(), 0);
        String posicao = txtBrawlPosicao.getText();
        Pessoa pessoa = new Pessoa(nome, forca, posicao);
        String pessoaMsg = pessoa.atacar();

        float comprimento = parseOrDefault(txtTrilhoComprimento.getText(), 0);
        String material = txtTrilhoMaterial.getText();
        float bitola = parseOrDefault(txtTrilhoBitola.getText(), 0);
        Trilhos trilhos = new Trilhos(comprimento, material, bitola);
        String trilhosMsg = trilhos.suportarTrem();

        String vencedorNome = txtVencedorNome.getText();
        int pontuacao = parseOrDefault(txtVencedorPontuacao.getText(), 0);
        String tempoVitoria = txtVencedorTempoVitoria.getText();
        Vencedor vencedor = new Vencedor(vencedorNome, pontuacao, tempoVitoria);
        String vencedorMsg = vencedor.declararVitoria();

        //Grava atributos e as saídas dos métodos no CSV
        List<String> registro = Arrays.asList(
            nome, String.valueOf(forca), posicao,
            String.valueOf(bitola), String.valueOf(comprimento), material,
            vencedorNome, String.valueOf(pontuacao), tempoVitoria,
            pessoaMsg, trilhosMsg, vencedorMsg
        );
        try (FileWriter writer = new FileWriter(TRACKBRAWL_CSV, true)) {
            writer.append(String.join(",", registro)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnExibirPapparazzi != null : "fx:id=\"btnExibirPapparazzi\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert btnExibirTrackBrawl != null : "fx:id=\"btnExibirTrackBrawl\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert btnGravarPapparazzi != null : "fx:id=\"btnGravarPapparazzi\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert btnGravarTrackBrawl != null : "fx:id=\"btnGravarTrackBrawl\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtBrawlForca != null : "fx:id=\"txtBrawlForca\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtBrawlPosicao != null : "fx:id=\"txtBrawlPosicao\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtBrwalNome != null : "fx:id=\"txtBrwalNome\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtCameraModelo != null : "fx:id=\"txtCameraModelo\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtExperiencia != null : "fx:id=\"txtExperiencia\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtFotografoNome != null : "fx:id=\"txtFotografoNome\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtMicrofoneCompCabo != null : "fx:id=\"txtMicrofoneCompCabo\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtMicrofoneMarca != null : "fx:id=\"txtMicrofoneMarca\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtMicrofoneSensibilidade != null : "fx:id=\"txtMicrofoneSensibilidade\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtResolucao != null : "fx:id=\"txtResolucao\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtTipoCamera != null : "fx:id=\"txtTipoCamera\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtTipoLente != null : "fx:id=\"txtTipoLente\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtTrilhoBitola != null : "fx:id=\"txtTrilhoBitola\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtTrilhoComprimento != null : "fx:id=\"txtTrilhoComprimento\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtTrilhoMaterial != null : "fx:id=\"txtTrilhoMaterial\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtVencedorNome != null : "fx:id=\"txtVencedorNome\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtVencedorPontuacao != null : "fx:id=\"txtVencedorPontuacao\" was not injected: check your FXML file 'VikMuniz.fxml'.";
        assert txtVencedorTempoVitoria != null : "fx:id=\"txtVencedorTempoVitoria\" was not injected: check your FXML file 'VikMuniz.fxml'.";

    }

    private int parseOrDefault(String value, int def) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return def;
        }
    }

}
