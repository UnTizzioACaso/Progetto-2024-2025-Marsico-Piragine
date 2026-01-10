package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
            FXMLLoader transactionHistoryLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/transactionHistory.fxml"));
            Node favouritesCards = favouritesCardLoader.load(); //creating favouritesCards widget's node
            Node bankAccount = bankAccountLoader.load(); //creating bankAccount widget's node
            Node fiveExpenses = fiveExpensesLoader.load(); //creating fiveExpenses widget's node
            Node transactionHistory = transactionHistoryLoader.load();
            BranchController controller = bankAccountLoader.getController(); //getting the bankAccount widget's controller
            controller.setRootController(rootController); //setting the rootController in the bankAccount widget's
            homePageGridPane.add(favouritesCards, 1, 0); //adding the favouriteCards Node to homePageGridPane
            GridPane.setMargin(bankAccount, new Insets(10, 10, 0, 10)); //setting GridPanes margins for the bankAccount widget
            GridPane.setMargin(fiveExpenses, new Insets(10, 10, 0, 10)); //setting GridPanes margins for the fiveExpenses widget
            GridPane.setMargin(favouritesCards, new Insets(10, 10, 0, 10)); //setting GridPanes margins for the favouritesCards widget
            GridPane.setMargin(transactionHistory, new Insets(10, 10, 0, 10));
            homePageGridPane.add(bankAccount, 0, 0); //adding the bankAccount Node to homePageGridPane
            homePageGridPane.add(fiveExpenses, 0, 1); //adding the fiveExpenses Node to homePageGridPane
            homePageGridPane.add(transactionHistory, 0, 2);
        }
        catch (Exception e)
        {
            System.err.println("error during initializzation");
            throw new RuntimeException(e);
        }
    }

}