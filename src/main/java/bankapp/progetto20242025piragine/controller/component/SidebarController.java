package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.AccountPopupController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

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
        rootController.showPopUp("Account", "/bankapp/progetto20242025piragine/fxml/popup/accountPopup.fxml", 420, 300);
    }

    @FXML
    public void loadHomePage() {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml");
    }

    @FXML
    public void loadCardsPage() {

        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/cardPage.fxml");
    }

    @FXML
    public void loadFriendsPage()
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/friendsPage.fxml");
    }

}