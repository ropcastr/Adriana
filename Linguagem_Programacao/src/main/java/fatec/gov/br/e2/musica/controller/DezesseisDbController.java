package fatec.gov.br.e2.musica.controller;

import fatec.gov.br.e1.musica.Carro;
import fatec.gov.br.e1.musica.CurvaDoDiabo;
import fatec.gov.br.e1.musica.Pessoa;
import fatec.gov.br.e1.musica.Violao;
import fatec.gov.br.e2.musica.MusicaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DezesseisDbController {
    @FXML private TextField txtPessoaNome, txtPessoaIdade, txtPessoaReputacao;
    @FXML private TextField txtCarroModelo, txtCarroCor, txtCarroMotor;
    @FXML private TextField txtViolaoCordas, txtViolaoMadeira, txtViolaoTimbre;
    @FXML private TextField txtCurvaDiaboNome, txtCurvaDiaboLocalizacao, txtCurvaDiaboPerigosidade;
    @FXML private TextArea txtAreaMetodoDb;
    @FXML private TableView<Pessoa> tableViePessoaDb; @FXML private TableColumn<Pessoa,String> tablePessoaColunaNomeDb; @FXML private TableColumn<Pessoa,Integer> tablePessoaColunaIdadeDb; @FXML private TableColumn<Pessoa,String> tablePessoaColunaReputacaoDb;
    @FXML private TableView<Carro> tableViewCarroDb; @FXML private TableColumn<Carro,String> tableCarroColunaModeloDb; @FXML private TableColumn<Carro,String> tableCarroColunaCorDb; @FXML private TableColumn<Carro,String> tableCarroColunaMotorDb;
    @FXML private TableView<Violao> tableViewViolaoDb; @FXML private TableColumn<Violao,Integer> tableViolaoColunaCordasDb; @FXML private TableColumn<Violao,String> tableViolaoColunaMadeiraDb; @FXML private TableColumn<Violao,String> tableViolaoColunaTimbreDb;
    @FXML private TableView<CurvaDoDiabo> tableViewCurvaDiaboDb; @FXML private TableColumn<CurvaDoDiabo,String> tableCurvaDiaboColunaNomeDb; @FXML private TableColumn<CurvaDoDiabo,String> tableCurvaDiaboColunaLocalizacaoDb; @FXML private TableColumn<CurvaDoDiabo,String> tableCurvaDiaboColunaPerigosidadeDb;
    @FXML private Button btnGravarAtributosDb, btnRecarregarDb;
    @FXML private Button btnExportarCsvDb; @FXML private Button btnLimparBancoDb;

    private final MusicaDAO dao = new MusicaDAO();
    private final ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
    private final ObservableList<Carro> carros = FXCollections.observableArrayList();
    private final ObservableList<Violao> violoes = FXCollections.observableArrayList();
    private final ObservableList<CurvaDoDiabo> curvas = FXCollections.observableArrayList();

    @FXML public void initialize(){
        tablePessoaColunaNomeDb.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablePessoaColunaIdadeDb.setCellValueFactory(new PropertyValueFactory<>("idade"));
        tablePessoaColunaReputacaoDb.setCellValueFactory(new PropertyValueFactory<>("reputacao"));
        tableCarroColunaModeloDb.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableCarroColunaCorDb.setCellValueFactory(new PropertyValueFactory<>("cor"));
        tableCarroColunaMotorDb.setCellValueFactory(new PropertyValueFactory<>("motor"));
        tableViolaoColunaCordasDb.setCellValueFactory(new PropertyValueFactory<>("cordas"));
        tableViolaoColunaMadeiraDb.setCellValueFactory(new PropertyValueFactory<>("madeira"));
        tableViolaoColunaTimbreDb.setCellValueFactory(new PropertyValueFactory<>("timbre"));
        tableCurvaDiaboColunaNomeDb.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableCurvaDiaboColunaLocalizacaoDb.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        tableCurvaDiaboColunaPerigosidadeDb.setCellValueFactory(new PropertyValueFactory<>("perigosidade"));
        tableViePessoaDb.setItems(pessoas); tableViewCarroDb.setItems(carros); tableViewViolaoDb.setItems(violoes); tableViewCurvaDiaboDb.setItems(curvas);
        btnGravarAtributosDb.setOnAction(e -> gravar());
        btnRecarregarDb.setOnAction(e -> carregarDados());
        if (btnExportarCsvDb != null) btnExportarCsvDb.setOnAction(e -> exportarCsv());
        if (btnLimparBancoDb != null) btnLimparBancoDb.setOnAction(e -> limparBanco());
        carregarDados();
    }

    private void carregarDados(){
        pessoas.clear(); carros.clear(); violoes.clear(); curvas.clear();
        dao.listarPessoas().forEach(m -> pessoas.add(new Pessoa((String)m.get("nome"),(Integer)m.get("idade"),(String)m.get("reputacao"))));
        dao.listarCarros().forEach(m -> carros.add(new Carro((String)m.get("modelo"),(String)m.get("cor"),(String)m.get("motor"))));
        dao.listarVioloes().forEach(m -> violoes.add(new Violao((Integer)m.get("cordas"),(String)m.get("madeira"),(String)m.get("timbre"))));
        dao.listarCurvas().forEach(m -> curvas.add(new CurvaDoDiabo((String)m.get("localizacao"),(String)m.get("perigosidade"),(String)m.get("nome"))));
        var msgs = dao.listarMetodos(); txtAreaMetodoDb.setText(msgs.isEmpty()?"Sem registros." : String.join("\n", msgs));
    }

    private void gravar(){
        if(camposVazios()){ txtAreaMetodoDb.setText("Preencha todos os campos."); return; }
        int idade = parseInt(txtPessoaIdade.getText()); if(idade<=0){ txtAreaMetodoDb.setText("Idade deve ser > 0"); return; }
        int cordas = parseInt(txtViolaoCordas.getText()); if(cordas<=0){ txtAreaMetodoDb.setText("Cordas deve ser > 0"); return; }
        Pessoa pessoa = new Pessoa(txtPessoaNome.getText(), idade, txtPessoaReputacao.getText());
        Carro carro = new Carro(txtCarroModelo.getText(), txtCarroCor.getText(), txtCarroMotor.getText());
        Violao violao = new Violao(cordas, txtViolaoMadeira.getText(), txtViolaoTimbre.getText());
        CurvaDoDiabo curva = new CurvaDoDiabo(txtCurvaDiaboLocalizacao.getText(), txtCurvaDiaboPerigosidade.getText(), txtCurvaDiaboNome.getText());
        String pessoaMsg = pessoa.PessoaInfo(); String carroMsg = carro.pilotarCarro(); String violaoMsg = violao.pegarViolao(); String curvaMsg = curva.acidentePega();
        dao.salvarPessoa(pessoa.getNome(), pessoa.getIdade(), pessoa.getReputacao());
        dao.salvarCarro(carro.getModelo(), carro.getCor(), carro.getMotor());
        dao.salvarViolao(violao.getCordas(), violao.getMadeira(), violao.getTimbre());
        dao.salvarCurva(curva.getLocalizacao(), curva.getPerigosidade(), curva.getNome());
        dao.salvarMetodos(pessoaMsg, carroMsg, violaoMsg, curvaMsg);
        carregarDados();
        txtAreaMetodoDb.setText(pessoaMsg+"\n"+carroMsg+"\n"+violaoMsg+"\n"+curvaMsg);
        limparCampos();
    }

    private void exportarCsv(){
        FileChooser fc=new FileChooser(); fc.setTitle("Exportar CSV"); fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
        Stage st=(Stage) txtAreaMetodoDb.getScene().getWindow();
        var f = fc.showSaveDialog(st); if(f==null){ return; }
        try { dao.exportarCsv(f.getAbsolutePath()); txtAreaMetodoDb.setText("Exportado: "+f.getAbsolutePath()); } catch(Exception ex){ txtAreaMetodoDb.setText("Falha ao exportar CSV"); }
    }

    private void limparBanco(){ dao.limparBanco(); carregarDados(); txtAreaMetodoDb.setText("Banco limpo."); }
    private boolean camposVazios(){ return
        vazio(txtPessoaNome)||vazio(txtPessoaIdade)||vazio(txtPessoaReputacao)||
        vazio(txtCarroModelo)||vazio(txtCarroCor)||vazio(txtCarroMotor)||
        vazio(txtViolaoCordas)||vazio(txtViolaoMadeira)||vazio(txtViolaoTimbre)||
        vazio(txtCurvaDiaboNome)||vazio(txtCurvaDiaboLocalizacao)||vazio(txtCurvaDiaboPerigosidade); }
    private boolean vazio(TextField f){ return f.getText()==null||f.getText().trim().isEmpty(); }
    private int parseInt(String v){ try{return Integer.parseInt(v);}catch(Exception e){return 0;} }
    private void limparCampos(){
        txtPessoaNome.clear(); txtPessoaIdade.clear(); txtPessoaReputacao.clear();
        txtCarroModelo.clear(); txtCarroCor.clear(); txtCarroMotor.clear();
        txtViolaoCordas.clear(); txtViolaoMadeira.clear(); txtViolaoTimbre.clear();
        txtCurvaDiaboNome.clear(); txtCurvaDiaboLocalizacao.clear(); txtCurvaDiaboPerigosidade.clear();
    }
}
