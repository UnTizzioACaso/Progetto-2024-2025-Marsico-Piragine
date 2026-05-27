package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BankTransferPopupController extends BranchController
{
    @FXML
    private TextField amountTextField;

    @FXML
    public void initialize()
    {

    }

    @FXML
    public void sendBankTransfer()
    {

    }
    public void abortBankTransfer()
    {
        ((Stage) amountTextField.getScene().getWindow()).close();
    }

}
