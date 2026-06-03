package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.FriendRequest;
import bankapp.progetto20242025piragine.model.Notify;
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

    private int idRequest;

    private Notify notify;

    public void setNotify(Notify notify) {this.notify = notify;}

    @FXML
    private Label friendshipUsernameLabel;

    public void setUsername(String username) {friendshipUsernameLabel.setText(username);}

    public void setIdRequest(int idRequest) {this.idRequest = idRequest;}

    @FXML
    private void declineRequest()
    {
        NotifyDAO.markAsRead(notify.getIdNotify());
        FriendRequestDAO.declineRequest(idRequest);
        BlockUserPopupController controller = (BlockUserPopupController) PopupCreator.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 368, 224);
        controller.setQuestion("Vorresti bloccare " + friendshipUsernameLabel.getText() + "?");
        controller.setUsername(friendshipUsernameLabel.getText());
        ((Stage)friendshipUsernameLabel.getScene().getWindow()).close();
    }

    @FXML
    private void acceptRequest()
    {
        NotifyDAO.markAsRead(notify.getIdNotify());
        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}
        FriendRequestDAO.acceptRequest(idRequest);
        FriendRequest r = FriendRequestDAO.getFriendRequestById(idRequest);
        FriendshipDAO.addFriendship(r.getRequester(), r.getRequested());
        CurrentSession.getTopbarController().updateNotifications();
        ((Stage)friendshipUsernameLabel.getScene().getWindow()).close();
    }

}