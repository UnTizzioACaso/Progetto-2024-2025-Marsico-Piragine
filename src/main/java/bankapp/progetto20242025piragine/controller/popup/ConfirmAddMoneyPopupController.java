package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class ConfirmAddMoneyPopupController extends BranchController {
    @FXML
    private TextField moneyConfirmTextField;

    @FXML
    private Label totalLabel, commissionLabel;

    public void setConfirmMoney(String money) {moneyConfirmTextField.setText(money);}

    public void calculate()
    {
        totalLabel.setText((new BigDecimal(moneyConfirmTextField.getText()).add(new BigDecimal(commissionLabel.getText()))).toString());
    }

}
