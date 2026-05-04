package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.FriendRequestDAO;
import bankapp.progetto20242025piragine.db.FriendshipDAO;
import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class PaymentRequestController extends BranchController {

    public int idRequest;

    @FXML
    private Label friendshipUsernameLabel;

    @FXML
    private Label moneyLabel;


    @FXML
    public void declineRequest()
    {

        FriendRequestDAO.declineRequest(idRequest);
        BlockUserPopupController controller = (BlockUserPopupController) rootController.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 420, 300);
        controller.wouldYouLikeToBlockLabel.setText("Vorresti bloccare " + friendshipUsernameLabel.getText() + "?");
        controller.username = friendshipUsernameLabel.getText();
    }

    @FXML
    public void acceptRequest()
    {
        User u = UserDAO.getUserByUsername(friendshipUsernameLabel.getText());
        FriendshipDAO.addFriendship(rootController.user.getUserID(), u.getUserID());
        rootController.topbarController.updateNotifications();
        FriendRequestDAO.acceptRequest(idRequest);
    }

}