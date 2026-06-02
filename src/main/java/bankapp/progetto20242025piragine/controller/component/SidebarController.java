package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController extends BranchController {

    @FXML
    private Button accountPopupButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button homeButton;

    @FXML  //opening the account popup
    private void openAccountPopup() {PopupCreator.showPopup("Account", "/bankapp/progetto20242025piragine/fxml/popup/accountPopup.fxml", 420, 240);}

    @FXML
    private void loadHomePage() {CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml");}

    @FXML
    private void loadCardsPage() {CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/cardPage.fxml");}

    @FXML
    private void loadFriendsPage() {CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/friendsPage.fxml");}

}