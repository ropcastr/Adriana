package fatec.gov.br.e1.calculomedia.controller;

import fatec.gov.br.e1.calculomedia.ResultadoMedia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class CalculoMediaController {

    @FXML private TextField txtE1; @FXML private TextField txtE2; @FXML private TextField txtP1; @FXML private TextField txtX; @FXML private TextField txtAPI; @FXML private TextField txtSUB;
    @FXML private Button btnCalcular; @FXML private Button btnLimpar; @FXML private Button btnAbrirCsv;
    @FXML private Label lblStatus;
    @FXML private TableView<ResultadoMedia> tabelaResultados;
    @FXML private TableColumn<ResultadoMedia, Double> colE1; @FXML private TableColumn<ResultadoMedia, Double> colE2; @FXML private TableColumn<ResultadoMedia, Double> colP1;
    @FXML private TableColumn<ResultadoMedia, Double> colX; @FXML private TableColumn<ResultadoMedia, Double> colAPI; @FXML private TableColumn<ResultadoMedia, Double> colSUB;
    @FXML private TableColumn<ResultadoMedia, Double> colBase; @FXML private TableColumn<ResultadoMedia, Double> colMedia; @FXML private TableColumn<ResultadoMedia, String> colStatus;
    @FXML private TableColumn<ResultadoMedia, String> colTimestamp;

    private final ObservableList<ResultadoMedia> dados = FXCollections.observableArrayList();
    private Path csvPath;

    @FXML
    public void initialize() {
        // Prepara colunas
        colE1.setCellValueFactory(new PropertyValueFactory<>("e1"));
        colE2.setCellValueFactory(new PropertyValueFactory<>("e2"));
        colP1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        colX.setCellValueFactory(new PropertyValueFactory<>("x"));
        colAPI.setCellValueFactory(new PropertyValueFactory<>("api"));
        colSUB.setCellValueFactory(new PropertyValueFactory<>("sub"));
        colBase.setCellValueFactory(new PropertyValueFactory<>("base"));
        colMedia.setCellValueFactory(new PropertyValueFactory<>("mediaFinal"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        tabelaResultados.setItems(dados);

        // Define caminho do CSV dentro do package (fonte). ATENÇÃO: para produção usar diretório de dados externo.
        csvPath = Paths.get("src","main","java","fatec","gov","br","e1","calculomedia","notas.csv");
        carregarCsvExistente();
    }

    @FXML
    public void calcularMedia(ActionEvent e) {
        if (!validarIntervalo(txtE1,txtE2,txtP1,txtX,txtAPI,txtSUB)) {
            lblStatus.setText("Valores devem estar entre 0 e 10.");
            return;
        }
        double e1 = parseCampo(txtE1); double e2 = parseCampo(txtE2); double p1 = parseCampo(txtP1); double x = parseCampo(txtX); double api = parseCampo(txtAPI); double sub = parseCampo(txtSUB);
        double base = (p1 * 0.5) + (e1 * 0.2) + (e2 * 0.3) + x + (sub * 0.15);
        double parte1 = base * 0.5;
        double parte2 = base > 5.9 ? api * 0.5 : 0; // simplificado
        double media = parte1 + parte2;
        String status = media >= 6 ? "Aprovado" : "Reprovado";
        ResultadoMedia resultado = new ResultadoMedia(e1,e2,p1,x,api,sub,base,media,status);
        dados.add(resultado);
        lblStatus.setText(String.format("Média: %.2f - %s", media, status));
        escreverLinhaCsv(resultado);
    }

    private boolean validarIntervalo(TextField... campos) {
        for (TextField t: campos) {
            double v = parseCampo(t);
            if (v < 0 || v > 10) return false;
        }
        return true;
    }

    @FXML public void abrirCsv(ActionEvent e) {
        try { java.awt.Desktop.getDesktop().open(csvPath.toFile()); } catch (Exception ex) { lblStatus.setText("Falha ao abrir CSV"); }
    }

    @FXML
    public void limparCampos(ActionEvent e) {
        txtE1.clear(); txtE2.clear(); txtP1.clear(); txtX.clear(); txtAPI.clear(); txtSUB.clear(); lblStatus.setText("");
    }

    private double parseCampo(TextField tf) {
        String v = tf.getText();
        if (v == null || v.trim().isEmpty()) return 0.0;
        try { return Double.parseDouble(v.replace(',', '.')); } catch (NumberFormatException ex) { return 0.0; }
    }

    private void carregarCsvExistente() {
        if (!Files.exists(csvPath)) return;
        try (BufferedReader br = Files.newBufferedReader(csvPath)) {
            List<String> linhas = br.lines().collect(Collectors.toList());
            for (int i=0;i<linhas.size();i++) {
                String linha = linhas.get(i);
                if (linha.startsWith("E1;")) continue; // header
                String[] partes = linha.split(";");
                if (partes.length < 10) continue;
                double e1 = Double.parseDouble(partes[0]);
                double e2 = Double.parseDouble(partes[1]);
                double p1 = Double.parseDouble(partes[2]);
                double x = Double.parseDouble(partes[3]);
                double api = Double.parseDouble(partes[4]);
                double sub = Double.parseDouble(partes[5]);
                double base = Double.parseDouble(partes[6]);
                double media = Double.parseDouble(partes[7]);
                String status = partes[8];
                // timestamp partes[9] será gerado novamente (mantemos original para visualização)
                ResultadoMedia r = new ResultadoMedia(e1,e2,p1,x,api,sub,base,media,status) {
                    private final String tsOriginal = partes[9];
                    @Override public String getTimestamp() { return tsOriginal; }
                };
                dados.add(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void escreverLinhaCsv(ResultadoMedia r) {
        try {
            if (!Files.exists(csvPath)) {
                Files.createDirectories(csvPath.getParent());
                Files.writeString(csvPath, ResultadoMedia.csvHeader() + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            Files.writeString(csvPath, r.toCsvLine() + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
