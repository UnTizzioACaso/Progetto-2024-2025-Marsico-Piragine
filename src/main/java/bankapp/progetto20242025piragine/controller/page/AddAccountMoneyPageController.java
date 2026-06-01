package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.ConfirmAddMoneyPopupController;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAccountMoneyPageController extends BranchController
{

        @FXML
        private Label errorLabel;

        @FXML
        private TextField ownerNameTextField, cardNumberTextField, expirationTextField, cvvTextField, MoneyTextField;

        @FXML
        public void confirm()
        {
                if(ownerNameTextField.getText().isEmpty() || cardNumberTextField.getText().isEmpty() || expirationTextField.getText().isEmpty() || cvvTextField.getText().isEmpty() || MoneyTextField.getText().isEmpty())
                {
                        errorLabel.setText("Tutti i campi devono essere compilati");
                        return;
                }
                errorLabel.setText("");
                ConfirmAddMoneyPopupController controller = (ConfirmAddMoneyPopupController) PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/confirmAddMoneyPopup.fxml", 400, 500);
                controller.setConfirmMoney(MoneyTextField.getText());
                controller.calculate();

        }

        @FXML
        public void payWithApplePay()
        {
                errorLabel.setText("Apple Pay non è al momento disponibile, usare un altra modalità di pagamento");
        }

        @FXML
        public void payWithGooglePay()
        {
                errorLabel.setText("Google Pay non è al momento disponibile, usare un altra modalità di pagamento");
        }

}

