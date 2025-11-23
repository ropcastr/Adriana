package fatec.gov.br.e1.video.controller;

import fatec.gov.br.e1.video.Chef;
import fatec.gov.br.e1.video.Ingrediente;
import fatec.gov.br.e1.video.Utensilio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VideoController {

    @FXML private MediaView mediaView;
    @FXML private TextField txtChefNome; @FXML private TextField txtChefExperiencia; @FXML private TextField txtChefEspecialidade;
    @FXML private TextField txtIngNome; @FXML private TextField txtIngQuantidade; @FXML private TextField txtIngUnidade;
    @FXML private TextField txtUteTipo; @FXML private TextField txtUteMaterial; @FXML private TextField txtUteTamanho;
    @FXML private Label lblStatus; @FXML private TextArea txtAreaLog;
    @FXML private TableView<Chef> tableChef; @FXML private TableColumn<Chef,String> colChefNome; @FXML private TableColumn<Chef,Integer> colChefExperiencia; @FXML private TableColumn<Chef,String> colChefEspecialidade;
    @FXML private TableView<Ingrediente> tableIngrediente; @FXML private TableColumn<Ingrediente,String> colIngNome; @FXML private TableColumn<Ingrediente,Double> colIngQuantidade; @FXML private TableColumn<Ingrediente,String> colIngUnidade;
    @FXML private TableView<Utensilio> tableUtensilio; @FXML private TableColumn<Utensilio,String> colUteTipo; @FXML private TableColumn<Utensilio,String> colUteMaterial; @FXML private TableColumn<Utensilio,Double> colUteTamanho;
    @FXML private TableColumn<Chef,String> colChefTimestamp; // nova coluna

    private MediaPlayer mediaPlayer;
    private final ObservableList<Chef> chefs = FXCollections.observableArrayList();
    private final ObservableList<Ingrediente> ingredientes = FXCollections.observableArrayList();
    private final ObservableList<Utensilio> utensilios = FXCollections.observableArrayList();
    private Path csvPath;

    @FXML
    public void initialize() {
        try {
            URL videoUrl = getClass().getResource("/ei/assets/video.mp4");
            if (videoUrl != null) {
                Media media = new Media(videoUrl.toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.setAutoPlay(false);
                // Pré-buffer: baixa volume inicialmente
                mediaPlayer.setVolume(0);
                mediaPlayer.setOnReady(() -> {
                    // Restaurar volume padrão após estar pronto
                    mediaPlayer.setVolume(1.0);
                });
            }
        } catch (Exception e) { e.printStackTrace(); }

        // Colunas
        colChefNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colChefExperiencia.setCellValueFactory(new PropertyValueFactory<>("experiencia"));
        colChefEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colChefTimestamp.setCellValueFactory(new PropertyValueFactory<>("criadoEm"));
        tableChef.setItems(chefs);

        colIngNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIngQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colIngUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade"));
        tableIngrediente.setItems(ingredientes);

        colUteTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colUteMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colUteTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        tableUtensilio.setItems(utensilios);

        csvPath = Paths.get("target","classes","fatec","gov","br","e1","video","cozinha.csv");

        // carregar CSV existente
        carregarCsvExistente();
    }

    private void carregarCsvExistente() {
        try {
            if (Files.exists(csvPath)) {
                for (String linha : Files.readAllLines(csvPath)) {
                    if (linha.startsWith("CHEF_NOME")) continue;
                    String[] p = linha.split(";");
                    if (p.length < 10) continue;
                    Chef chef = new Chef(p[0], Integer.parseInt(p[1]), p[2]);
                    Ingrediente ing = new Ingrediente(p[3], Double.parseDouble(p[4]), p[5]);
                    Utensilio ute = new Utensilio(p[6], p[7], Double.parseDouble(p[8]));
                    chefs.add(chef); ingredientes.add(ing); utensilios.add(ute);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void cozinhar() {
        lblStatus.setText("");
        // Validação obrigatória
        if (vazio(txtChefNome) || vazio(txtChefExperiencia) || vazio(txtChefEspecialidade) || vazio(txtIngNome) || vazio(txtIngQuantidade) || vazio(txtIngUnidade) || vazio(txtUteTipo) || vazio(txtUteMaterial) || vazio(txtUteTamanho)) {
            lblStatus.setText("Preencha todos os campos.");
            return;
        }
        try {
            int exp = parseIntPositivo(txtChefExperiencia.getText(), "Experiência inválida");
            double ingQtd = parseDoublePositivo(txtIngQuantidade.getText(), "Quantidade inválida");
            double uteTam = parseDoublePositivo(txtUteTamanho.getText(), "Tamanho inválido");

            Chef chef = new Chef(txtChefNome.getText().trim(), exp, txtChefEspecialidade.getText().trim());
            Ingrediente ing = new Ingrediente(txtIngNome.getText().trim(), ingQtd, txtIngUnidade.getText().trim());
            Utensilio ute = new Utensilio(txtUteTipo.getText().trim(), txtUteMaterial.getText().trim(), uteTam);
            // Associações simples
            chef.adicionarIngrediente(ing);
            chef.adicionarUtensilio(ute);
            ute.adicionarIngrediente(ing);

            chefs.add(chef);
            ingredientes.add(ing);
            utensilios.add(ute);

            StringBuilder log = new StringBuilder();
            log.append("["+timestamp()+"] Preparando ingrediente...\n");
            ing.cortar(); ing.temperar(); ing.limpar();
            log.append("Ingrediente preparado: "+ing.getNome()+"\n");
            log.append("["+timestamp()+"] Cozinhando...\n");
            chef.cozinhar(); chef.provarComida();
            log.append("Chef "+chef.getNome()+" cozinhou usando "+ute.getTipo()+"\n");
            log.append("Chef provou a comida.\n");

            txtAreaLog.appendText(log.toString());
            lblStatus.setText("Cozinha registrada. Iniciando vídeo...");
            escreverCsvLinha(chef, ing, ute);
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.seek(javafx.util.Duration.ZERO);
                // Aguarda estar pronto (caso ainda não) antes de reproduzir
                if (mediaPlayer.getStatus() == MediaPlayer.Status.READY) {
                    mediaPlayer.play();
                } else {
                    mediaPlayer.setOnReady(() -> mediaPlayer.play());
                }
            }
            limparCampos();
        } catch (IllegalArgumentException iae) {
            lblStatus.setText(iae.getMessage());
        } catch (Exception ex) {
            lblStatus.setText("Erro ao cozinhar.");
            ex.printStackTrace();
        }
    }

    private boolean vazio(TextField tf) { return tf.getText()==null || tf.getText().trim().isEmpty(); }

    private void limparCampos() {
        txtChefNome.clear(); txtChefExperiencia.clear(); txtChefEspecialidade.clear();
        txtIngNome.clear(); txtIngQuantidade.clear(); txtIngUnidade.clear();
        txtUteTipo.clear(); txtUteMaterial.clear(); txtUteTamanho.clear();
    }

    private String timestamp() { return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }

    private void escreverCsvLinha(Chef chef, Ingrediente ing, Utensilio ute) {
        try {
            if (!Files.exists(csvPath.getParent())) {
                Files.createDirectories(csvPath.getParent());
            }
            if (!Files.exists(csvPath)) {
                Files.writeString(csvPath, "CHEF_NOME;CHEF_EXP;CHEF_ESP;ING_NOME;ING_QTD;ING_UNID;UTE_TIPO;UTE_MAT;UTE_TAM;TIMESTAMP"+System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            String linha = String.join(";",
                    chef.getNome(), String.valueOf(chef.getExperiencia()), chef.getEspecialidade(),
                    ing.getNome(), String.valueOf(ing.getQuantidade()), ing.getUnidade(),
                    ute.getTipo(), ute.getMaterial(), String.valueOf(ute.getTamanho()), timestamp());
            Files.writeString(csvPath, linha+System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private int parseIntPositivo(String v, String msg) { int val = Integer.parseInt(v.trim()); if (val <=0) throw new IllegalArgumentException(msg); return val; }
    private double parseDoublePositivo(String v, String msg) { double val = Double.parseDouble(v.trim().replace(',','.')); if (val <=0) throw new IllegalArgumentException(msg); return val; }

    public void stop() { if (mediaPlayer != null) mediaPlayer.stop(); }
}
