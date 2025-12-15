package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AccountPopupController extends BranchController
{
    @FXML
    Label emailPopupAccountLabel, themeColorAccountPopupLabel, nameSurnameAccountPopupLabel;


    @FXML
    public void initialize()
    {
        emailPopupAccountLabel.setText(rootController.user.getEmail());
        nameSurnameAccountPopupLabel.setText(rootController.user.getFirstName() + " " + rootController.user.getLastName());
    }




}
