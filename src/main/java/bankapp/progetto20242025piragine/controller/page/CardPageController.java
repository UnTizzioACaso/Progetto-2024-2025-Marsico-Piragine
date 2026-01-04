package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CreditCardRectangleController;
import bankapp.progetto20242025piragine.controller.popup.AccountPopupController;
import bankapp.progetto20242025piragine.controller.popup.CreateCardPopupController;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import bankapp.progetto20242025piragine.util.CardService;
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
    @FXML
    VBox cardsContainerVBox;



    @FXML
    public void openCreateCard() throws SQLException
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/createCardPopup.fxml")); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            CreateCardPopupController controller = loader.getController(); //getting the controller from the loader
            controller.setRootController(rootController);
            Stage popupStage = new Stage(); //creating a new stage for the accountPopup
            popupStage.setTitle("Crea e personalizza la carta"); //setting the title
            popupStage.setMinWidth(500); //setting popup's minimum width
            popupStage.setMinHeight(400); //setting popup's minimum height
            popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait(); //blocks openAccountPopup event until the app gets closed
        }
        catch (IOException e)
        {
            System.err.println("error loading the create card popup" + e.getMessage());
            e.printStackTrace();
        }

        Card card= CardService.createCard(rootController.user.getUserID(), BankAccountDAO.getAccountByUserId(rootController.user.getUserID()).getIdAccount(), );
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
                System.err.println("error loading the credit card rectangle" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
