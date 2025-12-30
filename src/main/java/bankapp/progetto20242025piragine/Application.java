package bankapp.progetto20242025piragine;

import bankapp.progetto20242025piragine.db.DBInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {

        DBInitializer.initialize();

        FXMLLoader fxmlLoader = new FXMLLoader(
                Application.class.getResource("fxml/rootWindow.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        // 🎨 CSS TEMA CHIARO
        scene.getStylesheets().add(
                Application.class
                        .getResource("css/light.css")
                        .toExternalForm()
        );

        stage.setTitle("PSP bankkkkk!");
        stage.setMinWidth(650);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
