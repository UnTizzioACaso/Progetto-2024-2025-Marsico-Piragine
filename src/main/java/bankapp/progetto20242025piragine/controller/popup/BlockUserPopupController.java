package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BlockDAO;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BlockUserPopupController extends BranchController {

    @FXML
    private Label wouldYouLikeToBlockLabel;

    private Stage stage;

    private String username;

    public void setQuestion(String s) {wouldYouLikeToBlockLabel.setText(s);}
    public void setUsername(String username) {this.username = username;}

    @FXML
    private void acceptBlock()
    {
        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}
        stage = (Stage) wouldYouLikeToBlockLabel.getScene().getWindow();
        int id = UserDAO.getUserByUsername(username).getUserID();
        BlockDAO.blockUser(CurrentSession.getLoggedUser().getUserID(), id);
        reloadNotification();
        stage.close();
    }

    @FXML
    private void declineBlock()
    {
        stage = (Stage) wouldYouLikeToBlockLabel.getScene().getWindow();
        reloadNotification();
        stage.close();
    }

    private void reloadNotification()
    {
        CurrentSession.getTopbarController().updateNotifications();
    }



}
