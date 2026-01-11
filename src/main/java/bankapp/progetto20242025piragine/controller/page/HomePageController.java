package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
            FXMLLoader fiveExpensesLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/lastFiveExpenses.fxml")); //getting lastFiveExpenses widget's fxml
            Node favouritesCards = favouritesCardLoader.load(); //creating favouritesCards widget's node
            Node bankAccount = bankAccountLoader.load(); //creating bankAccount widget's node
            Node fiveExpenses = fiveExpensesLoader.load(); //creating fiveExpenses widget's node
            BranchController bankAccountController = bankAccountLoader.getController(); //getting the bankAccount widget's controller
            bankAccountController.setRootController(rootController); //setting the rootController in the bankAccount widget's
            homePageGridPane.add(favouritesCards, 1, 0); //adding the favouriteCards Node to homePageGridPane
            GridPane.setMargin(bankAccount, new Insets(10, 10, 0, 10)); //setting GridPanes margins for the bankAccount widget
            GridPane.setMargin(fiveExpenses, new Insets(10, 10, 0, 10)); //setting GridPanes margins for the fiveExpenses widget
            GridPane.setMargin(favouritesCards, new Insets(10, 10, 0, 10)); //setting GridPanes margins for the favouritesCards widget
            homePageGridPane.add(bankAccount, 0, 0); //adding the bankAccount Node to homePageGridPane
            homePageGridPane.add(fiveExpenses, 0, 1); //adding the fiveExpenses Node to homePageGridPane
        }
        catch (Exception e)
        {
            System.err.println("error during initializzation");
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void addWidget12()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/addWidget12.fxml")); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            AddWidget12Controller controller = loader.getController(); //getting the controller from the loader
            controller.setRootController(rootController);
            Stage popupStage = new Stage(); //creating a new stage for the accountPopup
            popupStage.setTitle("Aggiungi un widget"); //setting the title
            popupStage.setMinWidth(420); //setting popup's minimum width
            popupStage.setMinHeight(300); //setting popup's minimum height
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait(); //blocks openAccountPopup event until the app gets closed
        }
        catch (IOException e)
        {
            System.err.println("error loading the add widget (1,2) popup" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void addWidget01()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/addWidget01.fxml")); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            AddWidget01Controller controller = loader.getController(); //getting the controller from the loader
            controller.setRootController(rootController);
            Stage popupStage = new Stage(); //creating a new stage for the accountPopup
            popupStage.setTitle("Aggiungi un widget"); //setting the title
            popupStage.setMinWidth(420); //setting popup's minimum width
            popupStage.setMinHeight(300); //setting popup's minimum height
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait(); //blocks openAccountPopup event until the app gets closed
        }
        catch (IOException e)
        {
            System.err.println("error loading the add widget (0,1) popup" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void addWidget11()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/addWidget11.fxml")); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            AddWidget11Controller controller = loader.getController(); //getting the controller from the loader
            controller.setRootController(rootController);
            Stage popupStage = new Stage(); //creating a new stage for the accountPopup
            popupStage.setTitle("Aggiungi un widget"); //setting the title
            popupStage.setMinWidth(420); //setting popup's minimum width
            popupStage.setMinHeight(300); //setting popup's minimum height
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait(); //blocks openAccountPopup event until the app gets closed
        }
        catch (IOException e)
        {
            System.err.println("error loading the add widget (1,1) popup" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void addWidget02()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/addWidget02.fxml")); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            AddWidget02Controller controller = loader.getController(); //getting the controller from the loader
            controller.setRootController(rootController);
            Stage popupStage = new Stage(); //creating a new stage for the accountPopup
            popupStage.setTitle("Aggiungi un widget"); //setting the title
            popupStage.setMinWidth(420); //setting popup's minimum width
            popupStage.setMinHeight(300); //setting popup's minimum height
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait(); //blocks openAccountPopup event until the app gets closed
        }
        catch (IOException e)
        {
            System.err.println("error loading the add widget (0,2) popup" + e.getMessage());
            e.printStackTrace();
        }
    }
}