package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SidebarController extends BranchController {

    @FXML
    private ImageView accountPopupButton;

    @FXML
    private void openAccountPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/accountPopup.fxml")
            );
            Parent root = loader.load();
            Stage popupStage = new Stage();
            popupStage.setTitle("Account");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
