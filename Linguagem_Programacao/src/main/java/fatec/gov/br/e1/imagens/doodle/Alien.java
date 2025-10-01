package fatec.gov.br.e1.imagens.doodle;

public class Alien {
    private String type;
    private int strength;
    private int health;

    public Alien(String type, int strength, int health) {
        this.type = type;
        this.strength = strength;
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void invade(GamerRobot gamerRobot) {
        if (gamerRobot != null && this.strength > 0 && this.health > 0) {
            int damage = this.strength / 5;
            gamerRobot.setHealth(gamerRobot.getHealth() - damage);
            if (gamerRobot.getHealth() < 0) gamerRobot.setHealth(0);
            System.out.println(this.type + " invadiu e causou " + damage +
                              " de dano.\n Saúde do jogador: " + gamerRobot.getHealth() +
                              "\n Saúde do alienígena: " + this.health);
        }
    }

    public void defend(GamerRobot gamerRobot) {
        if (gamerRobot != null && this.health > 0) {
            int defenseBoost = this.strength / 10;
            this.health += defenseBoost;
            if (gamerRobot.getHealth() > 0) {
                int counterDamage = defenseBoost / 2;
                gamerRobot.setHealth(gamerRobot.getHealth() - counterDamage);
                if (gamerRobot.getHealth() < 0) gamerRobot.setHealth(0);
                System.out.println(this.type + " se defendeu, ganhando " + defenseBoost +
                                  " de saúde e causando " + counterDamage + " de dano.\n Saúde do jogador: " +
                                  gamerRobot.getHealth() + "\n Saúde do alienígena: " + this.health);
            }
        }
    }

    public void abduct(GamerRobot gamerRobot) {
        if (gamerRobot != null && this.strength >= gamerRobot.getHealth() && this.health > 0) {
            gamerRobot.setHealth(0);
            gamerRobot.setScore(gamerRobot.getScore() - 10);
            if (gamerRobot.getScore() < 0) gamerRobot.setScore(0);
            this.health -= 10;
            System.out.println(this.type + " abduziu o jogador!\n Saúde do jogador: " + gamerRobot.getHealth() +
                              ", Pontos perdidos: " + 10 + "\n Saúde do alienígena: " + this.health);
        } else if (gamerRobot != null && this.health > 0) {
            int resistanceDamage = this.strength / 10;
            gamerRobot.setHealth(gamerRobot.getHealth() - resistanceDamage);
            if (gamerRobot.getHealth() < 0) gamerRobot.setHealth(0);
            System.out.println(this.type + " tentou abduzir, mas falhou, causando " + resistanceDamage +
                              " de dano.\n Saúde do jogador: " + gamerRobot.getHealth() +
                              "\n Saúde do alienígena: " + this.health);
        }
    }
}