package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;

public class Register1Controller extends BranchController {

    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml"); //loading next registerer page, is triggered by the enterRegisterButton's on action event
    }
}

