package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class PinPopupController extends BranchController
{
    @FXML
    private PasswordField insertPinPasswordField;

    @FXML
    private Label errorLabel;


    @FXML
    public void checkPin()
    {
        if (PasswordUtil.checkPassword(insertPinPasswordField.getText(), CurrentSession.getLoggedUser().getPinHash()))
        {
            CurrentSession.setIsPinCorrect(true);
            ((Stage)insertPinPasswordField.getScene().getWindow()).close();
        }
         else
        {
            insertPinPasswordField.clear();
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("PIN errato, riprova");;
        }
    }

    @FXML
    public void abort()
    {
        ((Stage)insertPinPasswordField.getScene().getWindow()).close();
    }


}
