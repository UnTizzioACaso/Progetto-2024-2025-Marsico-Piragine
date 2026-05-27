package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddAccountMoneyPopupController extends BranchController {

    @FXML
    public Button closeAddAccountMoneyPopup;

    @FXML
    public void closePopup() {

        ((Stage) closeAddAccountMoneyPopup.getScene().getWindow()).close();
    }
}