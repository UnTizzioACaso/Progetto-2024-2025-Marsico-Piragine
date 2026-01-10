package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

public class LoginController extends BranchController {

    // TextField for the user's email input
    @FXML
    private TextField emailLoginTextField;

    @FXML
    private GridPane loginRootGridPane;

    // PasswordField used when the password is hidden
    @FXML
    private PasswordField passwordLoginPasswordField;

    // TextField used when the password is shown in plain text
    @FXML
    private TextField passwordLoginTextField;

    // Label used to display login error messages
    @FXML
    private Label accessErrorMessageLabel;

    // Loads the first registration page
    @FXML
    public void loadRegisterPage() // switching to the register section
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register1.fxml"); // loading first register page
    }

    // Attempts to log the user in and load the home page
    @FXML
    public void loadHomePage() throws SQLException // giving access to the homepage
    {
        // Check if the password is currently shown in plain text
        if(passwordLoginPasswordField.isDisabled())
        {
            // Login check using the visible password TextField
            if (UserDAO.loginCheck(emailLoginTextField.getText(), passwordLoginTextField.getText()))
            {
                // Retrieve the logged-in user from the database
                rootController.user = UserDAO.getUserByEmail(emailLoginTextField.getText());

                // Load application UI components after successful login
                rootController.loadSideBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml"); // loading the sidebar
                rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml"); // loading the topbar
                rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"); // loading the home page
            }

            // Display error message if login fails
            {
                accessErrorMessageLabel.setText("credenziali errate riprova"); // error message for invalid credentials
            }
        }
        else
        {
            // Login check using the hidden PasswordField
            if (UserDAO.loginCheck(emailLoginTextField.getText(), passwordLoginPasswordField.getText()))
            {
                // Retrieve the logged-in user from the database
                rootController.user = UserDAO.getUserByEmail(emailLoginTextField.getText());

                // Setting correct theme
                ThemeManager.applyTheme(loginRootGridPane.getScene(), rootController.user.getTheme());

                // Load application UI components after successful login
                rootController.loadSideBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml"); // loading the sidebar
                rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml"); // loading the topbar
                rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"); // loading the home page
            }

            // Display error message if login fails
            {
                accessErrorMessageLabel.setText("credenziali errate riprova"); // error message for invalid credentials
            }
        }
    }

    // Toggles password visibility between hidden and plain text
    @FXML
    public void showPassword()
    {
        // If the password is currently hidden
        if(passwordLoginTextField.isDisabled())
        {
            // Show password as plain text
            passwordLoginTextField.setDisable(false);
            passwordLoginTextField.setVisible(true);

            // Hide PasswordField
            passwordLoginPasswordField.setDisable(true);
            passwordLoginPasswordField.setVisible(false);

            // Copy password value to the visible TextField
            passwordLoginTextField.setText(passwordLoginPasswordField.getText());
        }
        else
        {
            // Hide plain text password
            passwordLoginTextField.setDisable(true);
            passwordLoginTextField.setVisible(false);

            // Show PasswordField
            passwordLoginPasswordField.setDisable(false);
            passwordLoginPasswordField.setVisible(true);

            // Copy password value back to the PasswordField
            passwordLoginPasswordField.setText(passwordLoginTextField.getText());
        }
    }
}
