package fatec.gov.br.e1.imagens.vikmuniz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FotografoTest {
    private Fotografo fotografo;
    private Camera camera;
    private Microfone microfone;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        fotografo = new Fotografo("Paparazzi", "Canon EOS", "20 anos");
        camera = new Camera("Canon EOS", "50mm", 24);
        microfone = new Microfone("Shure", 2.5f, 50);
        fotografo.setTipoCamera(camera.getModelo()); // Simula uso da câmera
        // Relacionamentos implícitos via teste
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.out.println("Mensagens capturadas:\n" + outContent.toString());
        outContent.reset();
    }

    @Test
    public void test01CapturarImagem() {
        camera.capturarImagem();
        String output = outContent.toString();
        assertTrue(output.contains("A câmera Canon EOS está capturando uma imagem exclusiva com resolução de 24 MP."));
    }

    @Test
    public void test02CapturarAudio() {
        microfone.capturarAudio();
        String output = outContent.toString();
        assertTrue(output.contains("O microfone Shure está capturando o áudio da entrevista exclusiva com sensibilidade de 50 MP."));
    }

    @Test
    public void test03TirarFoto() {
        fotografo.tirarFoto();
        String output = outContent.toString();
        assertTrue(output.contains("Paparazzi está tirando uma foto exclusiva com sua Canon EOS."));
    }
}