package fatec.gov.br.e2.calculomedia.controller;

import fatec.gov.br.e2.calculomedia.ResultadoMedia;
import fatec.gov.br.e2.calculomedia.ResultadoMediaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CalculoMediaDbController {
    @FXML private TextField txtE1, txtE2, txtP1, txtX, txtAPI, txtSUB;
    @FXML private Label lblStatus;
    @FXML private TableView<ResultadoMedia> tabelaResultados;
    @FXML private TableColumn<ResultadoMedia, Double> colE1, colE2, colP1, colX, colAPI, colSUB, colBase, colMedia;
    @FXML private TableColumn<ResultadoMedia, String> colStatus, colTimestamp;
    @FXML private Button btnCalcular, btnLimpar, btnRecarregar, btnLimparBanco;

    private final ObservableList<ResultadoMedia> dados = FXCollections.observableArrayList();
    private ResultadoMediaDAO dao;

    @FXML public void initialize(){
        dao = new ResultadoMediaDAO();
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
        recarregar();
    }

    @FXML public void calcularMedia(){
        if(!validarIntervalo(txtE1,txtE2,txtP1,txtX,txtAPI,txtSUB)){ lblStatus.setText("Valores devem 0..10"); return; }
        double e1 = parseCampo(txtE1); double e2 = parseCampo(txtE2); double p1 = parseCampo(txtP1); double x = parseCampo(txtX); double api = parseCampo(txtAPI); double sub = parseCampo(txtSUB);
        double base = (p1 * 0.5) + (e1 * 0.2) + (e2 * 0.3) + x + (sub * 0.15);
        double parte1 = base * 0.5;
        double parte2 = base > 5.9 ? api * 0.5 : 0;
        double media = parte1 + parte2;
        String status = media >= 6 ? "Aprovado" : "Reprovado";
        ResultadoMedia r = new ResultadoMedia(e1,e2,p1,x,api,sub,base,media,status);
        dao.salvar(r); dados.add(0,r); // adiciona no topo
        lblStatus.setText(String.format("Média: %.2f - %s (id=%d)",media,status,r.getId()));
        limparCampos();
    }

    @FXML public void recarregar(){
        dados.setAll(dao.listar());
    }

    @FXML public void limparCampos(){ txtE1.clear(); txtE2.clear(); txtP1.clear(); txtX.clear(); txtAPI.clear(); txtSUB.clear(); }

    @FXML public void limparBanco(){ dao.deletarTodos(); dados.clear(); lblStatus.setText("Banco limpo."); }

    private boolean validarIntervalo(TextField...campos){ for(TextField t: campos){ double v = parseCampo(t); if(v<0||v>10) return false; } return true; }
    private double parseCampo(TextField tf){ String v=tf.getText(); if(v==null||v.trim().isEmpty()) return 0.0; try{return Double.parseDouble(v.replace(',', '.'));}catch(NumberFormatException e){return 0.0;} }
}

