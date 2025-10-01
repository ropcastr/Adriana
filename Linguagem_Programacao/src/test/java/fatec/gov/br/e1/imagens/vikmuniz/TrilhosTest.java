package fatec.gov.br.e1.imagens.vikmuniz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TrilhosTest {

    @Test
    public void testConstructorAndGetters() {
        Trilhos trilhos = new Trilhos(100.0f, "Aço", 1.435f);
        assertEquals(100.0f, trilhos.getComprimento(), 0.01f);
        assertEquals("Aço", trilhos.getMaterial());
        assertEquals(1.435f, trilhos.getBitola(), 0.01f);
    }

    @Test
    public void testSetters() {
        Trilhos trilhos = new Trilhos(100.0f, "Aço", 1.435f);
        trilhos.setComprimento(150.0f);
        trilhos.setMaterial("Ferro");
        trilhos.setBitola(1.668f);
        assertEquals(150.0f, trilhos.getComprimento(), 0.01f);
        assertEquals("Ferro", trilhos.getMaterial());
        assertEquals(1.668f, trilhos.getBitola(), 0.01f);
    }

    @Test
    public void testSuportarTrem() {
        Trilhos trilhos = new Trilhos(100.0f, "Aço", 1.435f);
        trilhos.suportarTrem();
    }
}
