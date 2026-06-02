package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.ConfirmAddMoneyPopupController;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class AddAccountMoneyPageController extends BranchController
{

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
                errorLabel.setText("");
                ConfirmAddMoneyPopupController controller = (ConfirmAddMoneyPopupController) PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/confirmAddMoneyPopup.fxml", 400, 500);
                controller.setConfirmMoney(value.toString());
                controller.calculate();

        }

        @FXML
        private void payWithApplePay()
        {
                errorLabel.setText("Apple Pay non è al momento disponibile, usare un altra modalità di pagamento");
        }

        @FXML
        private void payWithGooglePay()
        {
                errorLabel.setText("Google Pay non è al momento disponibile, usare un altra modalità di pagamento");
        }

}

