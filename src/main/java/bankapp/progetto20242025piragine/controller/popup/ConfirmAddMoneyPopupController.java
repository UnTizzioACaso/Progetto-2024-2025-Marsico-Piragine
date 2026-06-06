package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class ConfirmAddMoneyPopupController extends BranchController {

    @FXML
    private TextField moneyConfirmTextField;
    private boolean isCorrect;
    @FXML
    private Label totalLabel, commissionLabel,LabelConfirmAddMoney;
    @FXML
    public void initialize() {

        moneyConfirmTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            BigDecimal total=ValueValidator.validateFormat(newValue);
            if (total != null){
            calculate();
            LabelConfirmAddMoney.setText("");
            isCorrect = true;
            }
            else {
                LabelConfirmAddMoney.setText("Formato non valido");
                isCorrect = false;
            }
            if (!(total.compareTo(BigDecimal.ZERO) > 0)) {
                LabelConfirmAddMoney.setText("L'importo deve essere maggiore di 0");
                isCorrect = false;
            }
            if ((total.add(CurrentSession.getLoggedAccount().getMoney()).compareTo(new BigDecimal("9223372036854775807.00")) > 0)) {
                LabelConfirmAddMoney.setText("il database non puo gestire un saldo cosi grande");
                isCorrect = false;
            }
        });
    }

    public void setConfirmMoney(String money) {moneyConfirmTextField.setText(money);}


    public void calculate()
    {

        totalLabel.setText((new BigDecimal(moneyConfirmTextField.getText()).add(new BigDecimal(commissionLabel.getText()))).toString());

    }
    @FXML
    private void ConfirmAddMoney()
    {
        if(!isCorrect){
            return;
        }
        BigDecimal total = new BigDecimal(totalLabel.getText());
        CurrentSession.setIsPinCorrect(true);
        CurrentSession.getTopbarController().reloadPage();
        BankAccountDAO.updateBalance(CurrentSession.getLoggedAccount().getIdAccount(), total);
        CurrentSession.setLoggedAccount(BankAccountDAO.getAccountById(CurrentSession.getLoggedAccount().getIdAccount())); //update logged account to get the correct balance after a transaction request is accepted
        ((Stage) moneyConfirmTextField.getScene().getWindow()).close();

    }

    @FXML
    private void closePopup()
    {
        ((Stage) moneyConfirmTextField.getScene().getWindow()).close();
    }

}
