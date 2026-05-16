package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopupCreator
{
    public static BranchController showPopup(String title, String fxml, int width, int height)
    {
        try {
            FXMLLoader loader = new FXMLLoader(PopupCreator.class.getResource(fxml)); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            BranchController controller = loader.getController(); //getting the controller from the loader
            Stage popupStage = new Stage(); //creating a new stage for the popup
            popupStage.setTitle(title); //setting the title
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.setMinWidth(width); //setting popup's minimum width
            popupStage.setMaxWidth(width);
            popupStage.setMinHeight(height); //setting popup's minimum height
            popupStage.setMaxHeight(height);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
            Scene scene =new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            popupStage.setScene(scene);
            controller.initializer();
            popupStage.show();

            return controller;
        }
        catch (IOException e)
        {
            System.err.println("error loading the " + fxml + " popup " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
