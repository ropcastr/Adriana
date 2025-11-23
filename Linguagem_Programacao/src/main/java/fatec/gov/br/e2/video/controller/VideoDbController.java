package fatec.gov.br.e2.video.controller;

import fatec.gov.br.e1.video.Chef;
import fatec.gov.br.e1.video.Ingrediente;
import fatec.gov.br.e1.video.Utensilio;
import fatec.gov.br.e2.video.VideoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VideoDbController {
    @FXML private MediaView mediaView;
    @FXML private TextField txtChefNome, txtChefExperiencia, txtChefEspecialidade;
    @FXML private TextField txtIngNome, txtIngQuantidade, txtIngUnidade;
    @FXML private TextField txtUteTipo, txtUteMaterial, txtUteTamanho;
    @FXML private Label lblStatus; @FXML private TextArea txtAreaLog;
    @FXML private TableView<Chef> tableChef; @FXML private TableColumn<Chef,String> colChefNome; @FXML private TableColumn<Chef,Integer> colChefExperiencia; @FXML private TableColumn<Chef,String> colChefEspecialidade; @FXML private TableColumn<Chef,String> colChefTimestamp;
    @FXML private TableView<Ingrediente> tableIngrediente; @FXML private TableColumn<Ingrediente,String> colIngNome; @FXML private TableColumn<Ingrediente,Double> colIngQuantidade; @FXML private TableColumn<Ingrediente,String> colIngUnidade;
    @FXML private TableView<Utensilio> tableUtensilio; @FXML private TableColumn<Utensilio,String> colUteTipo; @FXML private TableColumn<Utensilio,String> colUteMaterial; @FXML private TableColumn<Utensilio,Double> colUteTamanho;
    @FXML private Button btnCozinharDb, btnLimparDb;

    private final ObservableList<Chef> chefs= FXCollections.observableArrayList();
    private final ObservableList<Ingrediente> ingredientes= FXCollections.observableArrayList();
    private final ObservableList<Utensilio> utensilios= FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;
    private final VideoDAO dao = new VideoDAO();

    @FXML public void initialize(){
        URL videoUrl = getClass().getResource("/ei/assets/video.mp4");
        if(videoUrl!=null){ Media media=new Media(videoUrl.toExternalForm()); mediaPlayer=new MediaPlayer(media); mediaView.setMediaPlayer(mediaPlayer); mediaPlayer.setAutoPlay(false); }
        colChefNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colChefExperiencia.setCellValueFactory(new PropertyValueFactory<>("experiencia"));
        colChefEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colChefTimestamp.setCellValueFactory(new PropertyValueFactory<>("criadoEm"));
        tableChef.setItems(chefs);
        colIngNome.setCellValueFactory(new PropertyValueFactory<>("nome")); colIngQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade")); colIngUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade")); tableIngrediente.setItems(ingredientes);
        colUteTipo.setCellValueFactory(new PropertyValueFactory<>("tipo")); colUteMaterial.setCellValueFactory(new PropertyValueFactory<>("material")); colUteTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho")); tableUtensilio.setItems(utensilios);
        btnCozinharDb.setOnAction(e -> cozinhar());
        btnLimparDb.setOnAction(e -> limpar());
        carregarLogs();
    }

    private void cozinhar(){ lblStatus.setText(""); if(camposVazios()){ lblStatus.setText("Preencha todos os campos."); return; }
        try {
            int exp = parseIntPositivo(txtChefExperiencia.getText(),"Experiência inválida");
            double ingQtd = parseDoublePositivo(txtIngQuantidade.getText(),"Quantidade inválida");
            double uteTam = parseDoublePositivo(txtUteTamanho.getText(),"Tamanho inválido");
            Chef chef = new Chef(txtChefNome.getText().trim(), exp, txtChefEspecialidade.getText().trim());
            Ingrediente ing = new Ingrediente(txtIngNome.getText().trim(), ingQtd, txtIngUnidade.getText().trim());
            Utensilio ute = new Utensilio(txtUteTipo.getText().trim(), txtUteMaterial.getText().trim(), uteTam);
            chef.adicionarIngrediente(ing); chef.adicionarUtensilio(ute); ute.adicionarIngrediente(ing);
            chefs.add(0,chef); ingredientes.add(0,ing); utensilios.add(0,ute);
            dao.salvarChef(chef.getNome(), chef.getExperiencia(), chef.getEspecialidade(), chef.getCriadoEm());
            dao.salvarIngrediente(ing.getNome(), ing.getQuantidade(), ing.getUnidade());
            dao.salvarUtensilio(ute.getTipo(), ute.getMaterial(), ute.getTamanho());
            StringBuilder log = new StringBuilder(); log.append("Preparando ingrediente: ").append(ing.getNome()).append("\n"); ing.cortar(); ing.temperar(); ing.limpar();
            log.append("Cozinhando com utensílio: ").append(ute.getTipo()).append("\n"); chef.cozinhar(); chef.provarComida();
            dao.salvarLog(log.toString()); txtAreaLog.appendText("["+timestamp()+"] "+log+"\n");
            if(mediaPlayer!=null){ mediaPlayer.stop(); mediaPlayer.seek(javafx.util.Duration.ZERO); mediaPlayer.play(); }
            limparCampos(); lblStatus.setText("Registro salvo.");
        } catch(IllegalArgumentException ex){ lblStatus.setText(ex.getMessage()); }
    }

    private void carregarLogs(){ var logs=dao.listarLogs(); for(String l: logs){ txtAreaLog.appendText(l+"\n"); } }
    private void limpar(){ dao.limparTudo(); chefs.clear(); ingredientes.clear(); utensilios.clear(); txtAreaLog.clear(); lblStatus.setText("Banco limpo."); }
    private boolean camposVazios(){ return vazio(txtChefNome)||vazio(txtChefExperiencia)||vazio(txtChefEspecialidade)||vazio(txtIngNome)||vazio(txtIngQuantidade)||vazio(txtIngUnidade)||vazio(txtUteTipo)||vazio(txtUteMaterial)||vazio(txtUteTamanho); }
    private boolean vazio(TextField t){ return t.getText()==null||t.getText().trim().isEmpty(); }
    private int parseIntPositivo(String v,String msg){ int val=Integer.parseInt(v.trim()); if(val<=0) throw new IllegalArgumentException(msg); return val; }
    private double parseDoublePositivo(String v,String msg){ double val=Double.parseDouble(v.trim().replace(',','.')); if(val<=0) throw new IllegalArgumentException(msg); return val; }
    private String timestamp(){ return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }
    private void limparCampos(){ txtChefNome.clear(); txtChefExperiencia.clear(); txtChefEspecialidade.clear(); txtIngNome.clear(); txtIngQuantidade.clear(); txtIngUnidade.clear(); txtUteTipo.clear(); txtUteMaterial.clear(); txtUteTamanho.clear(); }
}

