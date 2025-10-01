package fatec.gov.br.e1.musica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {
    private Pessoa joaoRoberto;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        joaoRoberto = new Pessoa("João Roberto", 16, "legal");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Restaura o System.out original
    }

    @Test
    void testTocar() {
        joaoRoberto.tocar();
        String expectedOutput = "João Roberto está tocando violão para conquistar meninas." + System.lineSeparator();
        assertEquals(expectedOutput.replaceAll("\r\n", "\n"), outContent.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void testDirigirCarro() {
        joaoRoberto.dirigirCarro();
        String expectedOutput = "João Roberto está dirigindo o carro em um pega." + System.lineSeparator();
        assertEquals(expectedOutput.replaceAll("\r\n", "\n"), outContent.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void testGetSetAtributos() {
        joaoRoberto.setNome("Johnny");
        joaoRoberto.setIdade(17);
        joaoRoberto.setReputacao("fera");
        assertEquals("Johnny", joaoRoberto.getNome());
        assertEquals(17, joaoRoberto.getIdade());
        assertEquals("fera", joaoRoberto.getReputacao());
    }
}