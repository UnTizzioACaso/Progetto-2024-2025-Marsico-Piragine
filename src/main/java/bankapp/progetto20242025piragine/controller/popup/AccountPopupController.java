package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;


public class AccountPopupController extends BranchController
{
    @FXML
    private Label emailPopupAccountLabel, themeColorAccountPopupLabel, nameSurnameAccountPopupLabel;

    @FXML
    public RadioButton themeColorAccountRadioButton;

    public void showCorrectValues() //this method sets correct values on top of placeholder ones
    {
        emailPopupAccountLabel.setText(rootController.user.getEmail());
        themeColorAccountPopupLabel.setText(rootController.user.getTheme());
        nameSurnameAccountPopupLabel.setText(rootController.user.getFirstName() + " " + rootController.user.getLastName());
    }




}
