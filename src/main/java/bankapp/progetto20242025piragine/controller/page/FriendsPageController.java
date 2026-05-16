package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.FriendContactController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsPageController extends BranchController
{
    public User friend;

    public FriendContactController currentFriendController = null;

    @FXML
    public VBox friendsVBox;

    @FXML
    public TextField noteTextFiled;

    @FXML
    public Label errorLabel;

    @FXML
    public VBox chatVBox;

    @FXML
    private TextField valueField;

    @FXML
    public ScrollPane chatScrollPane;

    @FXML
    public void loadFriendshipRequestPopup()
    {
        PopupCreator.showPopup("Invia aggiungi un amico", "/bankapp/progetto20242025piragine/fxml/popup/friendshipRequestPopup.fxml", 420, 250);
    }

    @FXML
    public void requestTransaction()
    {
        if (currentFriendController == null)
        {
            writeError("Devi selezionare un amico per richiedere denaro");
            return;
        }

        // string validation
        BigDecimal value = ValueValidator.validateFormat(valueField);
        if (value == null)
        {
            writeError("Il valore inserito è in formato non valido");
            return;
        }

        BigDecimal maxLimit = new BigDecimal("1000.00");

        // numeric value validation
        if (value.compareTo(maxLimit) > 0)
        {
            writeError("Il limite massimo di richiesta è 10.000");
            return;
        }
        if (value.compareTo(BigDecimal.ZERO) < 0)
        {
            writeError("la richiesta non può essere negativa");
            return;
        }

        int requesterAccount = BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID());
        int requestedAccount = BankAccountDAO.getIdAccountByUserId(friend.getUserID());
        Transaction t = new Transaction(requesterAccount, requestedAccount,value, noteTextFiled.getText(), "request", -1, "pending");

        if (!(TransactionDAO.insertTransaction(t)))
        {
            writeError("errore durante l'invio della richiesta");
            return;
        }

        //creating and sending the notifies to each user
        Notify n = new Notify(friend.getUserID(), t.getIdTransaction(), null, noteTextFiled.getText());
        Notify n2 = new Notify(CurrentSession.getLoggedUser().getUserID(), t.getIdTransaction(), null, noteTextFiled.getText());
        NotifyDAO.insertNotify(n);
        NotifyDAO.insertNotify(n2);

        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Richiesta effettuata con successo");

        currentFriendController.showChat();
    }

    @FXML
    public void sendTransaction()
    {
        if (currentFriendController == null)
        {
            writeError("Devi selezionare un amico per inviare denaro");
            return;
        }

        // string validation
        BigDecimal value = ValueValidator.validateFormat(valueField);
        if (value == null)
        {
            writeError("Il valore inserito è in formato non valido");
            return;
        }

        BigDecimal maxLimit = new BigDecimal("1000.00");
        BigDecimal userLimit = BankAccountDAO.getAccountByUserId(CurrentSession.getLoggedUser().getUserID()).getMaxTransfer();

        // numeric value validation
        if (value.compareTo(maxLimit) > 0)
        {
            writeError("Il limite massimo di invio è 10.000");
            return;
        }
        if (value.compareTo(BigDecimal.ZERO) < 0)
        {
            writeError("Il limite non può essere negativo");
            return;
        }
        if(value.compareTo(userLimit) > 0)
        {
            writeError("Il limite d'invio prestabilito dall'utente è: " + userLimit);
            return;
        }

        // creating the transaction java object
        BankAccount senderAccount = BankAccountDAO.getAccountById(CurrentSession.getLoggedUser().getUserID());
        BankAccount beneficiaryAccount = BankAccountDAO.getAccountByUserId(friend.getUserID());

        if(senderAccount == null || beneficiaryAccount == null)
        {
            writeError("errore durante l'invio della donazione");
            return;
        }

        if (senderAccount.getMoney().compareTo(value) < 0)
        {
            writeError("Non hai credito sufficiente per inviare la donazione");
            return;
        }

        if(!BankAccountDAO.transferMoney(beneficiaryAccount, senderAccount, value))
        {
            writeError("errore durante l'invio della donazione");
            return;
        }

        Transaction t = new Transaction(senderAccount.getIdAccount(), beneficiaryAccount.getIdAccount(), value, noteTextFiled.getText(), "donation", -1);

        //tring to insert the transaction in the db
        if(!(TransactionDAO.insertTransaction(t)))
        {
            writeError("errore durante l'invio della donazione");
            return;
        }

        //creating and sending the notifies to each user
        Notify n = new Notify(friend.getUserID(), t.getIdTransaction(), null, noteTextFiled.getText());
        Notify n2 = new Notify(CurrentSession.getLoggedUser().getUserID(), t.getIdTransaction(), null, noteTextFiled.getText());
        NotifyDAO.insertNotify(n);
        NotifyDAO.insertNotify(n2);

        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Transazione effettuata con successo");

        currentFriendController.showChat();
    }


    private void writeError(String message)
    {
        errorLabel.setTextFill(Paint.valueOf("red"));
        errorLabel.setText(message);
    }

    @Override
    public void initializer()
    {

        errorLabel.setText("");
        List<Integer> friends = new ArrayList<>();
        try {friends = FriendshipDAO.getFriendshipsByUserId(CurrentSession.getLoggedUser().getUserID());}
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
                Node friendContact= loader.load();
                FriendContactController controller = loader.getController();
                controller.friendsPageController = this;
                controller.friendUsernameLabel.setText(UserDAO.getUserByUserID(id).getUsername());

                friendsVBox.getChildren().add(friendContact);
            }
            catch(Exception e)
            {
                System.err.println("error during loading a friend" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
