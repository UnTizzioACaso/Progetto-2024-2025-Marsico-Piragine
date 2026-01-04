package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CreateCardPopupController extends BranchController
{
    @FXML
    public VBox cardSlotVbox;

    @FXML
    public void initialize()
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            cardSlotVbox.getChildren().add(cardLoader.load());
            CardController controller = cardLoader.getController();
            controller.setRootController(rootController);

        }
        catch (IOException e)
        {
            System.err.println("error loading the createCardPopup" + e.getMessage());
            e.printStackTrace();
        }

    }
}
