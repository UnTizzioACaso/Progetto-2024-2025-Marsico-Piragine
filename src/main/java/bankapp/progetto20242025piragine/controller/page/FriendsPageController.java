package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.FriendContactController;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsPageController extends BranchController
{
    @FXML
    public VBox friendsVBox;

    @FXML VBox chatVBox;

    @Override
    public void initializer()
    {
        List<Integer> friends = new ArrayList<>(); //da sostituire con una chiama del DAO quando davide lo sistemerà
        for(Integer id : friends)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/friendContact.fxml"));
                FriendContactController controller = loader.load();
                controller.setRootController(rootController);
                controller.chatVBox = chatVBox;
                try
                {
                    controller.friendUsernameLabel.setText(UserDAO.getUserByUserID(id).getUsername());
                }
                catch (SQLException e)
                {
                    System.err.println("error during loading a friend name from db" + e.getMessage());
                    e.printStackTrace();
                }
            }
            catch(Exception e)
            {
                System.err.println("error during loading a friend" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
