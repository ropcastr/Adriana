package fatec.gov.br.e1.imagens.doodle;

public class Spaceship {
    private String type;
    private int weapons;
    private int health;
    private Alien alien;
    private GamerRobot gamerRobot;

    public Spaceship(String type, int weapons, Alien alien, int health, GamerRobot gamerRobot) {
        this.type = type;
        this.weapons = weapons;
        this.alien = alien;
        this.health = health;
        this.gamerRobot = gamerRobot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeapons() {
        return weapons;
    }

    public void setWeapons(int weapons) {
        this.weapons = weapons;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
    }

    public GamerRobot getGamerRobot() {
        return gamerRobot;
    }

    public void setGamerRobot(GamerRobot gamerRobot) {
        this.gamerRobot = gamerRobot;
    }

    public void fly() {
        // Logic to fly the spaceship
    }

    public void shoot() {
        // Logic to shoot weapons
    }

    public void land() {
        // Logic to land the spaceship
    }
}