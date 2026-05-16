package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.FriendRequest;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FriendshipNotifyController extends BranchController
{
    @FXML private AnchorPane anchorPaneFriendshipNotify;
    public int idRequest;

    @FXML
    public Label friendshipUsernameLabel;

    @FXML
    public void declineRequest()
    {
        FriendRequestDAO.declineRequest(idRequest);
        BlockUserPopupController controller = (BlockUserPopupController) PopupCreator.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 368, 224);
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
        CurrentSession.getRootController().topbarController.updateNotifications();
        ((Stage)friendshipUsernameLabel.getScene().getWindow()).close();
    }

    @Override
    public void initializer() {
        ThemeManager.applyTheme(anchorPaneFriendshipNotify.getScene(), CurrentSession.getLoggedUser().getTheme());
    }
}