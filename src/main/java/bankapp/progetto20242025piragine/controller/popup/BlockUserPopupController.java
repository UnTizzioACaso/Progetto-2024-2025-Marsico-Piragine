package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BlockDAO;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BlockUserPopupController extends BranchController {

    @FXML
    public Label wouldYouLikeToBlockLabel;

    private Stage stage = (Stage) wouldYouLikeToBlockLabel.getScene().getWindow();

    public String username;

    @FXML
    public void acceptBlock()
    {
        try
        {
            int id = UserDAO.getUserByUsername(username).getUserID();
            try {BlockDAO.blockUser(rootController.user.getUserID(), id);}
            catch (SQLException e)
            {
                System.err.println("error trying to block the user " + id + " " + e);
                e.printStackTrace();
            }
        }
        catch (SQLException e)
        {
            System.err.println("error trying to find the user by username" + e.getMessage());
            e.printStackTrace();
        }
        reloadNotification();
        stage.close();
    }

    @Override
    public void initializer()
    {
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
        try {rootController.topbarController.updateNotifications();}
        catch (SQLException e)
        {
            System.err.println("error trying to reload notifications" + e);
            e.printStackTrace();
        }
    }

}
