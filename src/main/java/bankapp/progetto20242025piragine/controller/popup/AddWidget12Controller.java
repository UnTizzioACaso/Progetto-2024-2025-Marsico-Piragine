package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.HomeWidgetCustomDAO;
import bankapp.progetto20242025piragine.model.HomeWidgetCustom;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget12Controller extends BranchController
{
    @FXML
    private Node closeAddWidget12, transactionHistoryGridPane, monthlyExpensesVBox, lastFiveExpensesVBox, monthlyIncomesVBox;

    @FXML
    private Region transactionHistoryRegion, monthlyExpensesRegion, lastFiveExpensesRegion, monthlyIncomesRegion;

    @FXML
    private VBox widgetsVBox;

    @FXML
    private ScrollPane root;

    @Override
    public void initializer()
    {
        List<HomeWidgetCustom> usedWidgets = HomeWidgetCustomDAO.getUsedWidgetsByUserId(CurrentSession.getLoggedUser().getUserID());
        for(HomeWidgetCustom widget : usedWidgets)
        {
            switch (widget.getTypeWidget())
            {
                case "transactionHistoryGridPane" -> {
                    transactionHistoryRegion.setDisable(true);
                    transactionHistoryGridPane.setOpacity(0.3);
                }
                case "monthlyExpensesVBox" -> {
                    monthlyExpensesRegion.setDisable(true);
                    monthlyExpensesVBox.setOpacity(0.3);
                }
                case "lastFiveExpensesVBox" -> {
                    lastFiveExpensesRegion.setDisable(true);
                    lastFiveExpensesVBox.setOpacity(0.3);
                }
                case "monthlyIncomesVBox" -> {
                    monthlyIncomesRegion.setDisable(true);
                    monthlyIncomesVBox.setOpacity(0.3);
                }
            }
        }
    }

    @FXML
    public void addTransactionHistoryWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/transactionHistory.fxml", 1, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyIncomeWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyIncomes.fxml", 1, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyExpensesWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyExpenses.fxml", 1, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addLastFiveExpensesWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/lastFiveExpenses.fxml", 1, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void closeWidget() {
        if (closeAddWidget12 != null && closeAddWidget12.getScene() != null) {
            Stage stage = (Stage) closeAddWidget12.getScene().getWindow();
            stage.close();
        }
    }
}
