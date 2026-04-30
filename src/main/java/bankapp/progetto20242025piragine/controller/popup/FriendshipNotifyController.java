package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    }


    @FXML
    public void acceptRequest()
    {
        try
        {
            User u = UserDAO.getUserByUsername(friendshipUsernameLabel.getText());
            FriendRequestDAO.acceptRequest(idRequest);
            try
            {
                FriendshipDAO.addFriendship(rootController.user.getUserID(),  u.getUserID());
                rootController.topbarController.updateNotifications();
            }
            catch (SQLException e)
            {
                System.err.println("error during adding the friendship in the db " + e.getMessage());
                e.printStackTrace();
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during getting user from the db " + e.getMessage());
            e.printStackTrace();
        }
    }

}