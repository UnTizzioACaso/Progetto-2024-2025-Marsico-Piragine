package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

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

        // Update radio button and theme label based on user's theme
        if(rootController.user.getTheme().equals("light")) {themeColorAccountPopupRadioButton.setSelected(false);}
        if(rootController.user.getTheme().equals("dark")) {themeColorAccountPopupRadioButton.setSelected(true);}
        themeColorAccountPopupLabel.setText(themeColorAccountPopupRadioButton.isSelected() ? "Scuro" : "Chiaro");

        // Apply the selected theme to the popup scene
        ThemeManager.applyTheme(root.getScene(), rootController.user.getTheme());
    }

    // Handles theme switching when the radio button is toggled
    @FXML
    private void toggleTheme()
    {

        // Update the theme label text based on the selected theme
        themeColorAccountPopupLabel.setText(themeColorAccountPopupRadioButton.isSelected() ? "Scuro" : "Chiaro");
        String themeColor = themeColorAccountPopupRadioButton.isSelected() ? "dark" : "light";
        rootController.user.setTheme(themeColor);

        try
        {
            UserDAO.updateUserTheme(rootController.user.getUserID(), themeColor);
        }
        catch (SQLException e)
        {
            System.err.println("error during updating" + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Apply the selected theme to the popup window
        ThemeManager.applyTheme(root.getScene(), themeColor);

        // Apply the selected theme to the main application window
        ThemeManager.applyTheme(rootController.rootWindow.getScene(), themeColor);

    }
}
