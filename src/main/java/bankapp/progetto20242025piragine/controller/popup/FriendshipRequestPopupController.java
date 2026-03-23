package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.sql.SQLException;

public class FriendshipRequestPopupController extends BranchController {
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

    // Colori dinamici in base al tema dell'utente
    private String getBgInactive() {
        return rootController.user.getTheme().equalsIgnoreCase("dark") ? "#4a4a4a" : "white";
    }

    private String getTextInactive() {
        return rootController.user.getTheme().equalsIgnoreCase("dark") ? "white" : "#2b2b2b";
    }

    @Override
    public void initializer() {
        ThemeManager.applyTheme(errorLabel.getScene(), rootController.user.getTheme());
        // Impostiamo lo stato iniziale (Username attivo di default)
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

        // Visibilità TextField
        searchByUsernameField.setVisible(user);
        searchByEmailField.setVisible(email);
        searchByPhoneNumberField.setVisible(phone);

        // Reset errore quando l'utente cambia modalità di ricerca
        errorLabel.setText("");

        // Aggiornamento stili ToggleButtons
        toggleUsername.setStyle("-fx-background-color: " + (user ? "red" : bg) + "; -fx-text-fill: " + (user ? "white" : txt) + "; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: " + (email ? "red" : bg) + "; -fx-text-fill: " + (email ? "white" : txt) + "; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: " + (phone ? "red" : bg) + "; -fx-text-fill: " + (phone ? "white" : txt) + "; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
    }

    @FXML
    public void findHypotheticalFriend() {
        user2 = null; // Reset fondamentale: cancella l'utente trovato nella ricerca precedente
        errorLabel.setText("");

        try {
            if (searchByUsernameField.isVisible()) {
                String val = searchByUsernameField.getText();
                System.out.println("Ricerca per Username: " + val);
                user2 = UserDAO.getUserByUsername(val);
            }
            else if (searchByEmailField.isVisible()) {
                String val = searchByEmailField.getText();
                System.out.println("Ricerca per Email: " + val);
                user2 = UserDAO.getUserByEmail(val);
            }
            else if (searchByPhoneNumberField.isVisible()) {
                String val = searchByPhoneNumberField.getText();
                System.out.println("Ricerca per Telefono: " + val);
                user2 = UserDAO.getUserByCellphone(val);
            }
        } catch (SQLException e) {
            System.err.println("Errore Database durante la ricerca: " + e.getMessage());
            errorLabel.setText("Errore di connessione");
            return;
        }

        // Se dopo la ricerca user2 è ancora null, il DAO non ha trovato nulla
        if (user2 == null) {
            errorLabel.setText("Utente non trovato");
        } else {
            sendRequest();
        }
    }

    private void sendRequest() {
        // Controllo se l'utente trovato sono io (confronto ID)
        if (user2.getUserID() == rootController.user.getUserID()) {
            errorLabel.setText("L'utente inserito è il tuo");
            return;
        }

        try {
            // Controllo blocchi
            if (BlockDAO.isBlocked(user2.getUserID(), rootController.user.getUserID())) {
                errorLabel.setText("Utente non trovato (blocco)");
                return;
            }

            // Invio richiesta
            FriendRequest request = new FriendRequest(rootController.user.getUserID(), user2.getUserID());
            FriendRequestDAO.sendRequest(request);

            // Gestione notifiche
            Notify n = new Notify();
            n.setRead(false);

            // Prendiamo l'ID della richiesta appena creata
            var pending = FriendRequestDAO.getPendingRequests(user2.getUserID());
            if (!pending.isEmpty()) {
                n.setIdFriendRequest(pending.getFirst().getIdRequest());

                // Notifica al mittente
                n.setUserId(rootController.user.getUserID());
                NotifyDAO.insertNotify(n);

                // Notifica al destinatario
                n.setUserId(user2.getUserID());
                NotifyDAO.insertNotify(n);

                errorLabel.setText("Richiesta inviata correttamente");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'invio della richiesta: " + e.getMessage());
            errorLabel.setText("Errore nell'invio della richiesta");
        }
    }
}

