package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BlockDAO;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BlockUserPopupController extends BranchController {

    @FXML
    public Label wouldYouLikeToBlockLabel;

    private Stage stage;

    public String username;

    @FXML
    public void acceptBlock()
    {
        int id = UserDAO.getUserByUsername(username).getUserID();
        BlockDAO.blockUser(rootController.user.getUserID(), id);

        reloadNotification();
        stage.close();
    }

    @Override
    public void initializer()
    {
        stage = (Stage) wouldYouLikeToBlockLabel.getScene().getWindow();
        stage.setOnCloseRequest(event -> declineBlock());
        ThemeManager.applyTheme(wouldYouLikeToBlockLabel.getScene(), rootController.user.getTheme());
    }

    @FXML
    public void declineBlock()
    {
        reloadNotification();
        stage.close();
    }


    private void reloadNotification()
    {
       rootController.topbarController.updateNotifications();
    }

}
