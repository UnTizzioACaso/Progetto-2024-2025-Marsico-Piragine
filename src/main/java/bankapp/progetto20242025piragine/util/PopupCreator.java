package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.IOException;

public class PopupCreator
{
    public static BranchController showPopup(String title, String fxml, int width, int height)
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader(fxml);
        Parent root = (Parent) p.getValue();
        BranchController controller = p.getKey(); //getting the controller from the loader
        Stage popupStage = new Stage(); //creating a new stage for the popup
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupStage.setWidth(width); //setting popup's minimum width
        popupStage.setHeight(height);
        popupStage.setResizable(false);
        popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
        Stage ownerStage = (Stage) CurrentSession.getRootController().rootWindow.getScene().getWindow();
        popupStage.initOwner(ownerStage);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        popupStage.setScene(scene);
        popupStage.show();
        ThemeManager.applyTheme(scene, CurrentSession.getLoggedUser().getTheme());
        return controller;
    }
}
