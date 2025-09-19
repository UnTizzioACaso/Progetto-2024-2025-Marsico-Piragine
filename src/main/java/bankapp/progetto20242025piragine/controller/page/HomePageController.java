package bankapp.progetto20242025piragine.controller.page;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;

public class HomePageController extends BranchPageController
{
    @FXML
    HBox favouriteCardsHBox;

    @FXML
    public void initialize() //initializing the home page
    {
        int valoreneldb = 3;
        for (int i = 0; i < valoreneldb; i++)
        {


            try
            {
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml")); //getting the card base fxml
                FXMLLoader spacerloader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/favouriteCardsSpacingRegion.fxml")); //getting the spacer between card
                Region spacer = spacerloader.load(); //creating the spacer between cards
                Parent card = cardloader.load(); //creating the card object from the fxml
                HBox.setHgrow(spacer, Priority.ALWAYS);
                favouriteCardsHBox.getChildren().add(card); //adding the card to favouriteCardsHBox
                favouriteCardsHBox.getChildren().add(spacer); //adding the spacer to favouriteCardsHBox

            }
            catch (IOException e)
            {
                System.err.println("error loading an element from favourite cards widget" + e.getMessage());
                e.printStackTrace();
            }

        }

    }
}
