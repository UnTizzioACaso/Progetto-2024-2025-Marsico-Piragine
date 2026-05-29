package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.PaymentRequestController;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class FromUserTextCloudController extends BranchController
{
    @FXML
    public Label textLabel;

    public Transaction request  = null;

    public String friendUsername;

    @FXML
    public StackPane bubbleContainer;


    @FXML
    public void showRequest()
    {
        if(request == null) return;
        PaymentRequestController controller = (PaymentRequestController) PopupCreator.showPopup("Richiesta di denaro", "/bankapp/progetto20242025piragine/fxml/popup/paymentRequest.fxml", 300, 200);
        controller.request = request;
        controller.friendshipUsernameLabel.setText(friendUsername);
    }
}
