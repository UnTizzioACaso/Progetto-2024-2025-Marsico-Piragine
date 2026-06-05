package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.page.FriendsPageController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.Friendship;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.EasyFxmlLoader;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Pair;

import java.util.List;

public class FriendContactController extends BranchController
{


    @FXML
    private Label friendUsernameLabel;

    public void setFriendUsername(String username)
    {
        friendUsernameLabel.setText(username);
    }

    @FXML
    public void showChat()
    {
        CurrentSession.getFriendsPageController().showChatWith(friendUsernameLabel.getText());
    }

    @FXML
    private void initialize()
    {
        ThemeManager.applyTheme(CurrentSession.getFriendsPageController().getChatVBox().getScene(), CurrentSession.getLoggedUser().getTheme());;
    }



}
