package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.sql.SQLException;

public class FriendshipRequestController extends BranchController {
    @FXML
    private TextField searchByUsernameField;

    @FXML
    private TextField searchByPhoneNumberField;

    @FXML
    private TextField searchByEmailField;

    @FXML
    private Label errorLabel;

    @FXML
    private ToggleButton toggleUsername;

    @FXML
    private ToggleButton toggleEmail;

    @FXML
    private ToggleButton togglePhoneNumber;


    @FXML
    public void switchToUsername() {
        searchByUsernameField.setVisible(true);
        searchByPhoneNumberField.setVisible(false);
        searchByEmailField.setVisible(false);
        toggleUsername.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
    }


    @FXML
    public void switchToPhoneNumber() {
        searchByUsernameField.setVisible(false);
        searchByPhoneNumberField.setVisible(true);
        searchByEmailField.setVisible(false);
        toggleUsername.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");

    }

    @FXML
    public void switchToEmail() {
        searchByUsernameField.setVisible(false);
        searchByPhoneNumberField.setVisible(false);
        searchByEmailField.setVisible(true);
        toggleUsername.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
    }

    private void sendRequest(User user) //checking if requester has been blocked by requested
    {

        if (user != null)
        {
            errorLabel.setText("Utente non trovato");
            return;
        }
        if(user.equals(rootController.user))
        {
            errorLabel.setText("Utente non trovato");
            return;
        }
        boolean blocked = false;
        try {blocked = BlockDAO.isBlocked(user.getUserID(), rootController.user.getUserID());}
        catch (SQLException e)
        {
            System.err.println("error during checking block " + e.getMessage());
            e.printStackTrace();
        }
        if(blocked)
        {
            errorLabel.setText("Utente non trovato");
            return;
        }
        FriendRequest request = new FriendRequest(rootController.user.getUserID(), user.getUserID());
        try {FriendRequestDAO.sendRequest(request);}
        catch (SQLException e)
        {
            System.err.println("error during sending request " + e.getMessage());
            e.printStackTrace();
        }
        errorLabel.setText("richiesta inviata");
    }

    @FXML
    public void findHypotheticalFriend() //trying to find if the user who will get the request exist
    {
        //Send request with username
        if (searchByUsernameField.isVisible())
        {
            User user = null;
            try {user = UserDAO.getUserByUsername(searchByUsernameField.getText());}
            catch (SQLException e)
            {
                System.err.println("error during user research in db by username" + e.getMessage());
                e.printStackTrace();
            }
            sendRequest(user);
        }

        //Send request with email
        else if (searchByEmailField.isVisible())
        {
            User user = new User();
            try {user = UserDAO.getUserByEmail(searchByEmailField.getText());}
            catch (SQLException e)
            {
                System.err.println("error during user research in db by email " + e.getMessage());
                e.printStackTrace();
            }
            sendRequest(user);
        }

        //Send request with phone number
        else if (searchByPhoneNumberField.isVisible())
        {
            User user = new User();
            try {user = UserDAO.getUserByCellphone(searchByPhoneNumberField.getText());}
            catch (SQLException e)
            {
                System.err.println("error during user research in db by phone number " + e.getMessage());
                e.printStackTrace();
            }
            sendRequest(user);
        }
    }
}

