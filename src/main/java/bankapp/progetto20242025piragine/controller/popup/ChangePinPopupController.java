package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangePinPopupController extends BranchController {

    @FXML private AnchorPane rootPane;
    @FXML private Label errorMessageLabel;
    @FXML private PasswordField currentPinPasswordField;
    @FXML private PasswordField newPinPasswordField;
    @FXML private PasswordField confirmNewPinPasswordField;
    @FXML
    private void closePopup() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void completeChange() {
        String currentPin = currentPinPasswordField.getText();
        String newPin = newPinPasswordField.getText();
        String confirmPin = confirmNewPinPasswordField.getText();

        if (currentPin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
            errorMessageLabel.setText("Compila tutti i campi.");
            return;
        }
        if (!newPin.equals(confirmPin)) {
            errorMessageLabel.setText("I nuovi PIN non corrispondono.");
            return;
        }
        if (currentPin.equals(newPin)) {
            errorMessageLabel.setText("Il nuovo PIN deve essere diverso da quello attuale.");
            return;
        }
        if (newPin.length() < 4) {
            errorMessageLabel.setText("Il PIN deve essere di almeno 4 cifre.");
            return;
        }
        var loggedUser = CurrentSession.getLoggedUser();

        boolean isCurrentPinCorrect = PasswordUtil.checkPassword(currentPin, loggedUser.getPinHash());

        if (!isCurrentPinCorrect) {
            errorMessageLabel.setText("Il PIN corrente è errato.");
            return;
        }

        loggedUser.setPinHash(PasswordUtil.hashPassword(newPin));
        boolean isUpdated = UserDAO.updatepin(loggedUser.getUserID());
        if (isUpdated) {
            System.out.println("PIN aggiornato e hash salvato con successo!");
            errorMessageLabel.setText("");
            closePopup();
        } else {
            errorMessageLabel.setText("Errore durante il salvataggio nel database.");
        }
    }
}