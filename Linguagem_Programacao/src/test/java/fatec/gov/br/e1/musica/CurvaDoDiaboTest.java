package fatec.gov.br.e1.musica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class CurvaDoDiaboTest {
    private CurvaDoDiabo curva;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        curva = new CurvaDoDiabo("Sobradinho", "muito perigosa", "Curva do Diabo");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testProvocarAcidente() {
        curva.provocarAcidente();
        String expectedOutput = "A Curva do Diabo provoca um acidente com explosão na estrada." + System.lineSeparator();
        assertEquals(expectedOutput.replaceAll("\r\n", "\n"), outContent.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void testGetSetAtributos() {
        curva.setLocalizacao("Brasília");
        curva.setPerigosidade("perigosa");
        curva.setNome("Curva Perigosa");
        assertEquals("Brasília", curva.getLocalizacao());
        assertEquals("perigosa", curva.getPerigosidade());
        assertEquals("Curva Perigosa", curva.getNome());
    }
}
