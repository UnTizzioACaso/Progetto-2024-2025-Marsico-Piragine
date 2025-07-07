package bankapp.progetto20242025marsicopiragine;

import bankapp.progetto20242025marsicopiragine.db.DBInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    //running the application
    @Override
    public void start(Stage stage) throws IOException {
        DBInitializer.initialize();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/page/friendsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}