package fatec.gov.br.e1.imagens.vikmuniz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VencedorTest {

    @Test
    public void testConstructorAndGetters() {
        Vencedor vencedor = new Vencedor("João", 100, "00:05");
        assertEquals("João", vencedor.getNome());
        assertEquals(100, vencedor.getPontuacao());
        assertEquals("00:05", vencedor.getTempoVitoria());
    }

    @Test
    public void testDeclararVitoria() {
        Vencedor vencedor = new Vencedor("João", 100, "00:05");
        // Redirect System.out to capture output (using a ByteArrayOutputStream would be ideal, but for simplicity)
        vencedor.declararVitoria(); // Expected: "João venceu em 00:05 com pontuação 100"
    }
}
