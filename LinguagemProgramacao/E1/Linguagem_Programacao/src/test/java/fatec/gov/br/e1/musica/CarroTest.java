package fatec.gov.br.e1.musica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class CarroTest {
    private Carro opala;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        opala = new Carro("Opala", "metálico azul", "potente");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testRoncarEmPega() {
        opala.roncarEmPega();
        String expectedOutput = "O Opala está roncarando em um pega na Asa Sul." + System.lineSeparator();
        assertEquals(expectedOutput.replaceAll("\r\n", "\n"), outContent.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void testGetSetAtributos() {
        opala.setModelo("Chevette");
        opala.setCor("vermelho");
        opala.setMotor("fraco");
        assertEquals("Chevette", opala.getModelo());
        assertEquals("vermelho", opala.getCor());
        assertEquals("fraco", opala.getMotor());
    }
}
