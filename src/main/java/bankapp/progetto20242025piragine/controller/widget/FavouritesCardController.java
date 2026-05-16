package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.model.Card;
import bankapp.progetto20242025piragine.dao.CardDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.VisualCardCreator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;

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

        for (Card c : CardDAO.getCardsByUserId(CurrentSession.getLoggedUser().getUserID()))
        {
            if (c.isFavourite())
            {
                Node card = VisualCardCreator.cardCorrectValues(CurrentSession.getRootController(), c);
                addFavouriteCard(card);
            }
        }

    }
}


