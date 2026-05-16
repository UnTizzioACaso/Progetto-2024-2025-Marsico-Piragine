package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;

import bankapp.progetto20242025piragine.controller.widget.WidgetController;
import bankapp.progetto20242025piragine.model.HomeWidgetCustom;
import bankapp.progetto20242025piragine.dao.HomeWidgetCustomDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.EasyFxmlLoader;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.List;

public class HomePageController extends BranchController
{
    @FXML
    public GridPane homePageGridPane;

    @FXML
    public void initialize() //initializing the home page
    {
        if(CurrentSession.getHomePageController() == null) {CurrentSession.setHomePageController(this);}

        try
        {
            addWidget("/bankapp/progetto20242025piragine/fxml/widget/favouritesCard.fxml", 1, 0); //getting favouritesCard widget's fxml
            addWidget("/bankapp/progetto20242025piragine/fxml/widget/bankAccount.fxml", 0, 0); //getting favouritesCard widget's fxml
            List<HomeWidgetCustom> widgets = HomeWidgetCustomDAO.getWidgetsByUserId(CurrentSession.getLoggedUser().getUserID());
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
            System.err.println("error during initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void loadAddWidget12()
    {
        PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget12.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget01()
    {
        PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget01.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget11()
    {
        PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget11.fxml", 600, 600);
    }

    @FXML
    public void loadAddWidget02()
    {
        PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget02.fxml", 600, 600);
    }

    public void addWidget(String fxml, int column, int row)
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader(fxml);
        Node node = p.getValue();
        if (column == 0) {node.setStyle(node.getStyle() + "-fx-max-width: 400");}
        WidgetController controller = (WidgetController) p.getKey();
        homePageGridPane.add(node, column, row);
        controller.homePageGridPane = homePageGridPane;

        controller.widget = node;
        HomeWidgetCustomDAO.updatePosition(CurrentSession.getLoggedUser().getUserID(), controller.getWidgetType(), row, column, false);
    }
}