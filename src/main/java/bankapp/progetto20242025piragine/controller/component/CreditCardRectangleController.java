package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.MenageCardPopupController;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.util.CardCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreditCardRectangleController extends BranchController
{
    @FXML
    public Label limitValueLabel;

    public Card card;

    @FXML
    public AnchorPane rectangleAnchorPane;

    public void fill(Card c)
    {
        this.card = c;
        Node visualCard = CardCreator.cardCorrectValuesWithoutButtons(rootController, card);
        rectangleAnchorPane.getChildren().add(visualCard);
        limitValueLabel.setText("€ " + card.getSpendingLimit().toString());

        AnchorPane.setBottomAnchor(visualCard, 30.00);
        AnchorPane.setLeftAnchor(visualCard, 30.00);
    }

    @FXML
    private void menageCard()
    {
        MenageCardPopupController controller = (MenageCardPopupController) rootController.showPopup("Gestisci la carta", "/bankapp/progetto20242025piragine/fxml/popup/menageCardPopup.fxml", 500, 300);
        if(controller != null) {System.out.println("controller");}
        controller.card = card;
        controller.removeFavouritesButton.setVisible(card.isFavourite());
        controller.addFavouritesButton.setVisible(!card.isFavourite());
        controller.blockButton.setVisible(card.isStatus());
        controller.unblockButton.setVisible(!card.isStatus());
    }
}
