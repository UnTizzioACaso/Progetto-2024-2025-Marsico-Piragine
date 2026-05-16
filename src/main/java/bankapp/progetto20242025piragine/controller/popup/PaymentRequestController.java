package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
        controller.username = friendshipUsernameLabel.getText();
        if(n != null)
        {
            if (TransactionDAO.declineTransacion(n.getIdTransaction()))
            {

                NotifyDAO.markAsRead(n.getIdNotify());
                return;
            }
        }
        CurrentSession.getFriendsPageController().currentFriendController.showChat();
        TransactionDAO.declineTransacion(request.getIdTransaction());
    }

    @FXML
    public void acceptRequest()
    {
        if(n != null)
        {
            if (TransactionDAO.acceptTransacion(n.getIdTransaction()))
            {
                NotifyDAO.markAsRead(n.getIdNotify());
                CurrentSession.getRootController().topbarController.updateNotifications();
            }
            return;
        }
        CurrentSession.getFriendsPageController().currentFriendController.showChat();
        TransactionDAO.acceptTransacion(request.getIdTransaction());
    }

}