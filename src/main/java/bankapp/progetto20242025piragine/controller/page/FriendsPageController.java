package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.FriendContactController;
import bankapp.progetto20242025piragine.db.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

    public String note;
    @FXML
    public Label errorLabel;

    @FXML
    public VBox chatVBox;

    @FXML
    private TextField valueField;

    @FXML
    public void loadFriendshipRequestPopup()
    {
        rootController.showPopup("Invia aggiungi un amico", "/bankapp/progetto20242025piragine/fxml/popup/friendshipRequestPopup.fxml", 420, 250);
    }

    @FXML
    public void requestTransaction()
    {

    }

    @FXML
    public void sendTransaction()
    {
        if (currentFriendController == null)
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Devi selezionare un amico per inviare denaro");
            return;
        }

        String value;
        // string validation
        if (valueField.getText().matches("^\\d+(,\\d{1,2})?$")) {
            value = valueField.getText().replace(",", ".");
        } else if (valueField.getText().matches("^\\d+(\\.\\d{1,2})?$")) {
            value = valueField.getText();
        }
        else
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Il valore inserito è in formato non valido");
            return;
        }


        BigDecimal v = new BigDecimal(value);
        BigDecimal maxLimit = new BigDecimal("10000.00");

        // numeric value validation
        if (v.compareTo(maxLimit) > 0) {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Il limite massimo di invio è 10.000");
            return;
        }
        if (v.compareTo(BigDecimal.ZERO) < 0) {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Il limite non può essere negativo");
            return;
        }
        BigDecimal limit = BankAccountDAO.getAccountById(rootController.user.getUserID()).getMaxTransfer();
        if(v.compareTo(limit) > 0)
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Il limite d'invio prestabilito dall'utente è: " + limit);
        }

        // creating the transaction java object
        int senderAccount = BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID());
        int beneficiaryAccount = BankAccountDAO.getIdAccountByUserId(friend.getUserID());
        Transaction t = new Transaction(senderAccount, beneficiaryAccount, v, note, "donation", 0);


        //tring to insert the transaction in the db
        if(!(TransactionDAO.insertTransaction(t)))
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("errore durante l'invio della donazione");
            return;
        }

        //creating and sending the notifies to each user
        Notify n = new Notify(friend.getUserID(), t.getIdTransaction(), null, note);
        Notify n2 = new Notify(rootController.user.getUserID(), t.getIdTransaction(), null, note);
        NotifyDAO.insertNotify(n);
        NotifyDAO.insertNotify(n2);

        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Transazione effettuata con successo");

        currentFriendController.showChat();
    }


    @Override
    public void initializer()
    {

        errorLabel.setText("");
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
                Node friendContact= loader.load();
                FriendContactController controller = loader.getController();
                controller.setRootController(rootController);
                controller.friendsPageController = this;
                try {controller.friendUsernameLabel.setText(UserDAO.getUserByUserID(id).getUsername());}
                catch (SQLException e)
                {
                    System.err.println("error during loading a friend name from db" + e.getMessage());
                    e.printStackTrace();
                }
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
