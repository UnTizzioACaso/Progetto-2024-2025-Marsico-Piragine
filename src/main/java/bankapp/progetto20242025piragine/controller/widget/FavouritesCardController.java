package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import bankapp.progetto20242025piragine.util.VisualCardCreator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.sql.SQLException;

public class FavouritesCardController extends WidgetController{

    // HBox that holds all favourite cards
    @FXML
    private HBox favouriteCardsHBox;


    public String getWidgetType()
    {
        return favouriteCardsHBox.getId();
    }

    // Adds a card to the favouriteCardsHBox along with a spacer
    @FXML
    public void addFavouriteCard(Node card)
    {
        try
        {
            FXMLLoader spaceloader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/favouriteCardsSpacingRegion.fxml"));
            Region spacer = spaceloader.load();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            favouriteCardsHBox.getChildren().add(card);
            favouriteCardsHBox.getChildren().add(spacer);
        }
        catch (IOException e)
        {
            System.err.println("Error loading an element in favourite cards widget: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Initializes the favourite cards widget with default cards
    @FXML
    public void initializer()
    {
        try
        {
            for (Card c : CardDAO.getCardsByUserId(rootController.user.getUserID()))
            {
                if (c.isFavourite())
                {
                    Node card = VisualCardCreator.cardCorrectValues(rootController, c);
                    addFavouriteCard(card);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("error finding all user's cards: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
