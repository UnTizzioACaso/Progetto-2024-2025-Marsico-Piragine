package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Register3Controller extends BranchController {

    @FXML
    private TextField emailRegisterTextField;

    @FXML
    private TextField usernameRegisterTextField;

    @FXML
    private TextField cellphoneRegisterTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField passwordConfirmPasswordField;

    @FXML
    private Label errorMessageLabel;

    private static final String USERNAME_REGEX = "^[a-z0-9_]{3,30}$";

    private static final String EMAIL_REGEX = "^(?!.*\\.\\.)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final String PHONE_REGEX = "^[0-9]{10}$";

    @FXML
    public void openCreatePinPopup()
    {
        if (emailRegisterTextField.getText().isEmpty() || usernameRegisterTextField.getText().isEmpty() || cellphoneRegisterTextField.getText().isEmpty() || passwordPasswordField.getText().isEmpty() || passwordConfirmPasswordField.getText().isEmpty())
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!");
            return;
        }

        String email = emailRegisterTextField.getText();
        String username = usernameRegisterTextField.getText();
        String phone = cellphoneRegisterTextField.getText();

        if (!username.matches(USERNAME_REGEX))
        {
            errorMessageLabel.setText("L'username può contenere solo lettere minuscole, numeri e '_'");
            return;
        }

        if (!email.matches(EMAIL_REGEX))
        {
            errorMessageLabel.setText("Email non valida!");
            return;
        }

        if (!phone.matches(PHONE_REGEX))
        {
            errorMessageLabel.setText("Numero di telefono non valido!");
            return;
        }

        try
        {
            if(UserDAO.existUserByUsername(username))
            {
                errorMessageLabel.setText("Username già in uso!");
                return;
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during username research " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try
        {
            if(UserDAO.existUserByPhone(phone))
            {
                errorMessageLabel.setText("Numero di telefono già in uso!");
                return;
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during phone number research " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try
        {
            if(UserDAO.existUserByEmail(email))
            {
                errorMessageLabel.setText("Email già in uso!");
                return;
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during email research " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (!passwordPasswordField.getText().equals(passwordConfirmPasswordField.getText()))
        {
            errorMessageLabel.setText("Le password non coincidono!");
            return;
        }

        if (!passwordPasswordField.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$"))
        {
            errorMessageLabel.setText("La password deve contenere almeno una maiuscola, una minuscola, un numero, un simbolo ed essere lunga almeno 8 caratteri");
            return;
        }

        CurrentSession.getLoggedUser().setEmail(email);
        CurrentSession.getLoggedUser().setUsername(username);
        CurrentSession.getLoggedUser().setPhoneNumber(phone);
        CurrentSession.getLoggedUser().setPasswordHash(PasswordUtil.hashPassword(passwordPasswordField.getText()));

        PopupCreator.showPopup("Crea un pin", "/bankapp/progetto20242025piragine/fxml/popup/createPinPopup.fxml", 314, 240);
    }

    @FXML
    public void loadLogin()
    {
        CurrentSession.setLoggedUser(new User());
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }
}
