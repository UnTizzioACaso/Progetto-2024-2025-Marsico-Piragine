package bankapp.progetto20242025piragine.controller.widget;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;

public class FavouritesCardController {

    // HBox that holds all favourite cards
    @FXML
    private HBox favouriteCardsHBox;

    // Adds a card to the favouriteCardsHBox along with a spacer
    @FXML
    public void addFavouriteCard(Parent card)
    {
        try
        {
            // Load the spacer FXML
            FXMLLoader spaceloader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/favouriteCardsSpacingRegion.fxml"));
            Region spacer = spaceloader.load(); // Create the spacer between cards
            HBox.setHgrow(spacer, Priority.ALWAYS); // Make the spacer expand to fill available horizontal space
            favouriteCardsHBox.getChildren().add(card); // Add the card to the HBox
            favouriteCardsHBox.getChildren().add(spacer); // Add the spacer after the card
        }
        catch (IOException e)
        {
            System.err.println("Error loading an element in favourite cards widget: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Initializes the favourite cards widget with default cards
    @FXML
    public void initialize()
    {
        int valoreneldb = 3; // Example value representing number of cards from database
        for (int i = 0; i < valoreneldb; i++)
        {
            try
            {
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
                // Load the card FXML
                AnchorPane card = cardloader.load(); // Create the card object from FXML
                HBox.setMargin(card, new Insets(0, 5, 5, 0)); // Add margin around the card
                addFavouriteCard(card); // Add the card with a spacer to the HBox
                double ratio = 53.98 / 85.60; // Maintain card height-to-width ratio
                card.prefHeightProperty().bind(card.prefWidthProperty().multiply(ratio)); // Bind height to width
            }
            catch (IOException e)
            {
                System.err.println("Error loading an element in favourite cards widget: " + e.getMessage()); // Handle any exception during favourite cards creation
                e.printStackTrace();
            }
        }
    }
}
