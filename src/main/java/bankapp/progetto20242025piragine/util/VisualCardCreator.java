package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.RootWindowController;
import bankapp.progetto20242025piragine.controller.component.CardController;
import bankapp.progetto20242025piragine.db.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class VisualCardCreator
{

    //gives a visual card with correct values but without buttons
    public static Node cardCorrectValuesWithoutButtons(RootWindowController rootController, Card card )
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(VisualCardCreator.class.getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            Node visualCard = cardLoader.load();
            CardController controller = cardLoader.getController();
            controller.setRootController(rootController);
            controller.setup(card);
            return visualCard;
        }
        catch (Exception e)
        {
            System.err.println("error loading the visualCard " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    //gives a visual card with correct values and visible buttons
    public static Node cardCorrectValues(RootWindowController rootController, Card card )
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(VisualCardCreator.class.getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            Node visualCard = cardLoader.load();
            CardController controller = cardLoader.getController();
            controller.setRootController(rootController);
            controller.setupFavourites(card);
            return visualCard;
        }
        catch (Exception e)
        {
            System.err.println("error loading the visualCard " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //gives a card without correct values and without buttons
    public static Node cardWithoutButtons(RootWindowController rootController)
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(VisualCardCreator.class.getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            Node visualCard = cardLoader.load();
            CardController controller = cardLoader.getController();
            controller.setRootController(rootController);
            controller.removeButtons();
            return visualCard;
        }
        catch (Exception e)
        {
            System.err.println("error loading the visualCard " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
