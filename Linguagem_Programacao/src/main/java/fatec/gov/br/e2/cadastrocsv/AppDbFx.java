package fatec.gov.br.e2.cadastrocsv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppDbFx extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/ei/ui/cadastrocsv/pessoa_db"), 740, 493);
        var css = AppDbFx.class.getResource("/ei/css/Stylesheet.css");
        if (css != null) scene.getStylesheets().add(css.toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Cadastro Pessoa (SQLite)");
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        String normalized = fxml.startsWith("/") ? fxml : "/" + fxml;
        FXMLLoader fxmlLoader = new FXMLLoader(AppDbFx.class.getResource(normalized + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

