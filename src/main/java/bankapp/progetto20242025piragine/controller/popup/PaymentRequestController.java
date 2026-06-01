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

    public Transaction request;

    public Notify n;



    @FXML
    public Label friendshipUsernameLabel;

    @FXML
    public Label moneyLabel;

    @FXML
    public void declineRequest()
    {
        BlockUserPopupController controller = (BlockUserPopupController) PopupCreator.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 420, 300);
        controller.wouldYouLikeToBlockLabel.setText("Vorresti bloccare " + friendshipUsernameLabel.getText() + "?");
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
        CurrentSession.getFriendsPageController().currentFriendController.showChat();
        TransactionDAO.declineTransacion(request.getIdTransaction());
        ((Stage) moneyLabel.getScene().getWindow()).close();
    }

    @FXML
    public void acceptRequest()
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
                    CurrentSession.getFriendsPageController().currentFriendController.showChat();
                    ((Stage) moneyLabel.getScene().getWindow()).close();
                }
            }
    }




}