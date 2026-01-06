package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class AccountPopupController extends BranchController {

    // Label della popup
    @FXML
    private Label emailPopupAccountLabel, themeColorAccountPopupLabel, nameSurnameAccountPopupLabel;

    // RadioButton per il tema
    @FXML
    public RadioButton themeColorAccountPopupRadioButton;

    // Nodo root della popup
    @FXML
    private AnchorPane root;

    // Stato corrente del tema
    private boolean darkMode = false;

    /**
     * Imposta i valori corretti nelle label quando la popup viene aperta
     */
    public void showCorrectValues() {
        // dati utente
        emailPopupAccountLabel.setText(rootController.user.getEmail());
        nameSurnameAccountPopupLabel.setText(rootController.user.getFirstName() + " " + rootController.user.getLastName());

        // tema salvato dell'utente
        String theme = rootController.user.getTheme();
        themeColorAccountPopupLabel.setText(theme);

        if ("Scuro".equalsIgnoreCase(theme)) {
            darkMode = true;
            themeColorAccountPopupRadioButton.setSelected(true);
            applyTheme(true);
        } else {
            darkMode = false;
            themeColorAccountPopupRadioButton.setSelected(false);
            applyTheme(false);
        }
    }

    /**
     * Metodo chiamato quando l'utente clicca il RadioButton
     */
    @FXML
    private void toggleTheme() {
        darkMode = themeColorAccountPopupRadioButton.isSelected();
        applyTheme(darkMode);
    }

    /**
     * Applica il CSS alla popup e aggiorna anche lo sfondo inline se necessario
     * @param dark true = tema scuro, false = tema chiaro
     */
    private void applyTheme(boolean dark) {
        // Aggiorna Label
        themeColorAccountPopupLabel.setText(dark ? "Scuro" : "Chiaro");

        // Aggiorna la scena con il CSS corretto
        if (root.getScene() != null) {
            root.getScene().getStylesheets().clear();
            String cssPath = dark
                    ? "/bankapp/progetto20242025piragine/css/dark.css"
                    : "/bankapp/progetto20242025piragine/css/light.css";
            root.getScene().getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        }

        // Aggiorna sfondo inline del root per sicurezza
        root.setStyle(dark
                ? "-fx-background-color: #2C2C2C; -fx-background-radius: 12;"
                : "-fx-background-color: white; -fx-background-radius: 12;"
        );
    }

}
