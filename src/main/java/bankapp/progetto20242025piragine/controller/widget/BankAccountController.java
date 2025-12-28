package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class BankAccountController extends BranchController {

    @FXML
    private Button accountSettingsButton;

    @FXML
    private void openAccountSettings(){
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/bankAccountSettingsPage.fxml");
    }
}
