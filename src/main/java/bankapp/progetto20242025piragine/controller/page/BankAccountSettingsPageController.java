package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BankAccount;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class BankAccountSettingsPageController extends BranchController
{
    @FXML
    public TextField sendingLimitTextfield;

    @FXML
    public Label ibanLabel;

    @Override
    public void initializer()
    {


        try
        {
            BankAccount bankAccount = BankAccountDAO.getAccountByUserId(rootController.user.getUserID());
            ibanLabel.setText(bankAccount.getIban());
        }
        catch (SQLException e)
        {
            System.err.println("error loading iban correct value " + e.getMessage());
            e.printStackTrace();
        }
    }

}
