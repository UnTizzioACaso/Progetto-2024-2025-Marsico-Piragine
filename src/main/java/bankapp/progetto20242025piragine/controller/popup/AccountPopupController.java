package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class AccountPopupController extends BranchController {

    // Labels used to display user's email, selected theme and full name
    @FXML
    private Label emailPopupAccountLabel, themeColorAccountPopupLabel, nameSurnameAccountPopupLabel;

    // RadioButton used to toggle between light and dark theme
    @FXML
    public RadioButton themeColorAccountPopupRadioButton;

    // Root pane of the popup, used to apply theme changes
    @FXML
    private AnchorPane root;

    // Fills the popup with the correct user data and current theme
    public void showCorrectValues() {
        // Display user's email
        emailPopupAccountLabel.setText(rootController.user.getEmail());

        // Display user's full name
        nameSurnameAccountPopupLabel.setText(rootController.user.getFirstName() + " " + rootController.user.getLastName());

        // Check if the user's theme is dark
        boolean dark = "Scuro".equalsIgnoreCase(rootController.user.getTheme());

        // Update radio button and theme label based on user's theme
        themeColorAccountPopupRadioButton.setSelected(dark);
        themeColorAccountPopupLabel.setText(dark ? "Scuro" : "Chiaro");

        // Apply the selected theme to the popup scene
        ThemeManager.setDarkMode(root.getScene(), dark);
    }

    // Handles theme switching when the radio button is toggled
    @FXML
    private void toggleTheme() {

        // Apply the selected theme to the popup window
        ThemeManager.setDarkMode(root.getScene(), themeColorAccountPopupRadioButton.isSelected());

        // Apply the selected theme to the main application window
        ThemeManager.setDarkMode(rootController.rootWindow.getScene(), themeColorAccountPopupRadioButton.isSelected());

        // Update the theme label text based on the selected theme
        themeColorAccountPopupLabel.setText(themeColorAccountPopupRadioButton.isSelected() ? "Scuro" : "Chiaro");
    }
}
