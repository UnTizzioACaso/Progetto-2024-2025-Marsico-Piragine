package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CreditCardRectangleController;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import bankapp.progetto20242025piragine.util.CardService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CardPageController extends BranchController
{
    @FXML
    VBox cardsContainerVBox;



    @FXML
    public void createCard() throws SQLException
    {

        CardService.createCard(rootController.user.getUserID(), rootController.user.)
    }

    @Override
    public void initializer() throws SQLException
    {
        List<Card> cards = CardDAO.getCardsByUserId(rootController.user.getUserID());

        for (int i = 0; i < cards.size(); i++)
        {
            try
            {
                FXMLLoader cardRectangleLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/creditCardRectangle.fxml"));
                Node cardRectangle = cardRectangleLoader.load();
                CreditCardRectangleController controller = cardRectangleLoader.getController();
                controller.setRootController(rootController);
                controller.fill(cards.get(i));
                cardsContainerVBox.getChildren().add(cardRectangle);
            }
            catch (IOException e)
            {
                System.err.println("error loading the account popup" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
