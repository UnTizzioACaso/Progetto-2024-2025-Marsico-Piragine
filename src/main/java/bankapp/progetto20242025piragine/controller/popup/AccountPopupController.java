package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private AnchorPane accountPopupRoot;

    // Fills the popup with the correct user data and current theme
    @FXML
    public void initialize() {
        // Display user's email
        emailPopupAccountLabel.setText(CurrentSession.getLoggedUser().getEmail());

        // Display user's full name
        nameSurnameAccountPopupLabel.setText(CurrentSession.getLoggedUser().getFirstName() + " " + CurrentSession.getLoggedUser().getLastName());

        // Update radio button and theme label based on user's theme
        if (CurrentSession.getLoggedUser().getTheme().equals("light")) {
            themeColorAccountPopupRadioButton.setSelected(false);
        } else if (CurrentSession.getLoggedUser().getTheme().equals("dark")) {
            themeColorAccountPopupRadioButton.setSelected(true);
        }

        // Update theme color label based on radio button
        themeColorAccountPopupLabel.setText(themeColorAccountPopupRadioButton.isSelected() ? "Scuro" : "Chiaro");


    }


    @FXML
    private void loadAccountSettingsPage()
    {
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/bankAccountSettingsPage.fxml");
        ((Stage) accountPopupRoot.getScene().getWindow()).close();
    }


    // Handles theme switching when the radio button is toggled
    @FXML
    private void toggleTheme()
    {
        // Update the theme label text based on the selected theme
        themeColorAccountPopupLabel.setText(themeColorAccountPopupRadioButton.isSelected() ? "Scuro" : "Chiaro");
        String themeColor = themeColorAccountPopupRadioButton.isSelected() ? "dark" : "light";
        CurrentSession.getLoggedUser().setTheme(themeColor);

        try
        {
            UserDAO.updateUserTheme(CurrentSession.getLoggedUser().getUserID(), themeColor);
        }
        catch (SQLException e)
        {
            System.err.println("error during updating" + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Apply the selected theme to the popup window
        ThemeManager.applyTheme(accountPopupRoot.getScene(), themeColor);

        // Apply the selected theme to the main application window
        ThemeManager.applyTheme(CurrentSession.getRootController().rootWindow.getScene(), themeColor);
    }

    @FXML
    public void closePopup()
    {
        ((Stage) accountPopupRoot.getScene().getWindow()).close();
    }
}
