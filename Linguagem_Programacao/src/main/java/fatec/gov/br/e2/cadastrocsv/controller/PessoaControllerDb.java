package fatec.gov.br.e2.cadastrocsv.controller;

import fatec.gov.br.e2.cadastrocsv.Pessoa;
import fatec.gov.br.e2.cadastrocsv.PessoaDAO;

public class PessoaControllerDb {
    private final PessoaDAO dao;
    public PessoaControllerDb(PessoaDAO dao) { this.dao = dao; }

    public Pessoa cadastrar(String nome, String sexo, String alturaStr, String massaStr) {
        if (nome == null || nome.isBlank() || sexo == null || sexo.isBlank() || alturaStr == null || massaStr == null) {
            throw new IllegalArgumentException("Campos obrigatórios ausentes");
        }
        double altura = Double.parseDouble(alturaStr.replace(',', '.'));
        double massa = Double.parseDouble(massaStr.replace(',', '.'));
        if (altura <= 0 || massa <= 0) {
            throw new IllegalArgumentException("Altura e massa devem ser maiores que zero");
        }
        Pessoa p = new Pessoa(nome.trim(), sexo.trim(), altura, massa);
        return dao.salvar(p);
    }
}

