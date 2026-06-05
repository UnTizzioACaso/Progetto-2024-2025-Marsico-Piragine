package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CreditCardRectangleController;
import bankapp.progetto20242025piragine.model.Card;
import bankapp.progetto20242025piragine.dao.CardDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.EasyFxmlLoader;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

public class CardPageController extends BranchController
{
    // VBox that will contain all the user's cards
    @FXML
    private VBox cardsContainerVBox;

    // Opens the popup to create a new card
    @FXML
    private void openCreateCard()
    {
        PopupCreator.showPopup("Crea e personalizza la carta","/bankapp/progetto20242025piragine/fxml/popup/createCardPopup.fxml", 500, 447);
    }

    @FXML
    private void initialize()
    {
        CurrentSession.setCardPageController(this);
        List<Card> cards = CardDAO.getCardsByUserId(CurrentSession.getLoggedUser().getUserID()); // Retrieve all cards associated with the logged-in user
        for (int i = 0; i < cards.size(); i++) // Create a UI component for each card
        {
            Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/creditCardRectangle.fxml");
            // Create the node from the FXML
            Node cardRectangle = p.getValue();
            // Get the controller for the card component
            CreditCardRectangleController controller = (CreditCardRectangleController) p.getKey();
            // Fill the card component with data
            controller.fill(cards.get(i));
            // Add the card component to the VBox
            cardsContainerVBox.getChildren().add(cardRectangle);
        }
    }
}
