package fatec.gov.br.e2.calculomedia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculoMediaDbApp extends Application {
    @Override public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ei/ui/calculomedia/CalculoMediaDb.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);
        var css = getClass().getResource("/ei/css/Stylesheet.css");
        if (css != null) scene.getStylesheets().add(css.toExternalForm());
        stage.setTitle("Cálculo de Média (SQLite)");
        stage.setScene(scene); stage.show();
    }
    public static void main(String[] args){ launch(); }
}

