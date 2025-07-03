package bankapp.progetto20242025marsicopiragine.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RootWindowController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}