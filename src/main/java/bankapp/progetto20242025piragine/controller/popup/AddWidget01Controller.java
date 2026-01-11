package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddWidget01Controller extends BranchController
{


    @FXML
    public VBox widgetsVBox;

    public void disableUsedWidgets(GridPane homePageGridPane)
    {
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
