package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Register1Controller extends BranchController {

    @FXML
    private ChoiceBox sexChoiceBox;

    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        if (!(sexChoiceBox.getValue().equals("- Sesso -"))) //checking if any sex is selected (except "- Sesso -")
        {
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml"); //loading next registerer page, is triggered by the enterRegisterButton's on action event
        }
    }

    @FXML
    public void initialize() //initialization the page
    {
        sexChoiceBox.getSelectionModel().selectFirst(); //selecting the "- Sesso -" option of sexChoiceBox
    }
}

