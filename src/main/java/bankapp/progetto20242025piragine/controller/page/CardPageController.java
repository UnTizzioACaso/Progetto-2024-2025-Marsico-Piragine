package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CardPageController extends BranchController
{
    @FXML
    VBox cardsContainerVBox;

    @Override
    public void initializer()
    {

        try
        {
            FXMLLoader cardRectangleLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/creditCardRectangle.fxml"));
            Node cardRectangle = cardRectangleLoader.load();
            BranchController controller = cardRectangleLoader.getController();
            controller.setRootController(rootController);
            controller.initializer();
            cardsContainerVBox.getChildren().add(cardRectangle);
        }
        catch (IOException e) {
            System.err.println("error loading the account popup" + e.getMessage());
            e.printStackTrace();
        }

    }
}
