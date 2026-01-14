package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.CreatePinPopupController;
import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.DataSourceProvider;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        if (!username.matches("^[a-z0-9_]{3,30}$"))
        {
            errorMessageLabel.setText("L'username può contenere solo lettere minuscole, numeri e '_'");
            return;
        }

        if (email.contains(" ") || email.contains("&") || email.contains("=") || email.contains("\\") || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
        {
            errorMessageLabel.setText("Email non valida!");
            return;
        }

        if (!phone.matches("^\\d{10}$"))
        {
            errorMessageLabel.setText("Numero di telefono non valido!");
            return;
        }

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM User WHERE username = ? LIMIT 1"))
        {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    errorMessageLabel.setText("Username già in uso!");
                    return;
                }
            }
        }
        catch (SQLException e)
        {
            errorMessageLabel.setText("Errore durante la verifica dell'username!");
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
