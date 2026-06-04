package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.ChangePinPopupController;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ValueValidator;
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
        PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/changePinPopup.fxml", 315, 320);
    }
    @FXML
    private void saveLimitButton()
    {
        BigDecimal newLimit = ValueValidator.validateFormat(sendingLimitTextfield.getText());

        if (newLimit == null)
        {
            maxTransferLabel.setText("Il formato del limite inserito non è valido");
            maxTransferLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (newLimit.compareTo(new BigDecimal("100.00")) > 0)
        {
            maxTransferLabel.setText("Il limite massimo è 100,00");
            maxTransferLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean isUpdated = BankAccountDAO.updateLimit(CurrentSession.getLoggedAccount().getIdAccount(), newLimit);

        if (isUpdated) {maxTransferLabel.setText("Limite aggiornato!"); maxTransferLabel.setStyle("-fx-text-fill: green;");}

        else {maxTransferLabel.setText("Errore durante l'aggiornamento del limite"); maxTransferLabel.setStyle("-fx-text-fill: red;");}

    }


}
