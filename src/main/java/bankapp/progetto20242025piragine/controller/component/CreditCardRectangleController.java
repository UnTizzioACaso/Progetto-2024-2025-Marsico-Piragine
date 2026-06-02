package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.MenageCardPopupController;
import bankapp.progetto20242025piragine.model.Card;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.VisualCardCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CreditCardRectangleController extends BranchController
{
    @FXML
    private Label limitValueLabel;

    private Card card;

    @FXML
    private AnchorPane rectangleAnchorPane;

    public void fill(Card c)
    {
        this.card = c;
        Node visualCard = VisualCardCreator.cardCorrectValuesWithoutButtons(CurrentSession.getRootController(), card);
        rectangleAnchorPane.getChildren().add(visualCard);
        limitValueLabel.setText("€ " + card.getSpendingLimit().toString());

        AnchorPane.setBottomAnchor(visualCard, 30.00);
        AnchorPane.setLeftAnchor(visualCard, 30.00);
    }

    @FXML
    private void menageCard()
    {
        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}
        MenageCardPopupController controller = (MenageCardPopupController) PopupCreator.showPopup("Gestisci la carta", "/bankapp/progetto20242025piragine/fxml/popup/menageCardPopup.fxml", 500, 300);
        controller.setCard(card);
    }
}
