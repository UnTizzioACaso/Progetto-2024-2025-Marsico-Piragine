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

    public FriendsPageController friendsPageController;

    @FXML
    public Label friendUsernameLabel;

    @FXML
    public void showChat()
    {
        friendsPageController.chatVBox.setAlignment(Pos.TOP_CENTER);
        if(friendsPageController.currentFriendController == null) {friendsPageController.currentFriendController = this;}
        User friend = UserDAO.getUserByUsername(friendUsernameLabel.getText());
        friendsPageController.chatVBox.getChildren().clear();

        List<Transaction> transactions;
        int userAccount;
        int friendAccount;
        friendsPageController.friend = friend;
        userAccount = BankAccountDAO.getAccountByUserId(CurrentSession.getLoggedUser().getUserID()).getIdAccount();
        friendAccount = BankAccountDAO.getAccountByUserId(friend.getUserID()).getIdAccount();
        transactions = TransactionDAO.getTransactionsBetweenAccounts(userAccount, friendAccount);
        Friendship f = FriendshipDAO.getFriendship(friend.getUserID(), CurrentSession.getLoggedUser().getUserID());

        friendsPageController.chatVBox.getChildren().clear();

        if(f.getRequester() == CurrentSession.getLoggedUser().getUserID()) //if the user sent first the friendship and the friend accepted it
        {
            sendCloud("Ciao, vuoi essere mio amico?");
            receiveCloud("Certo!");
        }
        else
        {
            receiveCloud("Ciao, vuoi essere mio amico?");
            sendCloud("Certo!");
        }


        for (Transaction transaction : transactions)
        {
            if(transaction.getStatus() == null) transaction.setStatus("");
            if(transaction.getBeneficiary().equals(userAccount) && transaction.getType().equals("request"))
            {

                FromUserTextCloudController controller = sendCloud(transaction.getNote() + ": " + transaction.getAmount() + " (" + transaction.getStatus() + ")");
                controller.request = transaction;
                controller.friendUsername = friendUsernameLabel.getText();
            }
            else if (transaction.getBeneficiary().equals(userAccount))
            {
                receiveCloud(transaction.getNote() + ": " + transaction.getAmount() + " (" + transaction.getStatus() + ")");
            }
            else if (transaction.getBeneficiary().equals(friendAccount) && transaction.getType().equals("request"))
            {
                ToUserTextCloudController controller = receiveCloud(transaction.getNote() + ": " + transaction.getAmount() + " (" + transaction.getStatus() + ")");
                controller.request = transaction;
                controller.friendUsername = friendUsernameLabel.getText();
            }
            else
            {
                sendCloud(transaction.getNote() + ": " + transaction.getAmount() + " (" + transaction.getStatus() + ")");
            }

        }
    }

    @FXML
    public void initialize()
    {
        ThemeManager.applyTheme(CurrentSession.getFriendsPageController().chatVBox.getScene(), CurrentSession.getLoggedUser().getTheme());
    }

    private FromUserTextCloudController sendCloud(String text)
    {
        Pair <BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/fromUserTextCloud.fxml");
        FromUserTextCloudController controller = (FromUserTextCloudController) p.getKey();
        controller.textLabel.setText(text);
        friendsPageController.chatVBox.getChildren().add(p.getValue());
        return controller;
    }

    private ToUserTextCloudController receiveCloud(String text)
    {
        Pair <BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/toUserTextCloud.fxml");
        ToUserTextCloudController controller = (ToUserTextCloudController) p.getKey();
        controller.textLabel.setText(text);
        friendsPageController.chatVBox.getChildren().add(p.getValue());
        return controller;
    }

}
