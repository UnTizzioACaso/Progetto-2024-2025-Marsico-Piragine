package bankapp.progetto20242025piragine.controller.widget;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;

public class FavouritesCardController {
    @FXML
    private HBox favouriteCardsHBox;

    @FXML
    public void addFavouriteCard(Parent card) //this method add in favouriteCardsHBox a card and a spacer
    {
        try
        {
            FXMLLoader spaceloader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/favouriteCardsSpacingRegion.fxml")); //getting the spacer base fxml
            Region spacer = spaceloader.load(); //creating the spacer between cards
            HBox.setHgrow(spacer, Priority.ALWAYS); //setting the spacer to grow when there is empty space
            favouriteCardsHBox.getChildren().add(card); //adding the card to favouriteCardsHBox
            favouriteCardsHBox.getChildren().add(spacer); //adding the spacer to favouriteCardsHBox
        }
        catch (IOException e)
        {
            System.err.println("error loading an element from favourite cards widget" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize()
    {
        int valoreneldb = 3;
        for (int i = 0; i < valoreneldb; i++)
        {
            try
            {
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml")); //getting the card base fxml
                Parent card = cardloader.load(); //creating the card object from the fxml
                addFavouriteCard(card);
            }
            catch (IOException e)
            {
                System.err.println("error loading an element from favourite cards widget" + e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
