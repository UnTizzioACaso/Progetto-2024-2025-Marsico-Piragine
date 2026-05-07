package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.page.FriendsPageController;
import bankapp.progetto20242025piragine.db.*;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendContactController extends BranchController
{

    public FriendsPageController friendsPageController;

    @FXML
    public Label friendUsernameLabel;

    @FXML
    public void showChat()
    {
        friendsPageController.chatVBox.setAlignment(Pos.TOP_CENTER);
        if(friendsPageController.currentFriendController == null) {friendsPageController.currentFriendController = this;}
        int friendId = UserDAO.getUserByUsername(friendUsernameLabel.getText()).getUserID();
        friendsPageController.chatVBox.getChildren().clear();

        List<Transaction> transactions;
        int userAccount;
        int friendAccount;

        userAccount = BankAccountDAO.getAccountByUserId(rootController.user.getUserID()).getIdAccount();
        friendAccount = BankAccountDAO.getAccountByUserId(friendId).getIdAccount();
        transactions = TransactionDAO.getTransactionsBetweenUserAndUser2(userAccount, friendAccount);
        Friendship f = FriendshipDAO.getFriendship(friendId, rootController.user.getUserID());

        if(f.getRequester() == rootController.user.getUserID()) //if the user sent first the friendship and the friend accepted it
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/toFriendTransactionCloud.fxml"));
            try {
                Parent cloud = loader.load();
                ToFriendTransactionCloudController controller = loader.getController();
                controller.textLabel.setText("Ciao, vuoi essere mio amico?");
                friendsPageController.chatVBox.getChildren().add(cloud);
            }
            catch (Exception e)
            {
                System.err.println("error creating user's text cloud " + e.getMessage());
                e.printStackTrace();
            }

            loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/fromFriendTransactionCloud.fxml"));
            try
            {
                Parent cloud = loader.load();
                FromFriendTransactionCloudController controller= loader.getController();
                controller.textLabel.setText("Certo!");
                friendsPageController.chatVBox.getChildren().add(cloud);
            }
            catch (Exception e)
            {
                System.err.println("error creating bot's text cloud " + e.getMessage());
                e.printStackTrace();
            }
        }
        else
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/fromFriendTransactionCloud.fxml"));
            try
            {
                Parent cloud = loader.load();
                FromFriendTransactionCloudController controller= loader.getController();
                controller.textLabel.setText("Ciao, vuoi essere mio amico?");
                friendsPageController.chatVBox.getChildren().add(cloud);
            }
            catch (Exception e)
            {
                System.err.println("error creating bot's text cloud " + e.getMessage());
                e.printStackTrace();
            }


            loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/toFriendTransactionCloud.fxml"));
            try
            {
                Parent cloud = loader.load();
                ToFriendTransactionCloudController controller= loader.getController();
                controller.textLabel.setText("Certo!");
                friendsPageController.chatVBox.getChildren().add(cloud);
            }
            catch (Exception e)
            {
                System.err.println("error creating user's text cloud " + e.getMessage());
                e.printStackTrace();
            }
        }




        for (Transaction transaction : transactions)
        {
            if(transaction.getSender().equals(userAccount))
            {
                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/fromFriendTransactionCloud.fxml"));
                    Node toFriend = fxmlLoader.load();
                    ToFriendTransactionCloudController controller = fxmlLoader.getController();
                    controller.textLabel.setText(transaction.getAmount().toString());
                    VBox toFriendVBox = new VBox();
                    toFriendVBox.setAlignment(Pos.CENTER_RIGHT);
                    toFriendVBox.setMaxWidth(140.0);
                    toFriendVBox.setMaxHeight(60.0);
                    toFriendVBox.setMinWidth(140.0);
                    toFriendVBox.setMinHeight(60.0);
                    toFriendVBox.getChildren().add(toFriend);

                } catch (Exception e)
                {
                    System.err.println("error loading transaction cloud in the chat" + e.getMessage());
                }
            }
            else
            {
                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/fromFriendTransactionCloud.fxml"));
                    Node toFriend = fxmlLoader.load();
                    ToFriendTransactionCloudController controller = fxmlLoader.getController();
                    controller.textLabel.setText(transaction.getAmount().toString());
                    VBox toFriendVBox = new VBox();
                    toFriendVBox.setAlignment(Pos.CENTER_LEFT);
                    toFriendVBox.setMaxWidth(140.0);
                    toFriendVBox.setMaxHeight(60.0);
                    toFriendVBox.setMinWidth(140.0);
                    toFriendVBox.setMinHeight(60.0);
                    toFriendVBox.getChildren().add(toFriend);

                } catch (Exception e)
                {
                    System.err.println("error loading transaction cloud in the chat" + e.getMessage());
                }
            }

        }
    }

    @Override
    public void initializer()
    {
        ThemeManager.applyTheme(friendsPageController.chatVBox.getScene(), rootController.user.getTheme());
    }



}
