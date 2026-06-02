package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PaymentRequestController extends BranchController {

    private Transaction request;

    private Notify n;

    @FXML
    private Label friendshipUsernameLabel;

    @FXML
    private Label moneyLabel;

    public void setUsername(String username) {friendshipUsernameLabel.setText(username);}

    public void setNotify(Notify n) {this.n = n;}

    public void setTransaction(Transaction request) {this.request = request;}

    @FXML
    private void declineRequest()
    {
        BlockUserPopupController controller = (BlockUserPopupController) PopupCreator.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 420, 300);
        controller.setQuestion("Vorresti bloccare " + friendshipUsernameLabel.getText() + "?");
        controller.setUsername(friendshipUsernameLabel.getText());
        if(n != null)
        {
            if (TransactionDAO.declineTransacion(n.getIdTransaction()))
            {

                NotifyDAO.markAsRead(n.getIdNotify());
                ((Stage) moneyLabel.getScene().getWindow()).close();
                return;
            }
        }
        CurrentSession.getFriendsPageController().getCurrentFriendController().showChat();
        TransactionDAO.declineTransacion(request.getIdTransaction());
        ((Stage) moneyLabel.getScene().getWindow()).close();
    }

    @FXML
    private void acceptRequest()
    {
        PopupCreator.showAndWaitPopup("Inserisci il PIN", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
            if (CurrentSession.isPinCorrect())
            {
                if (BankAccountDAO.transferMoneyRequest(BankAccountDAO.getAccountById(request.getBeneficiary()), CurrentSession.getLoggedAccount(), request))
                {
                    //if this popup is generated from a notification, mark it as read and close the popup
                    if (n != null)
                    {
                        NotifyDAO.markAsRead(n.getIdNotify());
                        CurrentSession.getTopbarController().updateNotifications();
                        ((Stage) moneyLabel.getScene().getWindow()).close();
                        return;
                    }

                    //if this popup is generated from a friend message, show the chat and close the popup
                    BankAccountDAO.transferMoneyRequest(BankAccountDAO.getAccountById(request.getBeneficiary()), CurrentSession.getLoggedAccount(), request);
                    CurrentSession.getFriendsPageController().getCurrentFriendController().showChat();
                    ((Stage) moneyLabel.getScene().getWindow()).close();
                }
            }
    }




}