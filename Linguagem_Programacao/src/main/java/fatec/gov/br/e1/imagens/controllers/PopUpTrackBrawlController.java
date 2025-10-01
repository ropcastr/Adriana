package fatec.gov.br.e1.imagens.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import fatec.gov.br.e1.imagens.vikmuniz.Pessoa;
import fatec.gov.br.e1.imagens.vikmuniz.Trilhos;
import fatec.gov.br.e1.imagens.vikmuniz.Vencedor;
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

public class PopUpTrackBrawlController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneTrackBrawl;

    @FXML
    private TableColumn<Pessoa, Integer> tablePessoaColunaForca;

    @FXML
    private TableColumn<Pessoa, String> tablePessoaColunaNome;

    @FXML
    private TableColumn<Pessoa, String> tablePessoaColunaPosicao;

    @FXML
    private TableColumn<Trilhos, Float> tableTrilhoColunaBitola;

    @FXML
    private TableColumn<Trilhos, Float> tableTrilhoColunaComprimento;

    @FXML
    private TableColumn<Trilhos, String> tableTrilhoColunaMaterial;

    @FXML
    private TableColumn<Vencedor, String> tableVencedorColunaNome;

    @FXML
    private TableColumn<Vencedor, Integer> tableVencedorColunaPontuacao;

    @FXML
    private TableColumn<Vencedor, String> tableVencedorColunaTempoVitoria;

    @FXML
    private TableView<Pessoa> tableViewPessoa;

    @FXML
    private TableView<Trilhos> tableViewTrilho;

    @FXML
    private TableView<Vencedor> tableViewVencedor;

    @FXML
    private TextArea txtAreaMetodosTrackBrawl;

    private static final String TRACKBRAWL_CSV = "src/main/java/fatec/gov/br/e1/imagens/vikmuniz/trackbrawl.csv";

    @FXML
    private void carregarDadosTrackBrawl() {
        ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
        ObservableList<Trilhos> trilhosList = FXCollections.observableArrayList();
        ObservableList<Vencedor> vencedores = FXCollections.observableArrayList();
        StringBuilder metodos = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(TRACKBRAWL_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 12) continue;
                Pessoa pessoa = new Pessoa(dados[0], Integer.parseInt(dados[1]), dados[2]);
                pessoas.add(pessoa);
                Trilhos trilhos = new Trilhos(Float.parseFloat(dados[4]), dados[5], Float.parseFloat(dados[3]));
                trilhosList.add(trilhos);
                Vencedor vencedor = new Vencedor(dados[6], Integer.parseInt(dados[7]), dados[8]);
                vencedores.add(vencedor);
                metodos.append(dados[9]).append("\n").append(dados[10]).append("\n").append(dados[11]).append("\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableViewPessoa.setItems(pessoas);
        tableViewTrilho.setItems(trilhosList);
        tableViewVencedor.setItems(vencedores);
        txtAreaMetodosTrackBrawl.setText(metodos.toString());
    }

    @FXML
    void initialize() {
        assert paneTrackBrawl != null : "fx:id=\"paneTrackBrawl\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tablePessoaColunaForca != null : "fx:id=\"tablePessoaColunaForca\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tablePessoaColunaNome != null : "fx:id=\"tablePessoaColunaNome\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tablePessoaColunaPosicao != null : "fx:id=\"tablePessoaColunaPosicao\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableTrilhoColunaBitola != null : "fx:id=\"tableTrilhoColunaBitola\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableTrilhoColunaComprimento != null : "fx:id=\"tableTrilhoColunaComprimento\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableTrilhoColunaMaterial != null : "fx:id=\"tableTrilhoColunaMaterial\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableVencedorColunaNome != null : "fx:id=\"tableVencedorColunaNome\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableVencedorColunaPontuacao != null : "fx:id=\"tableVencedorColunaPontuacao\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableVencedorColunaTempoVitoria != null : "fx:id=\"tableVencedorColunaTempoVitoria\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableViewPessoa != null : "fx:id=\"tableViewPessoa\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableViewTrilho != null : "fx:id=\"tableViewTrilho\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert tableViewVencedor != null : "fx:id=\"tableViewVencedor\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";
        assert txtAreaMetodosTrackBrawl != null : "fx:id=\"txtAreaMetodosTrackBrawl\" was not injected: check your FXML file 'PopUpTrackBrawl.fxml'.";

        tablePessoaColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablePessoaColunaForca.setCellValueFactory(new PropertyValueFactory<>("forca"));
        tablePessoaColunaPosicao.setCellValueFactory(new PropertyValueFactory<>("posicao"));
        tableTrilhoColunaBitola.setCellValueFactory(new PropertyValueFactory<>("bitola"));
        tableTrilhoColunaComprimento.setCellValueFactory(new PropertyValueFactory<>("comprimento"));
        tableTrilhoColunaMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        tableVencedorColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableVencedorColunaPontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        tableVencedorColunaTempoVitoria.setCellValueFactory(new PropertyValueFactory<>("tempoVitoria"));
        carregarDadosTrackBrawl();
    }

}
