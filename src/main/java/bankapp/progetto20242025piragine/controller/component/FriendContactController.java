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

    public FriendsPageController friendsPageController = CurrentSession.getFriendsPageController();

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
            if(transaction.getBeneficiary().equals(userAccount) && transaction.getType().equals("request")) //if user is beneficiary of a request
            {
                String status = "";
                if(transaction.getStatus().equals("pending")){status = "in attesa";}
                else if(transaction.getStatus().equals("accepted")){status = "accettata";}
                else if(transaction.getStatus().equals("declined")){status = "rifiutata";}
                sendCloud(transaction.getNote() + ": " + transaction.getAmount() + " (" + status + ")");
            }
            else if (transaction.getBeneficiary().equals(userAccount) && transaction.getType().equals("donation")) //if user is beneficiary of a donation
            {
                receiveCloud(transaction.getNote() + ": " + transaction.getAmount());
            }
            else if (transaction.getBeneficiary().equals(friendAccount) && transaction.getType().equals("request")) //if friend is beneficiary of a request
            {
                String status = "";
                if(transaction.getStatus().equals("pending")){status = "in attesa";}
                else if(transaction.getStatus().equals("accepted")){status = "accettata";}
                else if(transaction.getStatus().equals("declined")){status = "rifiutata";}
                ToUserTextCloudController controller = receiveCloud(transaction.getNote() + ": " + transaction.getAmount() + " (" + status + ")");
                controller.request = transaction;
                controller.friendUsername = friendUsernameLabel.getText();
            }
            else if (transaction.getBeneficiary().equals(friendAccount) && transaction.getType().equals("donation"))
            {
               sendCloud(transaction.getNote() + ": " + transaction.getAmount());
            }
        }
    }

    @FXML
    public void initialize()
    {
        ThemeManager.applyTheme(CurrentSession.getFriendsPageController().chatVBox.getScene(), CurrentSession.getLoggedUser().getTheme());
    }

    private void sendCloud(String text)
    {
        Pair <BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/fromUserTextCloud.fxml");
        FromUserTextCloudController controller = (FromUserTextCloudController) p.getKey();
        controller.textLabel.setText(text);
        friendsPageController.chatVBox.getChildren().add(p.getValue());
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
