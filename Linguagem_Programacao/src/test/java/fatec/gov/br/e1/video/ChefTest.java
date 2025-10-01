package fatec.gov.br.e1.video;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChefTest {
    private Chef chef;
    private Ingrediente carne;
    private Utensilio frigideira;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        chef = new Chef("Noel", 5, "Culinária Asiática");
        carne = new Ingrediente("Carne", 500, "gramas");
        frigideira = new Utensilio("Frigideira", "Aço", 30);
        chef.adicionarUtensilio(frigideira);
        chef.adicionarIngrediente(carne);
        frigideira.adicionarIngrediente(carne); // Bidirecional
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.out.println("Mensagens capturadas:\n" + outContent.toString());
        outContent.reset();
    }

    @Test
    public void test01PrepararIngrediente() {
        chef.prepararIngrediente();
        String output = outContent.toString();
        assertTrue(output.contains("Carne (500 gramas) foi cortado em tiras."));
        assertTrue(output.contains("Carne foi temperada com bicarbonato de sódio e água para velveting."));
        assertTrue(output.contains("Deixando o Carne de molho por 10 minutos."));
        assertTrue(output.contains("Carne (500 gramas) foi lavado após o molho."));
        assertTrue(output.contains("Chef Noel preparou o ingrediente Carne para velveting."));
    }

    @Test
    public void test02Cozinhar() {
        chef.cozinhar();
        String output = outContent.toString();
        assertTrue(output.contains("Frigideira de Aço está sendo preparada com óleo e especiarias."));
        assertTrue(output.contains("Frigideira está aquecida a uma temperatura adequada."));
        assertTrue(output.contains("Adicionando Carne à frigideira."));
        assertTrue(output.contains("Frigideira está sendo usada para mexer Carne regularmente."));
        assertTrue(output.contains("Chef Noel está cozinhando na Frigideira"));
    }

    @Test
    public void test03ProvarComida() {
        chef.provarComida();
        String output = outContent.toString();
        assertTrue(output.contains("Chef Noel está provando a comida: tenderiza e retém a umidade, delicioso!"));
    }
}