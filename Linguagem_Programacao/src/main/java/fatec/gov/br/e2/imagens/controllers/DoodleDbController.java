package fatec.gov.br.e2.imagens.controllers;

import fatec.gov.br.e2.imagens.doodle.AlienDb;
import fatec.gov.br.e2.imagens.doodle.RobotDb;
import fatec.gov.br.e2.imagens.doodle.AlienRobotDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DoodleDbController {
    @FXML private TextField txt_robot_nivel_db, txt_robot_vida_db, txt_robot_pontos_db;
    @FXML private TextField txt_alien_tipo_db, txt_alien_forca_db, txt_alien_vida_db;
    @FXML private Button btn_robot_db, btn_alien_db;
    @FXML private TableView<RobotDb> table_view_robot_db; @FXML private TableColumn<RobotDb,Integer> colRobotId; @FXML private TableColumn<RobotDb,Integer> colRobotNivel; @FXML private TableColumn<RobotDb,Integer> colRobotVida; @FXML private TableColumn<RobotDb,Integer> colRobotPontos;
    @FXML private TableView<AlienDb> table_view_alien_db; @FXML private TableColumn<AlienDb,Integer> colAlienId; @FXML private TableColumn<AlienDb,String> colAlienTipo; @FXML private TableColumn<AlienDb,Integer> colAlienForca; @FXML private TableColumn<AlienDb,Integer> colAlienVida;
    @FXML private TextArea txt_area_robot_db; @FXML private TextArea txt_area_alien_db;
    @FXML private Button btn_robot_recarregar_db; @FXML private Button btn_robot_limpar_db; @FXML private Button btn_alien_recarregar_db; @FXML private Button btn_alien_limpar_db;

    private final AlienRobotDAO dao = new AlienRobotDAO();
    private final ObservableList<RobotDb> robots = FXCollections.observableArrayList();
    private final ObservableList<AlienDb> aliens = FXCollections.observableArrayList();

    @FXML public void initialize(){
        colRobotId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRobotNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        colRobotVida.setCellValueFactory(new PropertyValueFactory<>("vida"));
        colRobotPontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));
        colAlienId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAlienTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colAlienForca.setCellValueFactory(new PropertyValueFactory<>("forca"));
        colAlienVida.setCellValueFactory(new PropertyValueFactory<>("vida"));
        table_view_robot_db.setItems(robots); table_view_alien_db.setItems(aliens);
        recarregar();
        btn_robot_db.setOnAction(e -> salvarRobot());
        btn_alien_db.setOnAction(e -> salvarAlien());
        if (btn_robot_recarregar_db != null) btn_robot_recarregar_db.setOnAction(e -> recarregar());
        if (btn_alien_recarregar_db != null) btn_alien_recarregar_db.setOnAction(e -> recarregar());
        if (btn_robot_limpar_db != null) btn_robot_limpar_db.setOnAction(e -> limparRobot());
        if (btn_alien_limpar_db != null) btn_alien_limpar_db.setOnAction(e -> limparAlien());
    }

    private void salvarRobot(){
        int nivel=parseInt(txt_robot_nivel_db.getText(),1);
        int vida=parseInt(txt_robot_vida_db.getText(),100);
        int pontos=parseInt(txt_robot_pontos_db.getText(),0);
        RobotDb r=dao.salvarRobot(new RobotDb(nivel,vida,pontos));
        robots.add(0,r);
        txt_area_robot_db.setText("Robô salvo: " + r.toString());
        limparRobot();
    }
    private void salvarAlien(){
        String tipo= txt_alien_tipo_db.getText().isBlank()?"Desconhecido":txt_alien_tipo_db.getText();
        int forca=parseInt(txt_alien_forca_db.getText(),10);
        int vida=parseInt(txt_alien_vida_db.getText(),100);
        AlienDb a=dao.salvarAlien(new AlienDb(tipo,forca,vida));
        aliens.add(0,a);
        txt_area_alien_db.setText("Alien salvo: " + a.toString());
        limparAlien();
    }
    private void recarregar(){
        robots.setAll(dao.listarRobots());
        aliens.setAll(dao.listarAliens());
    }
    private int parseInt(String v,int def){
        try{return Integer.parseInt(v);}catch(Exception e){return def;}
    }
    private void limparRobot(){
        txt_robot_nivel_db.clear();
        txt_robot_vida_db.clear();
        txt_robot_pontos_db.clear();
    }
    private void limparAlien(){
        txt_alien_tipo_db.clear();
        txt_alien_forca_db.clear();
        txt_alien_vida_db.clear();
    }
}
