package fatec.gov.br.e1.imagens.doodle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GamerRobotTest {
    private GamerRobot gamerRobot;
    private Alien alien;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        gamerRobot = new GamerRobot(1, 100, 0);
        alien = new Alien("Martian", 50, 100);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
        System.out.println("Mensagens capturadas: \n" + outContent.toString());
        outContent.reset();
    }

    @Test
    public void testConfrontationSequence() {
        gamerRobot.playGame();
        assertEquals(1, gamerRobot.getLevel());
        assertEquals(100, gamerRobot.getHealth());
        assertEquals(0, gamerRobot.getScore());
        String playOutput = outContent.toString();
        String expectedPlayMessage = "O jogador iniciou o jogo viajando pelo espaço no nível " + gamerRobot.getLevel() +
                                    ", com " + gamerRobot.getHealth() + " de vida e " + gamerRobot.getScore() + " pontos.";
        assertTrue(playOutput.contains(expectedPlayMessage));
        assertTrue(playOutput.contains("."));
        assertTrue(playOutput.contains("O jogador encontrou um alienígena"));

        alien.invade(gamerRobot);
        assertEquals(90, gamerRobot.getHealth());
        String invadeOutput = outContent.toString();
        assertTrue(invadeOutput.contains("Martian invadiu e causou 10 de dano"));

        // Ataques até derrotar o alien
        for (int i = 0; i < 10; i++) {
            gamerRobot.attackAlien(alien);
            if (alien.getHealth() <= 0) {
                break;
            }
        }

        assertEquals(1, gamerRobot.getLevel());
        assertEquals(40, gamerRobot.getHealth());
        assertEquals(20, gamerRobot.getScore());
        assertEquals(0, alien.getHealth());
        String attackOutput = outContent.toString();
        assertTrue(attackOutput.contains("O jogador derrotou o alienígena e está com " + gamerRobot.getHealth() + " pontos de vida restantes."));
        assertTrue(attackOutput.contains("O jogador ganhou 20 pontos e agora tem " + gamerRobot.getScore() + " pontos"));

        gamerRobot.upgradeWeapons();
        assertEquals(2, gamerRobot.getLevel());
        assertEquals(100, gamerRobot.getHealth());
        assertEquals(10, gamerRobot.getScore());
        String upgradeOutput = outContent.toString();
        assertTrue(upgradeOutput.contains("O jogador usou " + GamerRobot.UPGRADE_COST + " pontos para upgrade de armas e agora tem " + gamerRobot.getScore() + " pontos restantes."));
        assertTrue(upgradeOutput.contains("O jogador avançou para o nível " + gamerRobot.getLevel() + " e está com " + gamerRobot.getHealth() + " de vida."));
    }
}