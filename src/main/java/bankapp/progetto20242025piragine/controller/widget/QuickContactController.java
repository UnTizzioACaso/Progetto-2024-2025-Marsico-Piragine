package bankapp.progetto20242025piragine.controller.widget;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.List;

public class QuickContactController extends WidgetController
{
    @FXML
    private GridPane quickContactGridPane;

    @FXML
    public void remove()
    {

        List<Node> nodes = homePageGridPane.getChildren();
        for(Node node : nodes)
        {
            if (quickContactGridPane.getId().equals(node.getId()))
            {
                homePageGridPane.getChildren().remove(node);
                return;
            }
        }
    }

}
