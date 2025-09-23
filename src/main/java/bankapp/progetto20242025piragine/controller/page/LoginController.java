package bankapp.progetto20242025piragine.controller.page;


import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class LoginController extends BranchController {
    @FXML
    GridPane loginGridPane;

    @FXML
    public void loadRegisterPage() //loading the register page n.2,
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register1.fxml");
    }
}
