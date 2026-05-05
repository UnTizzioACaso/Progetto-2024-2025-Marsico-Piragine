package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget11Controller extends BranchController {

    @FXML
    private Node closeAddWidget11;

    @FXML
    private VBox widgetsVBox;

    @FXML
    private ScrollPane root;

    @Override
    public void initializer()
    {
        Set<String> usedIds = rootController.homePageGridPane.getChildren().stream().map(Node::getId).filter(Objects::nonNull).collect(Collectors.toSet());

        for (Node widget : widgetsVBox.getChildren())
        {
            if (usedIds.contains(widget.getId()))
            {
                widget.setDisable(true);
                widget.setOpacity(0.5);
            }
        }
        ThemeManager.applyTheme(root.getScene(), rootController.user.getTheme());

    }
    @FXML
    public void addTransactionHistoryWidget()
    {
        rootController.homePageController.addWidget("/bankapp/progetto20242025piragine/fxml/widget/transactionHistory.fxml", 1, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyIncomeWidget()
    {
        rootController.homePageController.addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyIncomes.fxml", 1, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyExpensesWidget()
    {
        rootController.homePageController.addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyExpenses.fxml", 1, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addLastFiveExpensesWidget()
    {
        rootController.homePageController.addWidget("/bankapp/progetto20242025piragine/fxml/widget/lastFiveExpenses.fxml", 1, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closeWidget() {
        if (closeAddWidget11 != null && closeAddWidget11.getScene() != null) {
            Stage stage = (Stage) closeAddWidget11.getScene().getWindow();
            stage.close();
        }
    }

}
