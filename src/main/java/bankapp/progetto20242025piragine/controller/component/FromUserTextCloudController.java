package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.PaymentRequestController;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class FromUserTextCloudController extends BranchController
{
    @FXML
    private Label textLabel;

    public void setText(String text) {textLabel.setText(text);}

}
