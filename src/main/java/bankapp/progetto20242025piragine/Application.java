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
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/rootWindow.fxml")); //getting the main window's fxml
        Scene scene = new Scene(fxmlLoader.load()); //crating the scene with the fxml
        stage.setTitle("PSP bankkkkk!"); //setting the window's title
        stage.setMinWidth(650); //setting the window's min width
        stage.setMinHeight(650); //setting the window's min lenght
        stage.setScene(scene); //setting the scene in the window
        stage.show(); //showing the window
    }


    public static void main(String[] args) {
        launch();
    }
}