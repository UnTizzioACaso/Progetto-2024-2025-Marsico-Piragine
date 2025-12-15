package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Register3Controller extends RegisterController {

    @FXML
    TextField emailRegisterTextField;

    @FXML
    TextField usernameRegisterTextField;

    @FXML
    TextField cellphoneRegisterTextField1;

    @FXML
    PasswordField passwordTextField;

    @FXML
    PasswordField passwordConfirmTextField;

    @FXML
    Label accessErrorMessageLabel;

    @FXML
    public void completeRegistration() throws SQLException //loads the user in the db an automaticaly logins in to the app
    {


        if (emailRegisterTextField.getText().isEmpty() || usernameRegisterTextField.getText().isEmpty() || cellphoneRegisterTextField1.getText().isEmpty() || passwordTextField.getText().isEmpty() || passwordConfirmTextField.getText().isEmpty()) //checking if all forms are compiled
        {

            accessErrorMessageLabel.setText("Tutti i campi devono essere compilati!"); //giving message error
            return; //stopping the code
        }

        if (!passwordTextField.getText().equals(passwordConfirmTextField.getText()))
        {
            accessErrorMessageLabel.setText("Le password non coincidono!"); //giving message error
            return; //stopping the code
        }

        //if (!(passwordTextField.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[^a-zA-Z0-9]).{8,}$")))
        //{
        //    accessErrorMessageLabel.setText("la password deve avere una maiuscola, una minuscola, un numero, un simbolo e deve essere lunga almeno 8 caratteri!"); //giving message error
        //    return; //stopping the code
        //}

        user.setEmail(emailRegisterTextField.getText()); //taking the email from the rispective form to the user object
        user.setUsername(usernameRegisterTextField.getText()); //taking the username from the rispective form to the user object
        user.setPhoneNumber(cellphoneRegisterTextField1.getText()); //taking the phone number from the rispective form to the user object
        PasswordUtil passwordUtil = new PasswordUtil(); // creating the password utility object for hashing the password
        user.setPasswordHash( passwordUtil.hashPassword(passwordTextField.getText())); //hashing the password and taking it to the user object


        try {
            boolean result = UserDAO.registerUser(user); //prova a registrare l'utente nel DB
            if (!result) {
                accessErrorMessageLabel.setText("Errore durante la registrazione."); //errore generico
                return;
            }
        } catch (SQLException e) {
            String errorMessage = e.getMessage();

            // Verifica se l'errore è causato dalla violazione del vincolo UNIQUE (username già esistente)
            if (errorMessage.contains("UNIQUE constraint failed") && errorMessage.contains("username")) {
                accessErrorMessageLabel.setText("Username già esistente. Scegli un altro username.");
            }
            else if (errorMessage.contains("UNIQUE constraint failed") && errorMessage.contains("email")) {
                accessErrorMessageLabel.setText("email già esistente. Login?");
            }
            else {
                accessErrorMessageLabel.setText("Errore nel database: " + errorMessage); //messaggio errore generale
            }
            return;
        }
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"); //loading home page
    }
}

