package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.FriendContactController;
import bankapp.progetto20242025piragine.db.FriendshipDAO;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsPageController extends BranchController
{
    @FXML
    public VBox friendsVBox;

    @FXML
    public VBox chatVBox;

    @FXML
    private Button sendButton, requestButton;

    @FXML
    private TextField valueField;

    @FXML
    public void loadFriendshipRequestPopup()
    {
        rootController.showPopup("Invia aggiungi un amico", "/bankapp/progetto20242025piragine/fxml/popup/friendshipRequestPopup.fxml", 420, 300);
    }

    @Override
    public void initializer()
    {


        List<Integer> friends = new ArrayList<>();
        try {friends = FriendshipDAO.getFriendshipsByUserId(rootController.user.getUserID());}
        catch (SQLException e)
        {
            System.err.println("error during loading all friends list" + e.getMessage());
            e.printStackTrace();
        }

        for(Integer id: friends)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/friendContact.fxml"));
                FriendContactController controller = loader.load();
                controller.setRootController(rootController);
                controller.chatVBox = chatVBox;
                try {controller.friendUsernameLabel.setText(UserDAO.getUserByUserID(id).getUsername());}
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
