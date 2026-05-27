package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.HomeWidgetCustomDAO;
import bankapp.progetto20242025piragine.model.HomeWidgetCustom;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget01Controller extends BranchController {

    @FXML
    private Node closeAddWidget01;

    @FXML
    private VBox widgetsVBox, lastFiveExpensesVBox,  monthlyExpensesVBox, monthlyIncomesVBox;

    @FXML
    GridPane monthlyBalanceGridPane, quickContactGridPane;

    @FXML
    private Region lastFiveExpensesRegion, monthlyBalanceRegion, monthlyExpensesRegion, monthlyIncomesRegion, quickContactRegion;


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

        root.requestFocus();
        root.setVvalue(0.0);
    }

    @FXML
    public void addQuickContactWidget() {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/quickContact.fxml", 0, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyBalanceWidget() {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyBalance.fxml", 0, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyExpensesWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyExpenses.fxml", 0, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addLastFiveExpensesWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/lastFiveExpenses.fxml", 0, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyIncomeWidget()
    {
        CurrentSession.getHomePageController().addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyIncomes.fxml", 0, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closeWidget() {
        if (closeAddWidget01 != null && closeAddWidget01.getScene() != null) {
            Stage stage = (Stage) closeAddWidget01.getScene().getWindow();
            stage.close();
        }
    }
}