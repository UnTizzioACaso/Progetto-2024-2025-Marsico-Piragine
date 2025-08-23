package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.RootWindowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Register1Controller extends BranchPageController {

    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml"); //loading next registerer page, is triggered by the enterRegisterButton's on action event
    }
}

