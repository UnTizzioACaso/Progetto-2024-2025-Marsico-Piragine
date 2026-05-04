package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Paint;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.BlurType;
import javafx.scene.paint.Color;

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
    @FXML
    public Button closeFriendshipRequestPopup;

    private User user2 = null;

    private String getBgInactive() {
        return rootController.user.getTheme().equalsIgnoreCase("dark") ? "#4a4a4a" : "white";
    }

    private String getTextInactive() {
        return rootController.user.getTheme().equalsIgnoreCase("dark") ? "white" : "#2b2b2b";
    }

    @Override
    public void initializer() {
        ThemeManager.applyTheme(errorLabel.getScene(), rootController.user.getTheme());
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

    private void updateUI(boolean user, boolean email, boolean phone) {
        String bg = getBgInactive();
        String txt = getTextInactive();

        InnerShadow effect = new InnerShadow();
        effect.setBlurType(BlurType.THREE_PASS_BOX);
        effect.setChoke(0.0);
        effect.setWidth(0.0);
        effect.setHeight(10.5);
        effect.setRadius(2.13);
        effect.setOffsetX(0.0);
        effect.setOffsetY(-10.0);
        effect.setColor(new Color(0, 0, 0, 0.15));

        searchByUsernameField.setVisible(user);
        searchByEmailField.setVisible(email);
        searchByPhoneNumberField.setVisible(phone);

        errorLabel.setText("");

        toggleUsername.setStyle("-fx-background-color: " + (user ? "red" : bg) + "; -fx-text-fill: " + (user ? "white" : txt) + "; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");
        toggleUsername.setEffect(user ? effect : null);

        toggleEmail.setStyle("-fx-background-color: " + (email ? "red" : bg) + "; -fx-text-fill: " + (email ? "white" : txt) + "; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        toggleEmail.setEffect(email ? effect : null);

        togglePhoneNumber.setStyle("-fx-background-color: " + (phone ? "red" : bg) + "; -fx-text-fill: " + (phone ? "white" : txt) + "; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
        togglePhoneNumber.setEffect(phone ? effect : null);
    }

    @FXML
    public void findHypotheticalFriend()
    {
        user2 = null;
        errorLabel.setText("");

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

        if (user2 == null)
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Utente non trovato");
            return;
        }

        sendRequest();
    }

    private void sendRequest()
    {
        if (user2.getUserID() == rootController.user.getUserID())
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("L'utente inserito sei tu");
            return;
        }

        if (BlockDAO.isBlocked(user2.getUserID(), rootController.user.getUserID()))
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Utente non trovato");
            return;
        }

        if(FriendRequestDAO.findRequestByUsersIds(rootController.user.getUserID(), user2.getUserID()))
        {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Hai gia una richiesta in sospeso con questo utente");
            return;
        }

        FriendRequest request = new FriendRequest(rootController.user.getUserID(), user2.getUserID());
        FriendRequestDAO.sendRequest(request);

        Notify n = new Notify(user2.getUserID(), null, request.getIdRequest(), "Richiesta d'amicizia");
        Notify n2 = new Notify(rootController.user.getUserID(), null, request.getIdRequest(), "Richiesta d'amicizia");
        NotifyDAO.insertNotify(n);
        NotifyDAO.insertNotify(n2);

        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Richiesta inviata correttamente");
    }

    @FXML
    public void closePopup()
    {
        closeFriendshipRequestPopup.getScene().getWindow().hide();
    }
}