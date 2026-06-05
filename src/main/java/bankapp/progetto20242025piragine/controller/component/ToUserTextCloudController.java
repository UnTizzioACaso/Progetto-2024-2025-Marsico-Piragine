package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.PaymentRequestController;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ToUserTextCloudController extends BranchController
{
    @FXML
    private Label textLabel;

    private Transaction request  = null;

    private String friendUsername;

    public void setRequest(Transaction request) {this.request = request;}

    public void setText(String text) {this.textLabel.setText(text);}

    public void setFriendUsername(String friendUsername) {this.friendUsername = friendUsername;}

    @FXML
    private void showRequest()
    {
        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}
        if(request == null) return;
        PaymentRequestController controller = (PaymentRequestController) PopupCreator.showPopup("Richiesta di denaro", "/bankapp/progetto20242025piragine/fxml/popup/paymentRequest.fxml", 200, 100);
        controller.setTransaction(request);
        controller.setUsername(friendUsername);
    }
}