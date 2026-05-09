package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class PaymentRequestController extends BranchController {

    public int idRequest;

    public Notify n;

    @FXML
    private Label friendshipUsernameLabel;

    @FXML
    private Label moneyLabel;


    @FXML
    public void declineRequest()
    {
        if(TransactionDAO.declineTransacion(n.getIdTransaction()))
        {
            BlockUserPopupController controller = (BlockUserPopupController) rootController.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 420, 300);
            controller.wouldYouLikeToBlockLabel.setText("Vorresti bloccare " + friendshipUsernameLabel.getText() + "?");
            controller.username = friendshipUsernameLabel.getText();
            NotifyDAO.markAsRead(n.getIdNotify());
        }
    }

    @FXML
    public void acceptRequest()
    {
        if(TransactionDAO.acceptTransacion(n.getIdTransaction()))
        {
            User u = UserDAO.getUserByUsername(friendshipUsernameLabel.getText());
            FriendshipDAO.addFriendship(rootController.user.getUserID(), u.getUserID());
            rootController.topbarController.updateNotifications();
            FriendRequestDAO.acceptRequest(n.getIdTransaction());
            NotifyDAO.markAsRead(n.getIdNotify());
        }
    }

}