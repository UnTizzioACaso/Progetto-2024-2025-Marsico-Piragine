package bankapp.progetto20242025piragine.controller.page;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class LoginController extends BranchPageController {
    @FXML
    GridPane loginGridPane;

    @FXML
    public void loadRegisterPage() //loading the register page n.2,
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register1.fxml");
    }
}
