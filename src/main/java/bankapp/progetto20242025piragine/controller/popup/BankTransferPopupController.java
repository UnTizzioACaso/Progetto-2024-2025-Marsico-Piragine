package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.math.BigDecimal;


public class BankTransferPopupController extends BranchController
{
    @FXML
    private TextField amountTextField, nameTextField, ibanTextField, noteTextField;

    @FXML
    private Label usernameErrorLabel, ibanErrorLabel, moneyErrorLabel, noteErrorLabel;

    @FXML
    public void initialize()
    {

    }

    @FXML
    public void sendBankTransfer()
    {
        if(!BankAccountDAO.existsByIban(ibanTextField.getText()))
        {
            ibanErrorLabel.setText("IBAN non valido");
            ibanErrorLabel.setTextFill(Paint.valueOf("red"));
            return;
        }

        if(!UserDAO.existsByUsername(nameTextField.getText()))
        {
            usernameErrorLabel.setText("Username non valido");
            usernameErrorLabel.setTextFill(Paint.valueOf("red"));
            return;
        }

        if(noteTextField.getText().isEmpty())
        {
            noteErrorLabel.setText("La nota non può essere vuota");
            noteErrorLabel.setTextFill(Paint.valueOf("red"));
            return;
        }



        BigDecimal amount = ValueValidator.validateFormat(amountTextField);
        if (amount == null)
        {
            moneyErrorLabel.setText("formato non valido");;
            moneyErrorLabel.setTextFill(Paint.valueOf("red"));
            return;
        }


        int userBeneficiary = UserDAO.getUserByUsername(nameTextField.getText()).getUserID();
        int accountBeneficiary = BankAccountDAO.getIdAccountByUserId(userBeneficiary);
        Transaction T  = new Transaction(CurrentSession.getLoggedAccount().getIdAccount(), accountBeneficiary, amount, noteTextField.getText(), "payment", -1) ;;;;
    }
    public void abortBankTransfer()
    {
        ((Stage) amountTextField.getScene().getWindow()).close();
    }

}
