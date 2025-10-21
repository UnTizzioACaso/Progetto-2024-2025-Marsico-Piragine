package bankapp.progetto20242025piragine;

import bankapp.progetto20242025piragine.db.DBInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    //running the application
    @Override
    public void start(Stage stage) throws IOException
    {
        DBInitializer.initialize(); //initialization
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/rootWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PSP bankkkkk!");
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}