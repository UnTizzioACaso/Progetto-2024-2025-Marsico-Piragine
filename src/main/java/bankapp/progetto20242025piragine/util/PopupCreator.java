package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

public class PopupCreator
{
    public static BranchController showPopup(String title, String fxml, double width, double height)
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader(fxml);
        Parent root = (Parent) p.getValue();
        BranchController controller =  p.getKey(); //getting the controller from the loader
        Scene scene = new Scene(root);
        ThemeManager.applyTheme(scene, CurrentSession.getLoggedUser().getTheme());
        Stage popupStage = new Stage(); //creating a new stage for the popup
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupStage.setWidth(width); //setting popup's minimum width
        popupStage.setHeight(height);
        popupStage.setResizable(false);
        popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
        Stage ownerStage = (Stage) CurrentSession.getRootController().rootWindow.getScene().getWindow();
        popupStage.initOwner(ownerStage);
        scene.setFill(Color.TRANSPARENT);
        popupStage.setScene(scene);
        popupStage.show();
        return controller;
    }

    public static BranchController showAndWaitPopup(String title, String fxml, double width, double height)
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader(fxml);
        Parent root = (Parent) p.getValue();
        BranchController controller =  p.getKey(); //getting the controller from the loader
        Scene scene = new Scene(root);
        ThemeManager.applyTheme(scene, CurrentSession.getLoggedUser().getTheme());
        Stage popupStage = new Stage(); //creating a new stage for the popup
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupStage.setWidth(width); //setting popup's minimum width
        popupStage.setHeight(height);
        popupStage.setResizable(false);
        popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
        Stage ownerStage = (Stage) CurrentSession.getRootController().rootWindow.getScene().getWindow();
        popupStage.initOwner(ownerStage);
        scene.setFill(Color.TRANSPARENT);
        popupStage.setScene(scene);
        popupStage.showAndWait();
        return controller;
    }




}
