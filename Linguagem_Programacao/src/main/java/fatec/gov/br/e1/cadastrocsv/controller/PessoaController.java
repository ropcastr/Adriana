package fatec.gov.br.e1.cadastrocsv.controller;

import fatec.gov.br.e1.cadastrocsv.Pessoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PessoaController {

    @FXML
    private Button btnCadastrarPessoa;

    @FXML
    private Label saida;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtMassa;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSexo;

    @FXML
    public void CadastrarPessoa(ActionEvent event) {
        try {
            String nome = txtNome.getText();
            String sexo = txtSexo.getText();
            String alturaStr = txtAltura.getText();
            String massaStr = txtMassa.getText();

            if (nome.isEmpty() || sexo.isEmpty() || alturaStr.isEmpty() || massaStr.isEmpty()) {
                saida.setText("Preencha todos os campos.");
                return;
            }

            double altura = Double.parseDouble(alturaStr);
            double massa = Double.parseDouble(massaStr);

            Pessoa p = new Pessoa(nome, sexo, altura, massa);
            p.salvar();
            saida.setText("Pessoa cadastrada com sucesso!");
            txtNome.setText("");
            txtSexo.setText("");
            txtAltura.setText("");
            txtMassa.setText("");
        } catch (NumberFormatException e) {
            saida.setText("Altura e massa devem ser números válidos.");
        } catch (Exception e) {
            saida.setText("Erro ao cadastrar pessoa.");
        }
    }

}
