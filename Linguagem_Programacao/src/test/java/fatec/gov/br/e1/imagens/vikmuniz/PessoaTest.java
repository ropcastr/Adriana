package fatec.gov.br.e1.imagens.vikmuniz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PessoaTest {

    @Test
    public void testConstructorAndGetters() {
        Pessoa pessoa = new Pessoa("João", 50, "Trilhos");
        assertEquals("João", pessoa.getNome());
        assertEquals(50, pessoa.getForca());
        assertEquals("Trilhos", pessoa.getPosicao());
    }

    @Test
    public void testSetters() {
        Pessoa pessoa = new Pessoa("João", 50, "Trilhos");
        pessoa.setNome("Maria");
        pessoa.setForca(60);
        pessoa.setPosicao("Plataforma");
        assertEquals("Maria", pessoa.getNome());
        assertEquals(60, pessoa.getForca());
        assertEquals("Plataforma", pessoa.getPosicao());
    }

    @Test
    public void testAtacar() {
        Pessoa pessoa = new Pessoa("João", 50, "Trilhos");
        pessoa.atacar();
    }
}
