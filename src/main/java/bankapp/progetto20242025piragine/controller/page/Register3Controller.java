package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.AccountPopupController;
import bankapp.progetto20242025piragine.controller.popup.CreatePinPopupController;
import bankapp.progetto20242025piragine.db.BankAccount;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Register3Controller extends BranchController {

    // TextField for user's email
    @FXML
    TextField emailRegisterTextField;

    // TextField for user's username
    @FXML
    TextField usernameRegisterTextField;

    // TextField for user's cellphone number
    @FXML
    TextField cellphoneRegisterTextField;

    // PasswordField for password input
    @FXML
    PasswordField passwordPasswordField;

    // PasswordField for password confirmation
    @FXML
    PasswordField passwordConfirmPasswordField;

    // Label used to display validation error messages
    @FXML
    Label errorMessageLabel;

    // Validates user credentials and opens the PIN creation popup
    @FXML
    public void openCreatePinPopup() // creates the user object and loads the createPinPopup
    {
        // Check if all required fields are filled
        if (emailRegisterTextField.getText().isEmpty() || usernameRegisterTextField.getText().isEmpty() || cellphoneRegisterTextField.getText().isEmpty() || passwordPasswordField.getText().isEmpty() || passwordConfirmPasswordField.getText().isEmpty())
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!"); // error message if fields are empty
            return; // stop execution
        }

        // Check if password and confirmation match
        if (!passwordPasswordField.getText().equals(passwordConfirmPasswordField.getText()))
        {
            errorMessageLabel.setText("Le password non coincidono!"); // error message
            return; // stop execution
        }

        // Check password complexity requirements
        if (!(passwordPasswordField.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$")))
        {
            errorMessageLabel.setText("la password deve avere una maiuscola, una minuscola, un numero, un simbolo e deve essere lunga almeno 8 caratteri!"); // error message for invalid password
            return; // stop execution
        }

        // Save credentials into the shared User object
        rootController.user.setEmail(emailRegisterTextField.getText()); // set user's email
        rootController.user.setUsername(usernameRegisterTextField.getText()); // set user's username
        rootController.user.setPhoneNumber(cellphoneRegisterTextField.getText()); // set user's phone number
        rootController.user.setPasswordHash(PasswordUtil.hashPassword(passwordPasswordField.getText())); // hash and store user's password

        try {
            // Load the create PIN popup FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/createPinPopup.fxml"));
            Parent root = loader.load(); // create the popup root node

            // Retrieve and configure the popup controller
            CreatePinPopupController controller = loader.getController();
            controller.setRootController(rootController); // pass root controller reference

            // Configure popup stage
            Stage popupStage = new Stage(); // create popup window
            popupStage.setTitle("Crea un pin"); // set window title
            popupStage.setMinWidth(315); // set minimum width
            popupStage.setMinHeight(280); // set minimum height
            popupStage.setResizable(false); // disable resizing
            popupStage.initModality(Modality.APPLICATION_MODAL); // block other windows



            popupStage.setScene(new Scene(root)); // set popup scene
            popupStage.showAndWait(); // wait until popup is closed
        }
        catch (IOException e)
        {
            System.err.println("error loading the create pin popup" + e.getMessage()); // log error
            e.printStackTrace();
        }
    }

    // Returns to the login page and resets the User object
    @FXML
    public void loadLogin()
    {
        rootController.user = new User(); // reset user data
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml"); // load login page
    }
}
