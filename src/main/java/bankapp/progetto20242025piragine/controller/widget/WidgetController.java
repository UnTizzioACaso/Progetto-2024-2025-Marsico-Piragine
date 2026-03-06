package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.HomeWidgetCustomDAO;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.sql.SQLException;
import java.util.List;

public class WidgetController extends BranchController {

    public GridPane homePageGridPane;

    public String getWidgetType(){ return "";}

    @FXML
    public Label widgetTitleLabel;

    //takes the root of the widget and removes it from the home page grid pane
    public void removeWidget(Node root)
    {
        List<Node> nodes = homePageGridPane.getChildren();
        for(Node node : nodes)
        {
            if (root.getId().equals(node.getId()))
            {
                homePageGridPane.getChildren().remove(node);
                try {HomeWidgetCustomDAO.markAsRemoved(rootController.user.getUserID(),  root.getId());}
                catch (SQLException e)
                {
                    System.err.println("error during elimination of " + root.getId() + e);
                    e.printStackTrace();
                }
                return;
            }
        }
    }


}
