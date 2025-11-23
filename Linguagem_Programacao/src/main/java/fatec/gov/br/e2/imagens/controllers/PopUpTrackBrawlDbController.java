package fatec.gov.br.e2.imagens.controllers;

import fatec.gov.br.e2.imagens.vikmuniz.TrackBrawlRecordDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PopUpTrackBrawlDbController {
    @FXML private TextArea txtAreaMetodosTrackBrawlDb;
    private final TrackBrawlRecordDAO dao = new TrackBrawlRecordDAO();
    @FXML public void initialize(){ var msgs = dao.listarMensagens(); txtAreaMetodosTrackBrawlDb.setText(String.join("\n", msgs)); }
}

