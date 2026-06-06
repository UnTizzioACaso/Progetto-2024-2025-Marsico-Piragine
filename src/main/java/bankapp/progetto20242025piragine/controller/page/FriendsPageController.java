package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.FriendContactController;
import bankapp.progetto20242025piragine.controller.component.FromUserTextCloudController;
import bankapp.progetto20242025piragine.controller.component.ToUserTextCloudController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.*;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.EasyFxmlLoader;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FriendsPageController extends BranchController
{
    private User friend;

    @FXML
    private VBox friendsVBox, chatVBox;

    @FXML
    private TextField noteTextFiled, valueField;

    @FXML
    private Label errorLabel;

    @FXML
    private ScrollPane chatScrollPane;

    public VBox getChatVBox() {return chatVBox;}

    public void setFriend(User friend) {this.friend = friend;}


    @FXML
    private void loadFriendshipRequestPopup()
    {
        PopupCreator.showPopup("Invia aggiungi un amico", "/bankapp/progetto20242025piragine/fxml/popup/friendshipRequestPopup.fxml", 420, 250);
    }

    @FXML
    private void requestTransaction()
    {

        if (friend == null) {writeError("Devi selezionare un amico per richiedere denaro");return;}

        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}

        // string validation
        BigDecimal value = ValueValidator.validateFormat(valueField.getText());
        if (value == null){writeError("Il valore inserito è in formato non valido");return;}

        BigDecimal maxLimit = new BigDecimal("1000.00");

        // numeric value validation
        if (value.compareTo(maxLimit) > 0) {writeError("Il limite massimo di richiesta è 10.000");return;}

        if (value.compareTo(BigDecimal.ZERO) < 0) {writeError("la richiesta non può essere negativa");return;}

        int beneficiaryAccount = CurrentSession.getLoggedAccount().getIdAccount();
        int senderAccount = BankAccountDAO.getIdAccountByUserId(friend.getUserID());
        Transaction t = new Transaction(senderAccount, beneficiaryAccount,value, noteTextFiled.getText(), "request", -1, "pending");

        if (!(TransactionDAO.insertTransaction(t))) {writeError("errore durante l'invio della richiesta");return;}

        //creating and sending the notifies to each user
        Notify n = new Notify(friend.getUserID(), t.getIdTransaction(), null, noteTextFiled.getText());
        Notify n2 = new Notify(CurrentSession.getLoggedUser().getUserID(), t.getIdTransaction(), null, noteTextFiled.getText());
        NotifyDAO.insertNotify(n);
        NotifyDAO.insertNotify(n2);

        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Richiesta effettuata con successo");

        showChatWith(friend.getUsername());
    }

    @FXML
    private void sendTransaction()
    {
        if (friend == null) {writeError("Devi selezionare un amico per inviare denaro");return;}

        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}

        // string validation
        BigDecimal value = ValueValidator.validateFormat(valueField.getText());
        if (value == null) {writeError("Il valore inserito è in formato non valido");return;}

        BigDecimal maxLimit = new BigDecimal("100.00");
        BigDecimal userLimit = BankAccountDAO.getAccountByUserId(CurrentSession.getLoggedUser().getUserID()).getMaxTransfer();

        // numeric value validation
        if (value.compareTo(maxLimit) > 0) {writeError("Il limite massimo di invio è 100");return;}

        if (value.compareTo(BigDecimal.ZERO) < 0) {writeError("Il limite non può essere negativo");return;}

        if(value.compareTo(userLimit) > 0) {writeError("Il limite d'invio prestabilito dall'utente è: " + userLimit);return;}

        BankAccount beneficiaryAccount = BankAccountDAO.getAccountByUserId(friend.getUserID());
        BankAccount senderAccount = CurrentSession.getLoggedAccount();




        if(senderAccount == null || beneficiaryAccount == null) {writeError("errore durante l'invio della donazione");return;}

        if (senderAccount.getMoney().compareTo(value) < 0) {writeError("Non hai credito sufficiente per inviare la donazione");return;}

        if (value.add(beneficiaryAccount.getMoney()).compareTo(new BigDecimal("9223372036854775807,00")) > 0)
        {
            writeError("errore durante l'invio della donazione");
            return;
        }

        Transaction t = new Transaction(senderAccount.getIdAccount(), beneficiaryAccount.getIdAccount(), value, noteTextFiled.getText(), "donation", -1, "accepted");

        if(!BankAccountDAO.transferMoney(beneficiaryAccount, senderAccount, t)) {writeError("errore durante l'invio della donazione");return;}

        //creating and sending the notifies to each user
        Notify n = new Notify(friend.getUserID(), t.getIdTransaction(), null, noteTextFiled.getText());
        NotifyDAO.insertNotify(n);

        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Transazione effettuata con successo");

        showChatWith(friend.getUsername());
    }




    @FXML
    private void initialize()
    {
        CurrentSession.setFriendsPageController(this);
        errorLabel.setText("");
        List<Integer> friends = new ArrayList<>();
        friends = FriendshipDAO.getFriendshipsByUserId(CurrentSession.getLoggedUser().getUserID());

        for(Integer id: friends)
        {
            Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/friendContact.fxml");
            Node friendContact = p.getValue();
            FriendContactController controller = (FriendContactController) p.getKey();
            controller.setFriendUsername(UserDAO.getUserByUserID(id).getUsername());
            friendsVBox.getChildren().add(friendContact);
        }
    }

    public void showChatWith(String username)
    {
        CurrentSession.getFriendsPageController().getChatVBox().setAlignment(Pos.TOP_CENTER);
        friend = UserDAO.getUserByUsername(username);
        CurrentSession.getFriendsPageController().getChatVBox().getChildren().clear();

        List<Transaction> transactions;
        int userAccount;
        int friendAccount;
        CurrentSession.getFriendsPageController().setFriend(friend);
        userAccount = BankAccountDAO.getAccountByUserId(CurrentSession.getLoggedUser().getUserID()).getIdAccount();
        friendAccount = BankAccountDAO.getAccountByUserId(friend.getUserID()).getIdAccount();
        transactions = TransactionDAO.getTransactionsBetweenAccounts(userAccount, friendAccount);
        Friendship f = FriendshipDAO.getFriendship(friend.getUserID(), CurrentSession.getLoggedUser().getUserID());

        CurrentSession.getFriendsPageController().getChatVBox().getChildren().clear();

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
                controller.setRequest(transaction);
                controller.setFriendUsername(username);;
            }
            else if (transaction.getBeneficiary().equals(friendAccount) && transaction.getType().equals("donation"))
            {
                sendCloud(transaction.getNote() + ": " + transaction.getAmount());
            }
        }

        Platform.runLater(() -> {
            chatScrollPane.requestFocus();
            chatScrollPane.setVvalue(1.0); // Scroll to the bottom
        });
    }

    private void sendCloud(String text)
    {
        Pair <BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/fromUserTextCloud.fxml");
        FromUserTextCloudController controller = (FromUserTextCloudController) p.getKey();
        controller.setText(text);
        CurrentSession.getFriendsPageController().getChatVBox().getChildren().add(p.getValue());
    }

    private ToUserTextCloudController receiveCloud(String text)
    {
        Pair <BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/toUserTextCloud.fxml");
        ToUserTextCloudController controller = (ToUserTextCloudController) p.getKey();
        controller.setText(text);
        CurrentSession.getFriendsPageController().getChatVBox().getChildren().add(p.getValue());
        return controller;
    }

    private void writeError(String message)
    {
        errorLabel.setTextFill(Paint.valueOf("red"));
        errorLabel.setText(message);
    }
}
