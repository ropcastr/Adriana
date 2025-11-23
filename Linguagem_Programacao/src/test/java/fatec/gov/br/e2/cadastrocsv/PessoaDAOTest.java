package fatec.gov.br.e2.cadastrocsv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaDAOTest {
    private PessoaDAO dao;
    private static final String TEST_DB_URL = "jdbc:sqlite:target/test-pessoas.db";

    @BeforeEach
    void setup() {
        // remove arquivo antigo de teste
        File f = new File("target/test-pessoas.db");
        if (f.exists()) {
            f.delete();
        }
        dao = new PessoaDAO(TEST_DB_URL);
        dao.deletarTodos();
    }

    @Test
    void testSalvarEListar() {
        dao.salvar(new Pessoa("Carlos", "M", 1.75, 72));
        dao.salvar(new Pessoa("Diana", "F", 1.65, 58));
        List<Pessoa> pessoas = dao.listarTodos();
        assertEquals(2, pessoas.size());
        assertTrue(pessoas.stream().anyMatch(p -> p.getNome().equals("Carlos")));
        assertTrue(pessoas.stream().anyMatch(p -> p.getNome().equals("Diana")));
    }

    @Test
    void testBuscarPorId() {
        Pessoa p = dao.salvar(new Pessoa("Eva", "F", 1.60, 55));
        Pessoa buscada = dao.buscarPorId(p.getId());
        assertNotNull(buscada);
        assertEquals("Eva", buscada.getNome());
    }

    @Test
    void testAtualizar() {
        Pessoa p = dao.salvar(new Pessoa("Fernando", "M", 1.82, 85));
        p.setMassa(86);
        assertTrue(dao.atualizar(p));
        Pessoa atualizado = dao.buscarPorId(p.getId());
        assertEquals(86, atualizado.getMassa());
    }

    @Test
    void testDeletar() {
        Pessoa p = dao.salvar(new Pessoa("Guilherme", "M", 1.77, 78));
        assertTrue(dao.deletar(p.getId()));
        assertNull(dao.buscarPorId(p.getId()));
    }

    @Test
    void testDeletarTodos() {
        dao.salvar(new Pessoa("Helena", "F", 1.68, 62));
        dao.salvar(new Pessoa("Igor", "M", 1.73, 70));
        int deletados = dao.deletarTodos();
        assertTrue(deletados >= 2); // pelo menos os inseridos
        assertEquals(0, dao.listarTodos().size());
    }
}

