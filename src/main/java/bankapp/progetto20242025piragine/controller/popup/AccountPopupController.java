package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class AccountPopupController extends BranchController {

    @FXML
    private Label emailPopupAccountLabel, themeColorAccountPopupLabel, nameSurnameAccountPopupLabel;

    @FXML
    public RadioButton themeColorAccountPopupRadioButton;

    @FXML
    private AnchorPane root;


    /**
     * Mostra i valori dell’utente e il tema corretto
     */
    public void showCorrectValues() {
        emailPopupAccountLabel.setText(rootController.user.getEmail());
        nameSurnameAccountPopupLabel.setText(rootController.user.getFirstName() + " " + rootController.user.getLastName());

        boolean dark = "Scuro".equalsIgnoreCase(rootController.user.getTheme());
        themeColorAccountPopupRadioButton.setSelected(dark);
        themeColorAccountPopupLabel.setText(dark ? "Scuro" : "Chiaro");

        // Applica il tema globale alla scena della popup
        ThemeManager.setDarkMode(root.getScene(), dark);
    }

    /**
     * Chiamato quando l’utente clicca il RadioButton per cambiare tema
     */
    @FXML
    private void toggleTheme() {
        // Cambia tema globale e applica alla scena della popup (e alla finestra principale)
        ThemeManager.setDarkMode(root.getScene(), themeColorAccountPopupRadioButton.isSelected());
        ThemeManager.setDarkMode(rootController.rootWindow.getScene(), themeColorAccountPopupRadioButton.isSelected());


        // Aggiorna la label
        themeColorAccountPopupLabel.setText(themeColorAccountPopupRadioButton.isSelected() ? "Scuro" : "Chiaro");
    }
}
