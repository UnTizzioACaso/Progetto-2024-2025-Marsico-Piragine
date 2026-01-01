package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreditCardRectangleController extends BranchController
{
    @FXML
    public Label limitValueLabel;

    @FXML
    public AnchorPane rectangleAnchorPane;


    public void fill(Card card)
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            Node visualCard = cardLoader.load();
            AnchorPane.setBottomAnchor(visualCard, 30.00);
            AnchorPane.setLeftAnchor(visualCard, 30.00);
            CardController controller = cardLoader.getController();
            controller.setRootController(rootController);
            controller.setup(card);
            rectangleAnchorPane.getChildren().add(visualCard);
            limitValueLabel.setText("€ " + card.getSpendingLimit().toString());
        }
        catch (IOException e)
        {
            System.err.println("error loading the account popup" + e.getMessage());
            e.printStackTrace();
        }
    }

}
