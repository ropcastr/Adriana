package fatec.gov.br.e2.cadastrocsv.controller;

import fatec.gov.br.e2.cadastrocsv.Pessoa;
import fatec.gov.br.e2.cadastrocsv.PessoaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.stream.Collectors;

public class PessoaDbFxController {
    @FXML private TextField txtNome;
    @FXML private TextField txtMassa;
    @FXML private TextField txtAltura;
    @FXML private TextField txtSexo;
    @FXML private TextArea saida;

    private PessoaDAO dao;

    @FXML
    public void initialize() {
        this.dao = new PessoaDAO();
    }

    @FXML
    public void salvarPessoa() {
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
            dao.salvar(p);
            saida.setText("Pessoa salva com id=" + p.getId());
            limparCampos();
        } catch (NumberFormatException e) {
            saida.setText("Altura e massa devem ser números válidos.");
        } catch (Exception e) {
            saida.setText("Erro ao salvar pessoa.");
        }
    }

    @FXML
    public void listarPessoas() {
        var lista = dao.listarTodos();
        if (lista.isEmpty()) {
            saida.setText("Nenhuma pessoa cadastrada.");
        } else {
            saida.setText(lista.stream().map(Pessoa::toString).collect(Collectors.joining("\n")));
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtSexo.clear();
        txtAltura.clear();
        txtMassa.clear();
    }
}
