package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FriendshipNotifyController extends BranchController
{
    public int idRequest;

    @FXML
    public Label friendshipUsernameLabel;

    @FXML
    public void declineRequest()
    {
        FriendRequestDAO.declineRequest(idRequest);
        BlockUserPopupController controller = (BlockUserPopupController) rootController.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 420, 300);
        controller.wouldYouLikeToBlockLabel.setText("Vorresti bloccare " + friendshipUsernameLabel.getText() + "?");
        controller.username = friendshipUsernameLabel.getText();
        ((Stage)friendshipUsernameLabel.getScene().getWindow()).close();
    }


    @FXML
    public void acceptRequest()
    {
        User u = UserDAO.getUserByUsername(friendshipUsernameLabel.getText());
        FriendRequestDAO.acceptRequest(idRequest);
        FriendRequest r = FriendRequestDAO.getFriendRequestById(idRequest);
        FriendshipDAO.addFriendship(r.getRequester(), r.getRequested());
        rootController.topbarController.updateNotifications();
        ((Stage)friendshipUsernameLabel.getScene().getWindow()).close();
    }

}