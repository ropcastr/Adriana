package fatec.gov.br.e1.imagens.doodle;

public class GamerRobot {
    public static final int UPGRADE_COST = 10;
    private int level;
    private int health;
    private int score;

    public GamerRobot(int level, int health, int score) {
        this.level = level;
        this.health = health;
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void playGame() {
        System.out.println("O jogador iniciou o jogo viajando pelo espaço no nível " + this.level + ", com " + this.health + " de vida e " + this.score + " pontos.");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("O jogador encontrou um alienígena.\n");
    }

    public void attackAlien(Alien alien) {
        if (alien != null && alien.getHealth() > 0) {
            this.health -= 5;
            if (this.health < 0) this.health = 0;
            int damageToAlien = this.level * 10;
            alien.setHealth(alien.getHealth() - damageToAlien);
            if (alien.getHealth() <= 0) {
                alien.setHealth(0);
                alien.setStrength(0);
                this.score += 20;
                System.out.println("O jogador derrotou o alienígena e está com " + this.health + " pontos de vida restantes.");
                System.out.println("O jogador ganhou " + 20 + " pontos e agora tem " + this.score + " pontos.");
            } else {
                System.out.println("O jogador atacou, causando " + damageToAlien + " de dano.\n Saúde do alienígena: " + alien.getHealth() +
                                  "\n Saúde do jogador: " + this.health);
            }
        }
    }

    public void upgradeWeapons() {
        if (this.score >= UPGRADE_COST) {
            this.score -= UPGRADE_COST;
            this.level += 1;
            this.health = 100; //Seta novamente a vida a 100% ao passar de nível
            System.out.println("O jogador usou " + UPGRADE_COST + " pontos para upgrade de armas e agora tem " + this.score + " pontos restantes.");
            System.out.println("O jogador avançou para o nível " + this.level + " e está com " + this.health + " de vida.");
        } else {
            System.out.println("Pontos insuficientes para upgrade de armas. Pontos atuais: " + this.score);
        }
    }
}