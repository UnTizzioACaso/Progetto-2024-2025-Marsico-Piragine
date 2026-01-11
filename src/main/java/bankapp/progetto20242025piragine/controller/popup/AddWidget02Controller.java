package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget02Controller extends BranchController
{


    @FXML
    private VBox widgetsVBox;

    public void disableUsedWidgets(GridPane homePageGridPane)
    {
        ThemeManager.applyTheme(widgetsVBox.getScene(), rootController.user.getTheme());
        Set<String> usedIds = homePageGridPane.getChildren().stream().map(Node::getId).filter(Objects::nonNull).collect(Collectors.toSet());

        for (Node widget : widgetsVBox.getChildren())
        {
            if (usedIds.contains(widget.getId()))
            {
                widget.setDisable(true);
            }
        }
    }


}
