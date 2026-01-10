package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CreditCardRectangleController;
import bankapp.progetto20242025piragine.controller.popup.CreateCardPopupController;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CardPageController extends BranchController
{
    // VBox that will contain all the user's cards
    @FXML
    VBox cardsContainerVBox;

    // Opens the popup to create a new card
    @FXML
    public void openCreateCard()
    {
        try
        {
            // Load the create card popup FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/createCardPopup.fxml"));

            // Create the root node from the FXML
            Parent root = loader.load();

            // Retrieve the popup controller
            CreateCardPopupController controller = loader.getController();
            controller.setRootController(rootController);

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Crea e personalizza la carta");

            // Set minimum popup dimensions
            popupStage.setMinWidth(500);
            popupStage.setMinHeight(400);

            // Block interaction with other windows until the popup is closed
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Set the scene and show the popup
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait(); // Blocks execution until popup is closed
        }
        catch (IOException e)
        {
            // Handle errors while loading the popup
            System.err.println("error loading the create card popup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Called automatically when the page is initialized
    @Override
    public void initializer()
    {
        try
        {
            // Retrieve all cards associated with the logged-in user
            List<Card> cards = CardDAO.getCardsByUserId(rootController.user.getUserID());

            // Create a UI component for each card
            for (int i = 0; i < cards.size(); i++)
            {
                try
                {
                    // Load the credit card rectangle component
                    FXMLLoader cardRectangleLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/creditCardRectangle.fxml"));

                    // Create the node from the FXML
                    Node cardRectangle = cardRectangleLoader.load();

                    // Get the controller for the card component
                    CreditCardRectangleController controller = cardRectangleLoader.getController();
                    controller.setRootController(rootController);

                    // Fill the card component with data
                    controller.fill(cards.get(i));

                    // Add the card component to the VBox
                    cardsContainerVBox.getChildren().add(cardRectangle);
                }
                catch (IOException e)
                {
                    // Handle errors while loading card components
                    System.err.println("error loading the credit card rectangle: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e)
        {
            // Handle database errors
            throw new RuntimeException(e);
        }
    }
}
