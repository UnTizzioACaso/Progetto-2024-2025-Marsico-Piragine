package bankapp.progetto20242025piragine.controller.page;

import javafx.fxml.FXML;

public class Register3Controller extends BranchPageController
{
    @FXML
    public void completeRegistration()
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homepage.fxml");
        rootController.loadLeftBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml");
        rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml");
    }
}
