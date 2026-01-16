package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;

import bankapp.progetto20242025piragine.controller.widget.FavouritesCardController;
import bankapp.progetto20242025piragine.controller.widget.WidgetController;
import bankapp.progetto20242025piragine.db.HomeWidgetCustom;
import bankapp.progetto20242025piragine.db.HomeWidgetCustomDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
            addWidget("/bankapp/progetto20242025piragine/fxml/widget/favouritesCard.fxml", 1, 0); //getting favouritesCard widget's fxml
            addWidget("/bankapp/progetto20242025piragine/fxml/widget/bankAccount.fxml", 0, 0); //getting favouritesCard widget's fxml
            List<HomeWidgetCustom> widgets = HomeWidgetCustomDAO.getWidgetsByUserId(rootController.user.getUserID());
            for(HomeWidgetCustom widget : widgets)
            {
                if (!widget.isRemove())
                {
                    switch (widget.getTypeWidget()) {
                        case "lastFiveExpensesVBox" ->
                                addWidget("/bankapp/progetto20242025piragine/fxml/widget/lastFiveExpenses.fxml", widget.getColumn(), widget.getRow());
                        case "monthlyBalanceGridPane" ->
                                addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyBalance.fxml", widget.getColumn(), widget.getRow());
                        case "monthlyExpensesVBox" ->
                                addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyExpenses.fxml", widget.getColumn(), widget.getRow());
                        case "monthlyIncomesVBox" ->
                                addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyIncomes.fxml", widget.getColumn(), widget.getRow());
                        case "quickContactGridPane" ->
                                addWidget("/bankapp/progetto20242025piragine/fxml/widget/quickContact.fxml", widget.getColumn(), widget.getRow());
                        case "transactionHistoryGridPane" ->
                                addWidget("/bankapp/progetto20242025piragine/fxml/widget/transactionHistory.fxml", widget.getColumn(), widget.getRow());
                    }
                }
            }
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
        rootController.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget12.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget01()
    {
        rootController.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget01.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget11()
    {
        rootController.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget11.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget02()
    {
        rootController.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget02.fxml", 600, 600);
    }

    public void addWidget(String fxml, int column, int row)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader (getClass().getResource(fxml));
            Node node = loader.load();
            if (column == 0) {node.setStyle(node.getStyle() + "-fx-max-width: 400");}
            WidgetController controller = loader.getController();
            controller.setRootController(rootController);
            homePageGridPane.add(node, column, row);
            controller.homePageGridPane = homePageGridPane;
            controller.initializer();
            HomeWidgetCustomDAO.updatePosition(rootController.user.getUserID(), controller.getWidgetType(), row, column, false);
        }
        catch (Exception e)
        {
            System.err.println("error during adding "+ fxml + "widget" + e.getMessage());
            e.printStackTrace();
        }
    }
}