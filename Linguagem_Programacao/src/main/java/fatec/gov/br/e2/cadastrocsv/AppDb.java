package fatec.gov.br.e2.cadastrocsv;

import java.util.List;

/**
 * Aplicação console demonstrando CRUD com SQLite.
 * Observação: O arquivo pessoas.db é criado dentro de src conforme pedido, mas
 * em projetos reais recomenda-se colocá-lo fora de src (ex: em data/ ou target/).
 */
public class AppDb {
    public static void main(String[] args) {
        PessoaDAO dao = new PessoaDAO();

        // Limpa tudo para demonstração
        dao.deletarTodos();

        // Cadastra duas pessoas
        Pessoa p1 = dao.salvar(new Pessoa("Ana", "F", 1.70, 60));
        Pessoa p2 = dao.salvar(new Pessoa("Bruno", "M", 1.80, 80));

        System.out.println("Inseridos: " + p1 + ", " + p2);

        // Lista todas
        List<Pessoa> lista = dao.listarTodos();
        System.out.println("Lista inicial:");
        lista.forEach(System.out::println);

        // Atualiza p1
        p1.setMassa(61);
        dao.atualizar(p1);
        System.out.println("Após atualizar massa de Ana:");
        dao.listarTodos().forEach(System.out::println);

        // Busca por id
        Pessoa buscada = dao.buscarPorId(p2.getId());
        System.out.println("Busca por id de Bruno: " + buscada);

        // Deleta p2
        dao.deletar(p2.getId());
        System.out.println("Após deletar Bruno:");
        dao.listarTodos().forEach(System.out::println);
    }
}

