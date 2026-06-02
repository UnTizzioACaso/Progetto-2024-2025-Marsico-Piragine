package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BankAccountSettingsPageController extends BranchController
{
    @FXML
    private TextField sendingLimitTextfield;

    @FXML
    private Label ibanLabel;

    @FXML
    private void initialize()
    {
        CurrentSession.setBankAccountSettingsPageController(this);
        BankAccount bankAccount = BankAccountDAO.getAccountByUserId(CurrentSession.getLoggedUser().getUserID());
        ibanLabel.setText(bankAccount.getIban());
    }

}
