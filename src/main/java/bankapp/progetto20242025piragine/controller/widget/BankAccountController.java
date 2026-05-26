package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BankAccountController extends WidgetController
{

    // Button to open the bank account settings page
    @FXML
    private Button accountSettingsButton;

    @FXML
    private Label labelBankAccount;

    // Opens the bank account settings page when the button is clicked
    @FXML
    private void openAccountSettings()
    {
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/bankAccountSettingsPage.fxml");//loads the bank account settings page
    }

    @FXML
    public void showBankTransferPopup()
    {
        PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/bankTransferPopup.fxml", 500, 510);//shows the bank transfer popup
    }

    @FXML
    public void initialize()
    {
        labelBankAccount.setText(CurrentSession.getLoggedAccount().getMoney().toString() + " €");
    }

    @FXML
    public void loadAddAccountMoneyPage()
    {
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/addAccountMoneyPage.fxml");//loads the add account money page
    }
}
