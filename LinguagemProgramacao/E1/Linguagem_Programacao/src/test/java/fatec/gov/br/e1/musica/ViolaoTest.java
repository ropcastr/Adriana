package fatec.gov.br.e1.musica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ViolaoTest {
    private Violao violao;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        violao = new Violao(6, "mogno", "quente");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testProduzirSomParaConquistar() {
        violao.produzirSomParaConquistar();
        String expectedOutput = "O violão está produzindo som para conquistar meninas." + System.lineSeparator();
        assertEquals(expectedOutput.replaceAll("\r\n", "\n"), outContent.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void testGetSetAtributos() {
        violao.setCordas(12);
        violao.setMadeira("cedro");
        violao.setTimbre("claro");
        assertEquals(12, violao.getCordas());
        assertEquals("cedro", violao.getMadeira());
        assertEquals("claro", violao.getTimbre());
    }
}