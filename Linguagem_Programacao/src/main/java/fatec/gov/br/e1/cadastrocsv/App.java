package fatec.gov.br.e1.cadastrocsv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Carrega a cena principal (usar caminho absoluto de recurso)
        scene = new Scene(loadFXML("/ei/ui/cadastrocsv/pessoa"), 740, 493);
        // Aplica stylesheet global
        var css = App.class.getResource("/ei/css/Stylesheet.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }
        stage.setScene(scene);
        stage.setTitle("Cadastro de Pessoa");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        String normalized = fxml.startsWith("/") ? fxml : "/" + fxml;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(normalized + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
