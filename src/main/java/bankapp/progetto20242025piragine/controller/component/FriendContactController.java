package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendContactController extends BranchController
{
    public VBox chatVBox;

    @FXML
    public Label friendUsernameLabel;

    public void showChat(int friendId)
    {
        chatVBox.getChildren().clear();
        List<Transaction> transactions = new ArrayList<>();
        int userAccount = 0;
        int friendAccount = 0;
        try
        {
            userAccount = BankAccountDAO.getAccountByUserId(rootController.user.getUserID()).getIdAccount();
            friendAccount = BankAccountDAO.getAccountByUserId(friendId).getIdAccount();
            transactions = TransactionDAO.getTransactionsBetweenUserAndFriend(userAccount, friendAccount);
        }
        catch(SQLException e)
        {
            System.err.println("error during loading chat with friend" + e.getMessage());
            e.printStackTrace();
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
                    controller.valueLabel.setText(transaction.getAmmount().toString());
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
                    controller.valueLabel.setText(transaction.getAmmount().toString());
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

}
