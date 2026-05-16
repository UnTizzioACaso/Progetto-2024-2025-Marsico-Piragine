package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BankAccountController extends WidgetController
{

    // Button to open the bank account settings page
    @FXML
    private Button accountSettingsButton;

    // Opens the bank account settings page when the button is clicked
    @FXML
    private void openAccountSettings()
    {
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/bankAccountSettingsPage.fxml");//loads the bank account settings page
    }
}
