package fatec.gov.br.e1.musica.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import fatec.gov.br.e1.musica.Carro;
import fatec.gov.br.e1.musica.CurvaDoDiabo;
import fatec.gov.br.e1.musica.Pessoa;
import fatec.gov.br.e1.musica.Violao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class DezesseisController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PaneDezesseis;

    @FXML
    private Button btnExibirCSV;

    @FXML
    private Button btnGravarAtributos;

    @FXML
    private TableColumn<?, ?> tableCarroColunaCor;

    @FXML
    private TableColumn<?, ?> tableCarroColunaModelo;

    @FXML
    private TableColumn<?, ?> tableCarroColunaMotor;

    @FXML
    private TableColumn<?, ?> tableCurvaDiaboColunaLocalizacao;

    @FXML
    private TableColumn<?, ?> tableCurvaDiaboColunaNome;

    @FXML
    private TableColumn<?, ?> tableCurvaDiaboColunaPerigosidade;

    @FXML
    private TableColumn<?, ?> tablePessoaColunaIdade;

    @FXML
    private TableColumn<?, ?> tablePessoaColunaNome;

    @FXML
    private TableColumn<?, ?> tablePessoaColunaReputacao;

    @FXML
    private TableView<Pessoa> tableViePessoa;

    @FXML
    private TableView<Carro> tableViewCarro;

    @FXML
    private TableView<CurvaDoDiabo> tableViewCurvaDiabo;

    @FXML
    private TableView<Violao> tableViewViolao;

    @FXML
    private TableColumn<?, ?> tableViolaoColunaCordas;

    @FXML
    private TableColumn<?, ?> tableViolaoColunaMadeira;

    @FXML
    private TableColumn<?, ?> tableViolaoColunaTimbre;

    @FXML
    private TextArea txtAreaMetodo;

    @FXML
    private TextField txtCarroCor;

    @FXML
    private TextField txtCarroModelo;

    @FXML
    private TextField txtCarroMotor;

    @FXML
    private TextField txtCurvaDiaboLocalizacao;

    @FXML
    private TextField txtCurvaDiaboNome;

    @FXML
    private TextField txtCurvaDiaboPerigosidade;

    @FXML
    private TextField txtPessoaIdade;

    @FXML
    private TextField txtPessoaNome;

    @FXML
    private TextField txtPessoaReputacao;

    @FXML
    private TextField txtViolaoCordas;

    @FXML
    private TextField txtViolaoMadeira;

    @FXML
    private TextField txtViolaoTimbre;

    private static final String DATA_DIR = "src/main/java/fatec/gov/br/e1/musica";
    private static final String DEZESSEIS_CSV = DATA_DIR + "/dezesseis.csv";

        @FXML
    void GravarAtributos(ActionEvent event) {
        List<String> atributos = Arrays.asList(
            txtPessoaNome.getText(),
            txtPessoaIdade.getText(),
            txtPessoaReputacao.getText(),
            txtCarroModelo.getText(),
            txtCarroCor.getText(),
            txtCarroMotor.getText(),
            txtViolaoCordas.getText(),
            txtViolaoMadeira.getText(),
            txtViolaoTimbre.getText(),
            txtCurvaDiaboNome.getText(),
            txtCurvaDiaboLocalizacao.getText(),
            txtCurvaDiaboPerigosidade.getText()
        );
        if (atributos.stream().anyMatch(s -> s == null || s.trim().isEmpty())) {
            txtAreaMetodo.setText("Por favor, preencha todos os campos antes de gravar.");
            return;
        }
        Pessoa pessoa = new Pessoa(txtPessoaNome.getText(), parseOrDefault(txtPessoaIdade.getText()), txtPessoaReputacao.getText());
        Carro carro = new Carro(txtCarroModelo.getText(), txtCarroCor.getText(), txtCarroMotor.getText());
        Violao violao = new Violao(parseOrDefault(txtViolaoCordas.getText()), txtViolaoMadeira.getText(), txtViolaoTimbre.getText());
        CurvaDoDiabo curva = new CurvaDoDiabo(txtCurvaDiaboLocalizacao.getText(), txtCurvaDiaboPerigosidade.getText(), txtCurvaDiaboNome.getText());
        String pessoaMensagem = pessoa.PessoaInfo();
        String carroMensagem = carro.pilotarCarro();
        String violaoMensagem = violao.pegarViolao();
        String curvaMensagem = curva.acidentePega();
        String mensagemCompleta = pessoaMensagem + "\n" + carroMensagem + "\n" + violaoMensagem + "\n" + curvaMensagem;
        // Corrigido: criar lista de Strings para o CSV
        List<String> linhaCSV = Arrays.asList(
            pessoa.getNome(), String.valueOf(pessoa.getIdade()), pessoa.getReputacao(),
            carro.getModelo(), carro.getCor(), carro.getMotor(),
            String.valueOf(violao.getCordas()), violao.getMadeira(), violao.getTimbre(),
            curva.getNome(), curva.getLocalizacao(), curva.getPerigosidade(),
            pessoaMensagem, carroMensagem, violaoMensagem, curvaMensagem
        );
        try (FileWriter writer = new FileWriter(DEZESSEIS_CSV, true)) {
            writer.append(String.join(",", linhaCSV)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtAreaMetodo.setText(mensagemCompleta);

    }

    private int parseOrDefault(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    @FXML
    void ExibirCSV(ActionEvent event) throws FileNotFoundException {
        ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
        ObservableList<Carro> carros = FXCollections.observableArrayList();
        ObservableList<Violao> violoes = FXCollections.observableArrayList();
        ObservableList<CurvaDoDiabo> curvas = FXCollections.observableArrayList();
        StringBuilder metodos = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(DEZESSEIS_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 12) continue;
                Pessoa pessoa = new Pessoa(dados[0], parseOrDefault(dados[1]), dados[2]);
                pessoas.add(pessoa);
                Carro carro = new Carro(dados[3], dados[4], dados[5]);
                carros.add(carro);
                Violao violao = new Violao(parseOrDefault(dados[6]), dados[7], dados[8]);
                violoes.add(violao);
                CurvaDoDiabo curva = new CurvaDoDiabo(dados[10], dados[11], dados[9]);
                curvas.add(curva);
                if (dados.length > 12) {
                    metodos.append(dados[12]).append("\n").append(dados[13]).append("\n").append(dados[14]).append("\n").append(dados[15]).append("\n\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableViePessoa.setItems(pessoas);
        tableViewCarro.setItems(carros);
        tableViewViolao.setItems(violoes);
        tableViewCurvaDiabo.setItems(curvas);
        txtAreaMetodo.setText(metodos.toString());
    }



    @FXML
    void initialize() {
        assert PaneDezesseis != null : "fx:id=\"PaneDezesseis\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert btnExibirCSV != null : "fx:id=\"btnExibirCSV\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert btnGravarAtributos != null : "fx:id=\"btnGravarAtributos\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableCarroColunaCor != null : "fx:id=\"tableCarroColunaCor\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableCarroColunaModelo != null : "fx:id=\"tableCarroColunaModelo\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableCarroColunaMotor != null : "fx:id=\"tableCarroColunaMotor\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableCurvaDiaboColunaLocalizacao != null : "fx:id=\"tableCurvaDiaboColunaLocalizacao\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableCurvaDiaboColunaNome != null : "fx:id=\"tableCurvaDiaboColunaNome\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableCurvaDiaboColunaPerigosidade != null : "fx:id=\"tableCurvaDiaboColunaPerigosidade\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tablePessoaColunaIdade != null : "fx:id=\"tablePessoaColunaIdade\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tablePessoaColunaNome != null : "fx:id=\"tablePessoaColunaNome\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tablePessoaColunaReputacao != null : "fx:id=\"tablePessoaColunaReputacao\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableViePessoa != null : "fx:id=\"tableViePessoa\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableViewCarro != null : "fx:id=\"tableViewCarro\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableViewCurvaDiabo != null : "fx:id=\"tableViewCurvaDiabo\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableViewViolao != null : "fx:id=\"tableViewViolao\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableViolaoColunaCordas != null : "fx:id=\"tableViolaoColunaCordas\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableViolaoColunaMadeira != null : "fx:id=\"tableViolaoColunaMadeira\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert tableViolaoColunaTimbre != null : "fx:id=\"tableViolaoColunaTimbre\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtAreaMetodo != null : "fx:id=\"txtAreaMetodo\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtCarroCor != null : "fx:id=\"txtCarroCor\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtCarroModelo != null : "fx:id=\"txtCarroModelo\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtCarroMotor != null : "fx:id=\"txtCarroMotor\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtCurvaDiaboLocalizacao != null : "fx:id=\"txtCurvaDiaboLocalizacao\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtCurvaDiaboNome != null : "fx:id=\"txtCurvaDiaboNome\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtCurvaDiaboPerigosidade != null : "fx:id=\"txtCurvaDiaboPerigosidade\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtPessoaIdade != null : "fx:id=\"txtPessoaIdade\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtPessoaNome != null : "fx:id=\"txtPessoaNome\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtPessoaReputacao != null : "fx:id=\"txtPessoaReputacao\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtViolaoCordas != null : "fx:id=\"txtViolaoCordas\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtViolaoMadeira != null : "fx:id=\"txtViolaoMadeira\" was not injected: check your FXML file 'Dezesseis.fxml'.";
        assert txtViolaoTimbre != null : "fx:id=\"txtViolaoTimbre\" was not injected: check your FXML file 'Dezesseis.fxml'.";

        tablePessoaColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablePessoaColunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        tablePessoaColunaReputacao.setCellValueFactory(new PropertyValueFactory<>("reputacao"));
        tableCarroColunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableCarroColunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        tableCarroColunaMotor.setCellValueFactory(new PropertyValueFactory<>("motor"));
        tableViolaoColunaCordas.setCellValueFactory(new PropertyValueFactory<>("cordas"));
        tableViolaoColunaMadeira.setCellValueFactory(new PropertyValueFactory<>("madeira"));
        tableViolaoColunaTimbre.setCellValueFactory(new PropertyValueFactory<>("timbre"));
        tableCurvaDiaboColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableCurvaDiaboColunaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        tableCurvaDiaboColunaPerigosidade.setCellValueFactory(new PropertyValueFactory<>("perigosidade"));
        try {
            ExibirCSV(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
