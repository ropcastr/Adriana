package fatec.gov.br.e2.imagens.vikmuniz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VikMunizDbApp extends Application {
    @Override public void start(Stage stage) throws Exception { ImagensDatabase.get(); FXMLLoader loader = new FXMLLoader(getClass().getResource("/ei/ui/imagens/VikMunizDb.fxml")); Scene scene = new Scene(loader.load(),800,600); stage.setTitle("VikMuniz DB"); stage.setScene(scene); stage.show(); }
    public static void main(String[] args){ launch(); }
}

