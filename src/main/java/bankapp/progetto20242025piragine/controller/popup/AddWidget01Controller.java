package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.page.HomePageController;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget01Controller extends BranchController {

    public HomePageController homePageController;

    @FXML
    private VBox widgetsVBox;

    public void disableUsedWidgets(GridPane homePageGridPane) {
        Set<String> usedIds = homePageGridPane.getChildren().stream().map(Node::getId).filter(Objects::nonNull).collect(Collectors.toSet());

        for (Node widget : widgetsVBox.getChildren()) {
            if (usedIds.contains(widget.getId())) {
                widget.setDisable(true);
                widget.setOpacity(0.5);
            }
        }
        ThemeManager.applyTheme(widgetsVBox.getScene(), rootController.user.getTheme());
    }

    @FXML
    public void addQuickContactWidget() {
        homePageController.addWidget("/bankapp/progetto20242025piragine/fxml/widget/quickContact.fxml", 0, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMonthlyBalanceWidget() {
        homePageController.addWidget("/bankapp/progetto20242025piragine/fxml/widget/monthlyBalance.fxml", 0, 1);
        Stage stage = (Stage) widgetsVBox.getScene().getWindow();
        stage.close();
    }
}