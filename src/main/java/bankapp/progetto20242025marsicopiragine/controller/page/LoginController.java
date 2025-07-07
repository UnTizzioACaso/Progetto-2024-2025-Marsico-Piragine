package bankapp.progetto20242025marsicopiragine.controller.page;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class LoginController {
    @FXML
    public void loadRegisterPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
    }
}
