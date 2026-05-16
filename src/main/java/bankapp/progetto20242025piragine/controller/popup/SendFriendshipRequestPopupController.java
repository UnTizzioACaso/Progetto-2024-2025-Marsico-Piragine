package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.FriendRequest;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SendFriendshipRequestPopupController extends BranchController {
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

    private User user2 = null;

    // Dynamic color by user
    private String getBgInactive() {
        return CurrentSession.getLoggedUser().getTheme().equalsIgnoreCase("dark") ? "#4a4a4a" : "white";
    }

    private String getTextInactive() {
        return CurrentSession.getLoggedUser().getTheme().equalsIgnoreCase("dark") ? "white" : "#2b2b2b";
    }

    @FXML
    public void initialize() {
        switchToUsername();
    }

    @FXML
    public void switchToUsername() {
        updateUI(true, false, false);
    }

    @FXML
    public void switchToEmail() {
        updateUI(false, true, false);
    }

    @FXML
    public void switchToPhoneNumber() {
        updateUI(false, false, true);
    }

    // Metodo centralizzato per gestire visibilità, stili e reset errori
    private void updateUI(boolean user, boolean email, boolean phone) {
        String bg = getBgInactive();
        String txt = getTextInactive();

        // Visibility TextField
        searchByUsernameField.setVisible(user);
        searchByEmailField.setVisible(email);
        searchByPhoneNumberField.setVisible(phone);

        // Reset error label
        errorLabel.setText("");

        // updating ToggleButtons
        toggleUsername.setStyle("-fx-background-color: " + (user ? "red" : bg) + "; -fx-text-fill: " + (user ? "white" : txt) + "; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: " + (email ? "red" : bg) + "; -fx-text-fill: " + (email ? "white" : txt) + "; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: " + (phone ? "red" : bg) + "; -fx-text-fill: " + (phone ? "white" : txt) + "; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
    }

    @FXML
    public void findHypotheticalFriend() //Finds a user in the db and stores it in the controller
    {
        user2 = null; //resetting the user2
        errorLabel.setText(""); //resetting error label

        //Dynamic three-way query
        try {
            if (searchByUsernameField.isVisible()) {
                String val = searchByUsernameField.getText();
                user2 = UserDAO.getUserByUsername(val);
            }
            else if (searchByEmailField.isVisible()) {
                String val = searchByEmailField.getText();
                user2 = UserDAO.getUserByEmail(val);
            }
            else if (searchByPhoneNumberField.isVisible()) {
                String val = searchByPhoneNumberField.getText();
                user2 = UserDAO.getUserByCellphone(val);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error during : " + e.getMessage());
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Errore nella ricerca");
            return;
        }

        //if after the query user2 is still null
        if (user2 == null)
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Utente non trovato");
            return;
        }

        //sending method, called when user2 is no more null
        sendRequest();

    }

    @FXML
    public void closePopup()
    {
        ((Stage) errorLabel.getScene().getWindow()).close();
    }

    private void sendRequest() //sends the friendship request and notifies in the db
    {
        //filtering if user2 is also user
        if (user2.getUserID() == CurrentSession.getLoggedUser().getUserID())
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("L'utente inserito sei tu");
            return;
        }

        //filtering if user2 blocked user
        if (BlockDAO.isBlocked(user2.getUserID(), CurrentSession.getLoggedUser().getUserID()))
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Utente non trovato");
            return;
        }

        //filtering if there is an existing pending request
        if(FriendRequestDAO.findPendingRequestByUsersIds(CurrentSession.getLoggedUser().getUserID(), user2.getUserID()))
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Hai gia una richiesta in sospeso con questo utente");
            return;
        }

        //filtering if there is an existing pending request
        if(FriendRequestDAO.findPendingRequestByUsersIds(CurrentSession.getLoggedUser().getUserID(), user2.getUserID()))
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Hai gia una richiesta in sospeso con questo utente");
            return;
        }

        //filtering if there is an accepted request
        if(FriendRequestDAO.findAcceptedRequestByUsersIds(CurrentSession.getLoggedUser().getUserID(), user2.getUserID()))
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Sei gia amico con questo utente");
            return;
        }

        //sending friendship request
        FriendRequest request = new FriendRequest(CurrentSession.getLoggedUser().getUserID(), user2.getUserID());
        FriendRequestDAO.sendRequest(request);

        //sending notifies
        Notify n = new Notify(user2.getUserID(), null, request.getIdRequest(), "Richiesta d'amicizia");
        Notify n2 = new Notify(CurrentSession.getLoggedUser().getUserID(), null, request.getIdRequest(), "Richiesta d'amicizia");
        NotifyDAO.insertNotify(n);
        NotifyDAO.insertNotify(n2);

        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Richiesta inviata correttamente");
    }

}


