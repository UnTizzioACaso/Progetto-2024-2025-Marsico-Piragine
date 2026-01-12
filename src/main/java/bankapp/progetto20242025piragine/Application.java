package bankapp.progetto20242025piragine;

import bankapp.progetto20242025piragine.db.DBInitializer;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Inizializza il database
        DBInitializer.initialize();

        // Carica il FXML principale
        FXMLLoader fxmlLoader = new FXMLLoader(
                Application.class.getResource("fxml/rootWindow.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        // Applica il tema globale (chiaro/scuro) all'apertura
        ThemeManager.applyTheme(scene, "light");

        // Imposta le proprietà della finestra principale
        stage.setTitle("PSP bankkkkk!");
        stage.setMinWidth(850);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
