package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.*;
import bankapp.progetto20242025piragine.controller.widget.WidgetController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomePageController extends BranchController
{
    @FXML
    private GridPane homePageGridPane;

    @Override
    public void initializer() //initializing the home page
    {
        try
        {
            FXMLLoader favouritesCardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/favouritesCard.fxml")); //getting favouritesCard widget's fxml
            FXMLLoader bankAccountLoader = new FXMLLoader (getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/bankAccount.fxml")); //getting bankAccount widget's fxml
            Node favouritesCards = favouritesCardLoader.load(); //creating favouritesCards widget's node
            Node bankAccount = bankAccountLoader.load(); //creating bankAccount widget's node
            BranchController bankAccountController = bankAccountLoader.getController(); //getting the bankAccount widget's controller
            bankAccountController.setRootController(rootController); //setting the rootController in the bankAccount widget's
            homePageGridPane.add(favouritesCards, 1, 0); //adding the favouriteCards Node to homePageGridPane
            homePageGridPane.add(bankAccount, 0, 0); //adding the bankAccount Node to homePageGridPane
        }
        catch (Exception e)
        {
            System.err.println("error during initializzation");
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void loadAddWidget12()
    {
        rootController.showPopUp("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget12.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget01()
    {
        rootController.showPopUp("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget01.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget11()
    {
        rootController.showPopUp("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget11.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget02()
    {
        rootController.showPopUp("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget02.fxml", 600, 600);
    }

    public void addWidget(String fxml, int column, int row)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader (getClass().getResource(fxml));
            Node node = loader.load();
            if (column == 0) {node.setStyle("-fx-max-width: 400");}
            WidgetController controller = loader.getController();
            controller.setRootController(rootController);
            homePageGridPane.add(node, column, row);
            controller.homePageGridPane = homePageGridPane;
        }
        catch (Exception e)
        {
            System.err.println("error during adding "+ fxml + "widget" + e.getMessage());
            e.printStackTrace();
        }
    }
}