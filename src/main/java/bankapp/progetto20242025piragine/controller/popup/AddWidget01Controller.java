package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget01Controller extends BranchController {

    @FXML
    private Node closeAddWidget01;

    @FXML
    private VBox widgetsVBox;

    @FXML
    private ScrollPane root;

    @Override
    public void initializer()
    {
        Set<String> usedIds = CurrentSession.getHomePageController().homePageGridPane.getChildren().stream().map(Node::getId).filter(Objects::nonNull).collect(Collectors.toSet());
        for (Node widget : widgetsVBox.getChildren())
        {
            if (usedIds.contains(widget.getId()))
            {
                widget.setDisable(true);
                widget.setOpacity(0.5);
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