package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.TopbarController;
import javafx.fxml.FXML;

public class Register3Controller extends BranchController
{
    @FXML
    public void completeRegistration()
    {
        rootController.loadLeftBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml");
        rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml");
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml");
    }
}
