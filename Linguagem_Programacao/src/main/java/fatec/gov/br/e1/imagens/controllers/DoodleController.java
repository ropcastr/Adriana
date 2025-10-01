package fatec.gov.br.e1.imagens.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import fatec.gov.br.e1.imagens.doodle.Alien;
import fatec.gov.br.e1.imagens.doodle.GamerRobot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class DoodleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_alien;

    @FXML
    private Button btn_alien_csv;

    @FXML
    private Button btn_robot;

    @FXML
    private Button btn_robot_csv;

    @FXML
    private HBox hbox_doodle;

    @FXML
    private AnchorPane pane_hbox_alien;

    @FXML
    private AnchorPane pane_hbox_robot;

    @FXML
    private AnchorPane pane_hbox_tables;

    @FXML
    private TableView<Alien> table_view_alien;

    @FXML
    private TableView<GamerRobot> table_view_robot;

    @FXML
    private TextField txt_alien_health;

    @FXML
    private TextField txt_alien_strength;

    @FXML
    private TextField txt_alien_type;

    @FXML
    private TextArea txt_area_alien;

    @FXML
    private TextArea txt_area_robot;

    @FXML
    private TextField txt_robot_health;

    @FXML
    private TextField txt_robot_level;

    @FXML
    private TextField txt_robot_score;

    @FXML private TableColumn<GamerRobot, Integer> colRobotLevel;
    @FXML private TableColumn<GamerRobot, Integer> colRobotHealth;
    @FXML private TableColumn<GamerRobot, Integer> colRobotScore;
    @FXML private TableColumn<Alien, String> colAlienType;
    @FXML private TableColumn<Alien, Integer> colAlienStrength;
    @FXML private TableColumn<Alien, Integer> colAlienHealth;

    private final ObservableList<Alien> alienList = FXCollections.observableArrayList();
    private final ObservableList<GamerRobot> robotList = FXCollections.observableArrayList();
    private static final String DATA_DIR = "src/main/java/fatec/gov/br/e1/imagens/doodle";
    private static final String ALIEN_CSV = DATA_DIR + "/aliens.csv";
    private static final String ROBOT_CSV = DATA_DIR + "/robots.csv";

    @FXML
    void initialize() {
        assert btn_alien != null : "fx:id=\"btn_alien\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert btn_alien_csv != null : "fx:id=\"btn_alien_csv\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert btn_robot != null : "fx:id=\"btn_robot\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert btn_robot_csv != null : "fx:id=\"btn_robot_csv\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert hbox_doodle != null : "fx:id=\"hbox_doodle\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert pane_hbox_alien != null : "fx:id=\"pane_hbox_alien\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert pane_hbox_robot != null : "fx:id=\"pane_hbox_robot\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert pane_hbox_tables != null : "fx:id=\"pane_hbox_tables\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert table_view_alien != null : "fx:id=\"table_view_alien\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert table_view_robot != null : "fx:id=\"table_view_robot\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_alien_health != null : "fx:id=\"txt_alien_health\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_alien_strength != null : "fx:id=\"txt_alien_strength\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_alien_type != null : "fx:id=\"txt_alien_type\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_area_alien != null : "fx:id=\"txt_area_alien\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_area_robot != null : "fx:id=\"txt_area_robot\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_robot_health != null : "fx:id=\"txt_robot_health\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_robot_level != null : "fx:id=\"txt_robot_level\" was not injected: check your FXML file 'Doodle.fxml'.";
        assert txt_robot_score != null : "fx:id=\"txt_robot_score\" was not injected: check your FXML file 'Doodle.fxml'.";

        colRobotLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        colRobotHealth.setCellValueFactory(new PropertyValueFactory<>("health"));
        colRobotScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        colAlienType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAlienStrength.setCellValueFactory(new PropertyValueFactory<>("strength"));
        colAlienHealth.setCellValueFactory(new PropertyValueFactory<>("health"));

        ensureDataDir();
        loadAliensFromCSV();
        loadRobotsFromCSV();
        table_view_alien.setItems(alienList);
        table_view_robot.setItems(robotList);
        btn_alien.setOnAction(e -> handleAlienSubmit());
        btn_robot.setOnAction(e -> handleRobotSubmit());
        btn_alien_csv.setOnAction(e -> handleAlienCSV());
        btn_robot_csv.setOnAction(e -> handleRobotCSV());
    }

    private void ensureDataDir() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private void handleAlienSubmit() {
        String type = txt_alien_type.getText().isEmpty() ? "Desconhecido" : txt_alien_type.getText();
        int strength = parseOrDefault(txt_alien_strength.getText(), 10);
        int health = parseOrDefault(txt_alien_health.getText(), 100);
        Alien alien = new Alien(type, strength, health);
        GamerRobot robot = getCurrentRobot();
        StringBuilder sb = new StringBuilder();
        alien.invade(robot);
        sb.append("Alien invade:\n");
        sb.append("Tipo: ").append(type).append(", Força: ").append(strength).append(", Vida: ").append(health).append("\n");
        sb.append("Robot: Nível: ").append(robot.getLevel()).append(", Vida: ").append(robot.getHealth()).append(", Pontos: ").append(robot.getScore()).append("\n");
        txt_area_alien.setText(sb.toString());
    }

    private void handleRobotSubmit() {
        int level = parseOrDefault(txt_robot_level.getText(), 1);
        int health = parseOrDefault(txt_robot_health.getText(), 100);
        int score = parseOrDefault(txt_robot_score.getText(), 0);
        GamerRobot robot = new GamerRobot(level, health, score);
        Alien alien = getCurrentAlien();
        StringBuilder sb = new StringBuilder();
        robot.attackAlien(alien);
        sb.append("Robot ataca:\n");
        sb.append("Nível: ").append(level).append(", Vida: ").append(health).append(", Pontos: ").append(score).append("\n");
        sb.append("Alien: Tipo: ").append(alien.getType()).append(", Força: ").append(alien.getStrength()).append(", Vida: ").append(alien.getHealth()).append("\n");
        txt_area_robot.setText(sb.toString());
    }

    private void handleAlienCSV() {
        String type = txt_alien_type.getText().isEmpty() ? "Desconhecido" : txt_alien_type.getText();
        int strength = parseOrDefault(txt_alien_strength.getText(), 10);
        int health = parseOrDefault(txt_alien_health.getText(), 100);
        Alien alien = new Alien(type, strength, health);
        alienList.add(alien);
        saveAliensToCSV();
        loadAliensFromCSV();
    }

    private void handleRobotCSV() {
        int level = parseOrDefault(txt_robot_level.getText(), 1);
        int health = parseOrDefault(txt_robot_health.getText(), 100);
        int score = parseOrDefault(txt_robot_score.getText(), 0);
        GamerRobot robot = new GamerRobot(level, health, score);
        robotList.add(robot);
        saveRobotsToCSV();
        loadRobotsFromCSV();
    }

    private int parseOrDefault(String value, int def) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return def;
        }
    }

    private void saveAliensToCSV() {
        System.out.println("[DEBUG] Salvando aliens em: " + new File(ALIEN_CSV).getAbsolutePath());
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ALIEN_CSV), StandardCharsets.UTF_8))) {
            for (Alien a : alienList) {
                writer.write(a.getType() + "," + a.getStrength() + "," + a.getHealth());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRobotsToCSV() {
        System.out.println("[DEBUG] Salvando robots em: " + new File(ROBOT_CSV).getAbsolutePath());
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ROBOT_CSV), StandardCharsets.UTF_8))) {
            for (GamerRobot r : robotList) {
                writer.write(r.getLevel() + "," + r.getHealth() + "," + r.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAliensFromCSV() {
        alienList.clear();
        File file = new File(ALIEN_CSV);
        System.out.println("[DEBUG] Lendo aliens de: " + file.getAbsolutePath());
        if (!file.exists()) {
            System.out.println("[DEBUG] Arquivo de aliens não existe.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[DEBUG] Linha lida (alien): " + line);
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0];
                    int strength = parseOrDefault(parts[1], 10);
                    int health = parseOrDefault(parts[2], 100);
                    alienList.add(new Alien(type, strength, health));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        table_view_alien.setItems(alienList);
    }

    private void loadRobotsFromCSV() {
        robotList.clear();
        File file = new File(ROBOT_CSV);
        System.out.println("[DEBUG] Lendo robots de: " + file.getAbsolutePath());
        if (!file.exists()) {
            System.out.println("[DEBUG] Arquivo de robots não existe.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[DEBUG] Linha lida (robot): " + line);
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int level = parseOrDefault(parts[0], 1);
                    int health = parseOrDefault(parts[1], 100);
                    int score = parseOrDefault(parts[2], 0);
                    robotList.add(new GamerRobot(level, health, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        table_view_robot.setItems(robotList);
    }

    private Alien getCurrentAlien() {
        String type = txt_alien_type.getText().isEmpty() ? "Desconhecido" : txt_alien_type.getText();
        int strength = parseOrDefault(txt_alien_strength.getText(), 10);
        int health = parseOrDefault(txt_alien_health.getText(), 100);
        return new Alien(type, strength, health);
    }

    private GamerRobot getCurrentRobot() {
        int level = parseOrDefault(txt_robot_level.getText(), 1);
        int health = parseOrDefault(txt_robot_health.getText(), 100);
        int score = parseOrDefault(txt_robot_score.getText(), 0);
        return new GamerRobot(level, health, score);
    }
}
