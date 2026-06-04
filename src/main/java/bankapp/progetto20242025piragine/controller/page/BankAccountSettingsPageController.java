package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.ChangePinPopupController;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class BankAccountSettingsPageController extends BranchController
{
    @FXML
    private TextField sendingLimitTextfield;

    @FXML
    private Label ibanLabel,maxTransferLabel;

    @FXML
    private void initialize()
    {
        CurrentSession.setBankAccountSettingsPageController(this);
        BankAccount bankAccount = BankAccountDAO.getAccountByUserId(CurrentSession.getLoggedUser().getUserID());
        ibanLabel.setText(bankAccount.getIban());
    }
    @FXML
    private void changePin()
    {
        ChangePinPopupController controller = (ChangePinPopupController) PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/changePinPopup.fxml", 400, 500);
    }
    @FXML
    private void saveLimitButton() { // Assicurati di collegare questo metodo al bottone
        try {
            BigDecimal nuovoLimite = new BigDecimal(sendingLimitTextfield.getText());
            int idAccount = CurrentSession.getLoggedAccount().getIdAccount();
            boolean isUpdated = BankAccountDAO.updateLimit(idAccount, nuovoLimite);
            if (isUpdated) {
                maxTransferLabel.setText("Limite aggiornato!");
            } else {
                maxTransferLabel.setText("Errore nell'aggiornamento.");
            }
        } catch (NumberFormatException e) {
            maxTransferLabel.setText("Inserisci un numero valido!");
        }
    }


}
