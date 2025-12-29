package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Register3Controller extends BranchController {

    @FXML
    TextField emailRegisterTextField;

    @FXML
    TextField usernameRegisterTextField;

    @FXML
    TextField cellphoneRegisterTextField;

    @FXML
    PasswordField passwordPasswordField;

    @FXML
    PasswordField passwordConfirmPasswordField;

    @FXML
    Label errorMessageLabel;

    @FXML
    public void completeRegistration() throws SQLException //loads the user in the db an automaticaly logins in to the app
    {

        if (emailRegisterTextField.getText().isEmpty() || usernameRegisterTextField.getText().isEmpty() || cellphoneRegisterTextField.getText().isEmpty() || passwordPasswordField.getText().isEmpty() || passwordConfirmPasswordField.getText().isEmpty()) //checking if all forms are compiled
        {

            errorMessageLabel.setText("Tutti i campi devono essere compilati!"); //giving message error
            return; //stopping the code
        }

        if (!passwordPasswordField.getText().equals(passwordConfirmPasswordField.getText()))
        {
            errorMessageLabel.setText("Le password non coincidono!"); //giving message error
            return; //stopping the code
        }

        if (!(passwordPasswordField.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$")))
        {
            errorMessageLabel.setText("la password deve avere una maiuscola, una minuscola, un numero, un simbolo e deve essere lunga almeno 8 caratteri!"); //giving message error
            return; //stopping the code
        }

        rootController.user.setEmail(emailRegisterTextField.getText()); //taking the email from the rispective form to the user object
        rootController.user.setUsername(usernameRegisterTextField.getText()); //taking the username from the rispective form to the user object
        rootController.user.setPhoneNumber(cellphoneRegisterTextField.getText()); //taking the phone number from the rispective form to the user object
        PasswordUtil passwordUtil = new PasswordUtil(); // creating the password utility object for hashing the password
        rootController.user.setPasswordHash( passwordUtil.hashPassword(passwordPasswordField .getText())); //hashing the password and taking it to the user object


        boolean result = UserDAO.registerUser(rootController.user); //trying to add the user to the db

        if (!result) //check if user got registered
        {
            errorMessageLabel.setText("Errore durante la registrazione."); //giving message error
            return; //stopping the code
        }


        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"); //loading home page
        rootController.loadSideBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml"); //loading sidebar
        rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml"); //loading topbar
    }

    @FXML
    public void loadLogin()
    {
        rootController.user = new User();
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }

}

