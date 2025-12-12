package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
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
    public void completeRegistration() throws SQLException {

        // ----- VALIDAZIONE -----
        if (emailRegisterTextField.getText().isEmpty()
                || usernameRegisterTextField.getText().isEmpty()
                || cellphoneRegisterTextField1.getText().isEmpty()
                || passwordTextField.getText().isEmpty()
                || passwordConfirmTextField.getText().isEmpty()) {

            accessErrorMessageLabel.setText("Tutti i campi devono essere compilati!");
            return;
        }

        if (!passwordTextField.getText().equals(passwordConfirmTextField.getText())) {
            accessErrorMessageLabel.setText("Le password non coincidono!");
            return;
        }

        // ----- COPIA DATI -----
        user.setEmail(emailRegisterTextField.getText());
        user.setUsername(usernameRegisterTextField.getText());
        user.setPhoneNumber(cellphoneRegisterTextField1.getText());
        user.setPasswordHash(passwordTextField.getText());

        // ----- SALVATAGGIO NEL DB -----
        UserDAO dao = new UserDAO();
        boolean result = UserDAO.registerUser(user);

        if (!result) {
            accessErrorMessageLabel.setText("Errore durante la registrazione.");
            return;
        }

        // ----- TORNA ALLA LOGIN -----
        rootController.loadPage(
                "/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"
        );
    }
}

