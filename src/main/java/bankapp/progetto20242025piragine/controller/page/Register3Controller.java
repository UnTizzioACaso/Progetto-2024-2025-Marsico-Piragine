package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.TopbarController;
import javafx.fxml.FXML;

public class Register3Controller extends BranchController
{
    @FXML
    public void completeRegistration()
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"); //loading the home page
        rootController.loadSideBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml"); //loading the sidebar
        rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml"); //loading the topbar
    }
}
