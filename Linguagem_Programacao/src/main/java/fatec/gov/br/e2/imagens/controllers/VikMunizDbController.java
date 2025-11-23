package fatec.gov.br.e2.imagens.controllers;

import fatec.gov.br.e2.imagens.vikmuniz.PaparazziRecordDAO;
import fatec.gov.br.e2.imagens.vikmuniz.TrackBrawlRecordDAO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class VikMunizDbController {
    @FXML private Button btnExibirPapparazziDb, btnExibirTrackBrawlDb, btnGravarPapparazziDb, btnGravarTrackBrawlDb;
    @FXML private TextField txtBrawlForcaDb, txtBrawlPosicaoDb, txtBrwalNomeDb;
    @FXML private TextField txtCameraModeloDb, txtExperienciaDb, txtFotografoNomeDb, txtMicrofoneCompCaboDb, txtMicrofoneMarcaDb, txtMicrofoneSensibilidadeDb, txtResolucaoDb, txtTipoCameraDb, txtTipoLenteDb, txtTrilhoBitolaDb, txtTrilhoComprimentoDb, txtTrilhoMaterialDb, txtVencedorNomeDb, txtVencedorPontuacaoDb, txtVencedorTempoVitoriaDb;
    @FXML private TextArea txtAreaStatusDb;

    private final PaparazziRecordDAO paparazziDAO = new PaparazziRecordDAO();
    private final TrackBrawlRecordDAO trackDAO = new TrackBrawlRecordDAO();

    @FXML void exibirAtributosPapparazziDb(ActionEvent e){ abrirPopup("/ei/ui/imagens/PopUpPapparazziDb.fxml","PopUp Paparazzi (DB)"); }
    @FXML void exibirAtributosTrackBrawlDb(ActionEvent e){ abrirPopup("/ei/ui/imagens/PopUpTrackBrawlDb.fxml","PopUp TrackBrawl (DB)"); }

    @FXML void gravarAtributosPapparazziDb(ActionEvent e){
        List<String> campos = Arrays.asList(txtCameraModeloDb.getText(), txtTipoCameraDb.getText(), txtTipoLenteDb.getText(), txtResolucaoDb.getText(), txtMicrofoneMarcaDb.getText(), txtMicrofoneCompCaboDb.getText(), txtMicrofoneSensibilidadeDb.getText(), txtFotografoNomeDb.getText(), txtExperienciaDb.getText());
        if (campos.stream().anyMatch(s -> s==null || s.trim().isEmpty())) { txtAreaStatusDb.setText("Preencha todos os campos Paparazzi."); return; }
        Camera camera = new Camera(txtCameraModeloDb.getText(), txtTipoLenteDb.getText(), parseInt(txtResolucaoDb.getText()));
        Fotografo fotografo = new Fotografo(txtFotografoNomeDb.getText(), txtTipoCameraDb.getText(), txtExperienciaDb.getText());
        Microfone microfone = new Microfone(txtMicrofoneMarcaDb.getText(), parseFloat(txtMicrofoneCompCaboDb.getText()), parseInt(txtMicrofoneSensibilidadeDb.getText()));
        String cameraMsg = camera.capturarImagem(); String fotografoMsg = fotografo.tirarFoto(); String microfoneMsg = microfone.capturarAudio();
        paparazziDAO.salvar(camera.getModelo(), camera.getLente(), camera.getResolucao(), microfone.getMarca(), microfone.getComprimentoCabo(), microfone.getSensibilidade(), fotografo.getNome(), fotografo.getAnosExperiencia(), cameraMsg, fotografoMsg, microfoneMsg);
        txtAreaStatusDb.setText("Registro Paparazzi salvo no DB.");
    }

    @FXML void gravarAtributosTrackBrawlDb(ActionEvent e){
        List<String> campos = Arrays.asList(txtBrwalNomeDb.getText(), txtBrawlForcaDb.getText(), txtBrawlPosicaoDb.getText(), txtTrilhoBitolaDb.getText(), txtTrilhoComprimentoDb.getText(), txtTrilhoMaterialDb.getText(), txtVencedorNomeDb.getText(), txtVencedorPontuacaoDb.getText(), txtVencedorTempoVitoriaDb.getText());
        if (campos.stream().anyMatch(s->s==null||s.trim().isEmpty())) { txtAreaStatusDb.setText("Preencha todos os campos TrackBrawl."); return; }
        Pessoa pessoa = new Pessoa(txtBrwalNomeDb.getText(), parseInt(txtBrawlForcaDb.getText()), txtBrawlPosicaoDb.getText());
        Trilhos trilhos = new Trilhos(parseFloat(txtTrilhoComprimentoDb.getText()), txtTrilhoMaterialDb.getText(), parseFloat(txtTrilhoBitolaDb.getText()));
        Vencedor vencedor = new Vencedor(txtVencedorNomeDb.getText(), parseInt(txtVencedorPontuacaoDb.getText()), txtVencedorTempoVitoriaDb.getText());
        String pessoaMsg = pessoa.atacar(); String trilhosMsg = trilhos.suportarTrem(); String vencedorMsg = vencedor.declararVitoria();
        trackDAO.salvar(pessoa.getNome(), pessoa.getForca(), pessoa.getPosicao(), trilhos.getBitola(), trilhos.getComprimento(), trilhos.getMaterial(), vencedor.getNome(), vencedor.getPontuacao(), vencedor.getTempoVitoria(), pessoaMsg, trilhosMsg, vencedorMsg);
        txtAreaStatusDb.setText("Registro TrackBrawl salvo no DB.");
    }

    private void abrirPopup(String fxml, String titulo){ try { Parent root = FXMLLoader.load(getClass().getResource(fxml)); Stage stage = new Stage(); stage.setTitle(titulo); stage.setScene(new Scene(root)); stage.show(); } catch (Exception ex){ ex.printStackTrace(); } }
    private int parseInt(String v){ try { return Integer.parseInt(v); } catch(Exception e){ return 0; } }
    private float parseFloat(String v){ try { return Float.parseFloat(v); } catch(Exception e){ return 0f; } }
}

