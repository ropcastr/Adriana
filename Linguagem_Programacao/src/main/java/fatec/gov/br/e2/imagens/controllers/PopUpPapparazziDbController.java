package fatec.gov.br.e2.imagens.controllers;

import fatec.gov.br.e2.imagens.vikmuniz.PaparazziRecordDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PopUpPapparazziDbController {
    @FXML private TextArea txtAreaMetodosPapparazziDb;
    private final PaparazziRecordDAO dao = new PaparazziRecordDAO();
    @FXML public void initialize(){ var msgs = dao.listarMensagens(); txtAreaMetodosPapparazziDb.setText(String.join("\n", msgs)); }
}

