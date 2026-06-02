package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.HomeWidgetCustomDAO;
import bankapp.progetto20242025piragine.model.HomeWidgetCustom;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget02Controller extends BranchController {

    @FXML
    private Node closeAddWidget02;

    @FXML
    private VBox widgetsVBox, lastFiveExpensesVBox,  monthlyExpensesVBox, monthlyIncomesVBox;

    @FXML
    private GridPane monthlyBalanceGridPane, quickContactGridPane;

    @FXML
    private Region lastFiveExpensesRegion, monthlyBalanceRegion, monthlyExpensesRegion, monthlyIncomesRegion, quickContactRegion;

    @FXML
    private ScrollPane root;

    public ScrollPane getRoot() {return root;}

    public void setRoot(ScrollPane root) {
        this.root = root;
    }

    @FXML
    private void initialize()
    {
        List<HomeWidgetCustom> usedWidgets = HomeWidgetCustomDAO.getUsedWidgetsByUserId(CurrentSession.getLoggedUser().getUserID());
        for(HomeWidgetCustom widget : usedWidgets)
        {
            switch (widget.getTypeWidget())
            {
                case "lastFiveExpensesVBox" -> {
                    lastFiveExpensesRegion.setDisable(true);
                    lastFiveExpensesVBox.setOpacity(0.3);
                }
                case "monthlyBalanceGridPane" -> {
                    monthlyBalanceRegion.setDisable(true);
                    monthlyBalanceGridPane.setOpacity(0.3);
                }
                case "monthlyExpensesVBox" -> {
                    monthlyExpensesRegion.setDisable(true);
                    monthlyExpensesVBox.setOpacity(0.3);
                }
                case "monthlyIncomesVBox" -> {
                    monthlyIncomesRegion.setDisable(true);
                    monthlyIncomesVBox.setOpacity(0.3);
                }
                case "quickContactGridPane" -> {
                    quickContactRegion.setDisable(true);
                    quickContactGridPane.setOpacity(0.3);
                }
            }
        }
    }

    @FXML
    private void addQuickContactWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/quickContact.fxml", 0, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMonthlyBalanceWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyBalance.fxml", 0, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMonthlyExpensesWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyExpenses.fxml", 0, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addLastFiveExpensesWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/lastFiveExpenses.fxml", 0, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMonthlyIncomeWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyIncomes.fxml", 0, 2);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void closeWidget()
    {
        if (closeAddWidget02 != null && closeAddWidget02.getScene() != null)
        {
            Stage stage = (Stage) closeAddWidget02.getScene().getWindow();
            stage.close();
        }
    }
}
