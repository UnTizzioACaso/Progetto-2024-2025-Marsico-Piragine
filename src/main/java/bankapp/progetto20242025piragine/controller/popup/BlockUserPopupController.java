package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BlockDAO;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BlockUserPopupController extends BranchController {

    @FXML
    public Label wouldYouLikeToBlockLabel;

    private Stage stage;


    public String username;

    @FXML
    public void acceptBlock()
    {

        int id = UserDAO.getUserByUsername(username).getUserID();
        BlockDAO.blockUser(CurrentSession.getLoggedUser().getUserID(), id);

        reloadNotification();
        stage.close();
    }

    @FXML
    public void initialize()
    {
        stage = (Stage) wouldYouLikeToBlockLabel.getScene().getWindow();
        stage.setOnCloseRequest(event -> declineBlock());
    }

    @FXML
    public void declineBlock()
    {
        reloadNotification();
        stage.close();
    }


    private void reloadNotification()
    {
        CurrentSession.getRootController().topbarController.updateNotifications();
    }

}
