package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;

import bankapp.progetto20242025piragine.controller.popup.AddWidget01Controller;
import bankapp.progetto20242025piragine.controller.popup.AddWidget02Controller;
import bankapp.progetto20242025piragine.controller.popup.AddWidget11Controller;
import bankapp.progetto20242025piragine.controller.popup.AddWidget12Controller;
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
        CurrentSession.setHomePageController(this);
        List<HomeWidgetCustom> widgets = HomeWidgetCustomDAO.getUsedWidgetsByUserId(CurrentSession.getLoggedUser().getUserID());
        addWidget("/bankapp/progetto20242025piragine/fxml/widget/favouritesCard.fxml", 1, 0); //adding favouritesCard widget's fxml
        addWidget("/bankapp/progetto20242025piragine/fxml/widget/bankAccount.fxml", 0, 0); //adding bankAccount widget's fxml
        for (HomeWidgetCustom widget : widgets) {
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


    @FXML
    public void loadAddWidget12()
    {
        AddWidget12Controller controller = (AddWidget12Controller) PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget12.fxml", 600, 600);
        controller.getRoot().requestFocus();
        controller.getRoot().setVvalue(0.0);
    }

    @FXML
    public void loadAddWidget01()
    {
        AddWidget01Controller controller = (AddWidget01Controller) PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget01.fxml", 600, 600);
        controller.getRoot().requestFocus();
        controller.getRoot().setVvalue(0.0);
    }

    @FXML
    public void loadAddWidget11()
    {
        AddWidget11Controller controller = (AddWidget11Controller) PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget11.fxml", 600, 600);;
        controller.getRoot().requestFocus();
        controller.getRoot().setVvalue(0.0);
    }

    @FXML
    public void loadAddWidget02()
    {
        AddWidget02Controller controller = (AddWidget02Controller) PopupCreator.showPopup("Aggiungi un widget", "/bankapp/progetto20242025piragine/fxml/popup/addWidget02.fxml", 600, 600);
        controller.getRoot().requestFocus();
        controller.getRoot().setVvalue(0.0);
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
        controller.x = column;
        controller.y = row;
        HomeWidgetCustomDAO.updatePosition(CurrentSession.getLoggedUser().getUserID(), node.getId(), row, column);
    }
}