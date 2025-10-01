package fatec.gov.br.e1.imagens.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import fatec.gov.br.e1.imagens.vikmuniz.Camera;
import fatec.gov.br.e1.imagens.vikmuniz.Fotografo;
import fatec.gov.br.e1.imagens.vikmuniz.Microfone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PopUpPapparazziController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Fotografo, String> TableFotografoColunaExperiencia;

    @FXML
    private AnchorPane paneTrackBrawl;

    @FXML
    private TableView<Microfone> tabeViewMicrofone;

    @FXML
    private TableColumn<Camera, String> tabelCameraColunaLente;

    @FXML
    private TableColumn<Camera, Integer> tableCameraColunaResolucao;

    @FXML
    private TableColumn<Camera, String> tableCameraColunamodelo;

    @FXML
    private TableColumn<Fotografo, String> tableFotografoColunaCamera;

    @FXML
    private TableColumn<Fotografo, String> tableFotografoColunaNome;

    @FXML
    private TableColumn<Microfone, Float> tableMicrofoneColunaComprimentoCabo;

    @FXML
    private TableColumn<Microfone, String> tableMicrofoneColunaMarca;

    @FXML
    private TableColumn<Microfone, Integer> tableMicrofoneColunaSensibilidade;

    @FXML
    private TableView<Camera> tableViewCamera;

    @FXML
    private TableView<Fotografo> tableViewFotografo;

    @FXML
    private TextArea txtAreaMetodosPapparazzi;

    private static final String PAPPARAZZI_CSV = "src/main/java/fatec/gov/br/e1/imagens/vikmuniz/papparazzi.csv";

    @FXML
    private void carregarDadosPapparazzi() {
        ObservableList<Camera> cameras = FXCollections.observableArrayList();
        ObservableList<Fotografo> fotografos = FXCollections.observableArrayList();
        ObservableList<Microfone> microfones = FXCollections.observableArrayList();
        StringBuilder metodos = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(PAPPARAZZI_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 12) continue;
                Camera camera = new Camera(dados[0], dados[2], Integer.parseInt(dados[3]));
                cameras.add(camera);
                Fotografo fotografo = new Fotografo(dados[7], dados[1], dados[8]);
                fotografos.add(fotografo);
                Microfone microfone = new Microfone(dados[4], Float.parseFloat(dados[5]), Integer.parseInt(dados[6]));
                microfones.add(microfone);
                metodos.append(dados[9]).append("\n").append(dados[10]).append("\n").append(dados[11]).append("\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableViewCamera.setItems(cameras);
        tableViewFotografo.setItems(fotografos);
        tabeViewMicrofone.setItems(microfones);
        txtAreaMetodosPapparazzi.setText(metodos.toString());
    }

    @FXML
    void initialize() {
        assert TableFotografoColunaExperiencia != null : "fx:id=\"TableFotografoColunaExperiencia\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert paneTrackBrawl != null : "fx:id=\"paneTrackBrawl\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tabeViewMicrofone != null : "fx:id=\"tabeViewMicrofone\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tabelCameraColunaLente != null : "fx:id=\"tabelCameraColunaLente\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableCameraColunaResolucao != null : "fx:id=\"tableCameraColunaResolucao\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableCameraColunamodelo != null : "fx:id=\"tableCameraColunamodelo\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableFotografoColunaCamera != null : "fx:id=\"tableFotografoColunaCamera\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableFotografoColunaNome != null : "fx:id=\"tableFotografoColunaNome\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableMicrofoneColunaComprimentoCabo != null : "fx:id=\"tableMicrofoneColunaComprimentoCabo\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableMicrofoneColunaMarca != null : "fx:id=\"tableMicrofoneColunaMarca\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableMicrofoneColunaSensibilidade != null : "fx:id=\"tableMicrofoneColunaSensibilidade\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableViewCamera != null : "fx:id=\"tableViewCamera\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert tableViewFotografo != null : "fx:id=\"tableViewFotografo\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";
        assert txtAreaMetodosPapparazzi != null : "fx:id=\"txtAreaMetodosPapparazzi\" was not injected: check your FXML file 'PopUpPapparazzi.fxml'.";

        tableCameraColunamodelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tabelCameraColunaLente.setCellValueFactory(new PropertyValueFactory<>("lente"));
        tableCameraColunaResolucao.setCellValueFactory(new PropertyValueFactory<>("resolucao"));
        tableFotografoColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableFotografoColunaCamera.setCellValueFactory(new PropertyValueFactory<>("tipoCamera"));
        TableFotografoColunaExperiencia.setCellValueFactory(new PropertyValueFactory<>("anosExperiencia"));
        tableMicrofoneColunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableMicrofoneColunaComprimentoCabo.setCellValueFactory(new PropertyValueFactory<>("comprimentoCabo"));
        tableMicrofoneColunaSensibilidade.setCellValueFactory(new PropertyValueFactory<>("sensibilidade"));
        carregarDadosPapparazzi();
    }

}
