package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreditCardRectangleController extends BranchController
{


    @FXML
    public AnchorPane rectangleAnchorPane;

    @Override
    public void initializer()
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            Node card = cardLoader.load();
            AnchorPane.setBottomAnchor(card, 30.00);
            AnchorPane.setLeftAnchor(card, 30.00);
            BranchController controller = cardLoader.getController();
            controller.setRootController(rootController);
            controller.initializer();
            rectangleAnchorPane.getChildren().add(card);
        }
        catch (IOException e)
        {
            System.err.println("error loading the account popup" + e.getMessage());
            e.printStackTrace();
        }
    }

}
