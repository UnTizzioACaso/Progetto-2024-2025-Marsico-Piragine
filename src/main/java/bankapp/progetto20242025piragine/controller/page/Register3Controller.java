package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.CreatePinPopupController;
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


        //filtering username
        if (!username.matches(USERNAME_REGEX))
        {
            errorMessageLabel.setText("L'username può contenere solo lettere minuscole, numeri e '_'");
            return;
        }

        //filtering email
        if (!email.matches(EMAIL_REGEX))
        {
            errorMessageLabel.setText("Email non valida!");
            return;
        }

        //filtering phone
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
            if(UserDAO.existUserByPhone(Integer.parseInt(phone)))
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

        rootController.user.setEmail(email);
        rootController.user.setUsername(username);
        rootController.user.setPhoneNumber(phone);
        rootController.user.setPasswordHash(PasswordUtil.hashPassword(passwordPasswordField.getText()));

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/createPinPopup.fxml"));
            Parent root = loader.load();

            CreatePinPopupController controller = loader.getController();
            controller.setRootController(rootController);

            Stage popupStage = new Stage();
            popupStage.setTitle("Crea un pin");
            popupStage.setMinWidth(315);
            popupStage.setMinHeight(280);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);

            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        }
        catch (IOException e)
        {
            System.err.println("error loading the create pin popup" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void loadLogin()
    {
        rootController.user = new User();
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }
}
