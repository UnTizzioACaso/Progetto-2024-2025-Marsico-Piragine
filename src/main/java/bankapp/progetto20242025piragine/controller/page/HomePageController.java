package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;

public class HomePageController extends BranchController
{
    @FXML
    private GridPane homePageGridPane;

    @FXML
    public void initialize() //initializing the home page
    {
        try
        {
            FXMLLoader favouritesCardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/favouritesCard.fxml")); //getting favouritesCard widget's fxml
            FXMLLoader bankAccountLoader = new FXMLLoader (getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/bankAccount.fxml")); //getting bankAccount widget's fxml
            FXMLLoader fiveExpensesLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/lastFiveExpenses.fxml")); //getting lastFiveExpenses's fxml
            Node favouritesCard = favouritesCardLoader.load(); //loading favouritesCard widget
            Node bankAccount = bankAccountLoader.load(); //loading bankAccount widget
            Node fiveExpenses = fiveExpensesLoader.load(); //loading lastFiveExpenses
            homePageGridPane.add(favouritesCard, 1, 0); //adding favouritesCard widget to the homePageGridPane
            GridPane.setMargin(bankAccount, new Insets(0, 10, 0, 0)); //setting GridPane's margins for the bankAccount widget
            GridPane.setMargin(fiveExpenses, new Insets(20, 35, 20, 25)); //setting GridPane's margins for the fiveExpenses widget
            homePageGridPane.add(bankAccount, 0, 0); //adding bankAccount widget to the homePageGridPane
            homePageGridPane.add(fiveExpenses, 0, 1); //adding fiveExpenses widget to the homePageGridPane
        }
        catch (Exception e)
        {
            System.err.println("error during initializzation of the home page");
            throw new RuntimeException(e);
        }
    }
}
