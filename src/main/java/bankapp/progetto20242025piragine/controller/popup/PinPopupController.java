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

    private boolean isPinCorrect;

    public boolean isPinCorrect() {
        return isPinCorrect;
    }

    @FXML
    public void checkPin()
    {

        if (PasswordUtil.checkPassword(insertPinPasswordField.getText(), CurrentSession.getLoggedUser().getPinHash()))
        {
            isPinCorrect = true;
            ((Stage) insertPinPasswordField.getScene().getWindow()).hide();
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
        isPinCorrect = false;
        ((Stage) insertPinPasswordField.getScene().getWindow()).close();
    }
}
