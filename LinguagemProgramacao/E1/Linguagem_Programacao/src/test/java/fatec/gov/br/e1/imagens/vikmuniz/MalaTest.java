package fatec.gov.br.e1.imagens.vikmuniz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MalaTest {

    @Test
    public void testConstructorAndGetters() {
        Mala mala = new Mala("Médio", "Couro", 5.0f);
        assertEquals("Médio", mala.getTamanho());
        assertEquals("Couro", mala.getMaterial());
        assertEquals(5.0f, mala.getPeso(), 0.01f);
    }

    @Test
    public void testSetters() {
        Mala mala = new Mala("Médio", "Couro", 5.0f);
        mala.setTamanho("Pequeno");
        mala.setMaterial("Tecido");
        mala.setPeso(3.0f);
        assertEquals("Pequeno", mala.getTamanho());
        assertEquals("Tecido", mala.getMaterial());
        assertEquals(3.0f, mala.getPeso(), 0.01f);
    }

    @Test
    public void testAbrir() {
        Mala mala = new Mala("Médio", "Couro", 5.0f);
        Vencedor vencedor = new Vencedor("João", 100, "00:05");
        mala.abrir(vencedor);
    }
}
