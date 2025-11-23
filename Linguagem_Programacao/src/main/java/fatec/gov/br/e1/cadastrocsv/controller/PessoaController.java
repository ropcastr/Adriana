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

    // Handler chamado pelo onAction="#cadastrarPessoa" definido em pessoa.fxml
    @FXML
    public void cadastrarPessoa(ActionEvent event) {
        try {
            String nome = txtNome.getText().trim();
            String sexo = txtSexo.getText().trim();
            String alturaStr = txtAltura.getText().trim();
            String massaStr = txtMassa.getText().trim();

            if (nome.isEmpty() || sexo.isEmpty() || alturaStr.isEmpty() || massaStr.isEmpty()) {
                saida.setText("Preencha todos os campos.");
                return;
            }

            double altura = Double.parseDouble(alturaStr.replace(',', '.'));
            double massa = Double.parseDouble(massaStr.replace(',', '.'));

            if (altura <= 0 || massa <= 0) {
                saida.setText("Altura e massa devem ser maiores que zero.");
                return;
            }

            Pessoa p = new Pessoa(nome, sexo, altura, massa);
            p.salvar();
            saida.setText("Pessoa cadastrada com sucesso!");
            limparCampos();
        } catch (NumberFormatException e) {
            saida.setText("Altura e massa devem ser números válidos.");
        } catch (Exception e) {
            saida.setText("Erro ao cadastrar pessoa.");
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtSexo.clear();
        txtAltura.clear();
        txtMassa.clear();
    }
}
