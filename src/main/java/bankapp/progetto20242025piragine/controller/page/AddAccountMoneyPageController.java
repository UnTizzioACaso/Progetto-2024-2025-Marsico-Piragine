package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.ConfirmAddMoneyPopupController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.math.BigDecimal;
import java.time.YearMonth;

public class AddAccountMoneyPageController extends BranchController
{
        @FXML
        private  void initialize()
        {
                if(CurrentSession.isPinCorrect())
                {
                        errorLabel.setText("Ricarica avvenuta con successo");
                        errorLabel.setStyle("-fx-text-fill: green");
                }
        }
        @FXML
        private Label errorLabel;

        @FXML
        private TextField ownerNameTextField, cardNumberTextField, expirationTextField, cvvTextField, MoneyTextField;

        @FXML
        private void confirm()
        {
                if(ownerNameTextField.getText().isEmpty() || cardNumberTextField.getText().isEmpty() || expirationTextField.getText().isEmpty() || cvvTextField.getText().isEmpty() || MoneyTextField.getText().isEmpty())
                {
                        errorLabel.setText("Tutti i campi devono essere compilati");
                        return;
                }

                BigDecimal value= ValueValidator.validateFormat(MoneyTextField);
                if (value == null) {
                        errorLabel.setText("Il formato del importo inserito non è valido");
                        return;
                }
                String nameRegex = "^[a-zA-Zàáâäãåèéêëìíîïòóôöõùúûüýÿ\\s'-]{2,50}$";
                String ownerName = ownerNameTextField.getText().trim();
                if (ownerName.isEmpty() || cardNumberTextField.getText().isEmpty() ||
                        expirationTextField.getText().isEmpty() || cvvTextField.getText().isEmpty() ||
                        MoneyTextField.getText().isEmpty()) {
                        errorLabel.setText("Tutti i campi devono essere compilati");
                        return;
                }
                if (!ownerName.matches(nameRegex)) {
                        errorLabel.setText("Nome titolare non valido (usa solo lettere)");
                        return;
                }
                String cardNumber = cardNumberTextField.getText().trim();

                if (cardNumber.length() != 16) {
                        errorLabel.setText("La carta deve avere 16 cifre");
                        return;
                }

                if (!cardNumber.matches("\\d{16}")) {
                        errorLabel.setText("La carta deve contenere solo numeri");
                        return;
                }
                String expiration = expirationTextField.getText().trim();

                if (!expiration.matches("^(0[1-9]|1[0-2])\\/([0-9]{2})$")) {
                        errorLabel.setText("Data scadenza non valida (formato: MM/YY)");
                        return;
                }

                try {
                        String[] parts = expiration.split("/");
                        int month = Integer.parseInt(parts[0]);
                        int year = 2000 + Integer.parseInt(parts[1]); // Conversione yy -> 20yy

                        YearMonth expirationDate = YearMonth.of(year, month);
                        YearMonth now = YearMonth.now();

                        // Se la data di scadenza è prima del mese corrente, la carta è scaduta
                        if (expirationDate.isBefore(now)) {
                                errorLabel.setText("Carta scaduta!");
                                return;
                        }
                } catch (Exception e) {
                        errorLabel.setText("Errore nella data");
                        return;
                }
                String cvv = cvvTextField.getText().trim();
                if (!cvv.matches("\\d{3}")) {
                        errorLabel.setText("CVV non valido (deve contenere 3 cifre)");
                        return;
                }
                errorLabel.setText("");
                ConfirmAddMoneyPopupController controller = (ConfirmAddMoneyPopupController) PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/ConfirmAddMoneyPopup.fxml", 400, 500);
                controller.setConfirmMoney(value.toString());
                controller.calculate();


        }

        @FXML
        private void payWithApplePay()
        {
                errorLabel.setStyle("-fx-text-fill: red");
                errorLabel.setText("Apple Pay non è al momento disponibile, usare un altra modalità di pagamento");
        }

        @FXML
        private void payWithGooglePay()
        {
                errorLabel.setStyle("-fx-text-fill: red");
                errorLabel.setText("Google Pay non è al momento disponibile, usare un altra modalità di pagamento");
        }


}

