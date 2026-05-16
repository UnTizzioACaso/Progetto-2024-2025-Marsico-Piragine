package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController extends BranchController {

    @FXML
    public Button accountPopupButton;

    @FXML
    public Button settingsButton;

    @FXML
    public Button homeButton;

    @FXML
    void openAccountPopup() //opening the account popup
    {
        PopupCreator.showPopup("Account", "/bankapp/progetto20242025piragine/fxml/popup/accountPopup.fxml", 420, 240);
    }

    @FXML
    public void loadHomePage() {
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml");
    }

    @FXML
    public void loadCardsPage() {

        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/cardPage.fxml");
    }

    @FXML
    public void loadFriendsPage()
    {
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/friendsPage.fxml");
    }

}