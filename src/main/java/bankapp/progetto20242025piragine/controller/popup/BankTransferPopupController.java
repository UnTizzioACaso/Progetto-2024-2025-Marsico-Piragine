package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.math.BigDecimal;


public class BankTransferPopupController extends BranchController
{
    @FXML
    private TextField amountTextField, nameTextField, ibanTextField, noteTextField;

    @FXML
    private Label usernameErrorLabel, ibanErrorLabel, moneyErrorLabel, noteErrorLabel, successLabel;

    @FXML
    public void initialize()
    {
            usernameErrorLabel.setText("");
            ibanErrorLabel.setText("");
            moneyErrorLabel.setText("");
            noteErrorLabel.setText("");
            amountTextField.setText("");
            nameTextField.setText("");
            ibanTextField.setText("");
            noteTextField.setText("");
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
        int commission = 1000;

        if (CurrentSession.getLoggedAccount().getMoney().compareTo(amount.add(new BigDecimal(commission))) < 0)
        {
            moneyErrorLabel.setText("Saldo insufficiente");
            moneyErrorLabel.setTextFill(Paint.valueOf("red"));
            return;
        }

        int userBeneficiary = UserDAO.getUserByUsername(nameTextField.getText()).getUserID();
        BankAccount accountBeneficiary = BankAccountDAO.getAccountByUserId(userBeneficiary);
        Transaction t  = new Transaction(CurrentSession.getLoggedAccount().getIdAccount(), accountBeneficiary.getIdAccount(), amount, noteTextField.getText(), "payment", -1, "accepted");
        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect())
        {return;}
        if(BankAccountDAO.transferMoneyWithCommission(accountBeneficiary, CurrentSession.getLoggedAccount(), t, commission))
        {
            successLabel.setText("Bonifico effettuato con successo");
            successLabel.setTextFill(Paint.valueOf("green"));
            initialize();
        }
        else {
            successLabel.setText("Errore durante il bonifico");
            successLabel.setTextFill(Paint.valueOf("red"));
            initialize();
        }
    }
    public void abortBankTransfer()
    {
        ((Stage) amountTextField.getScene().getWindow()).close();
    }

}
