package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SidebarController extends BranchController {

    @FXML
    Button accountPopupButton;
    @FXML
    void openAccountPopup() //opening the account popup
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/accountPopup.fxml")); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            Stage popupStage = new Stage(); //creating a new stage for the accountPopup
            popupStage.setTitle("Account"); //setting the title
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}