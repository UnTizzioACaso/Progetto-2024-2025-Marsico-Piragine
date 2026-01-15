package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.db.HomeWidgetCustom;
import bankapp.progetto20242025piragine.db.HomeWidgetCustomDAO;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
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
                try
                {
                    System.out.println(rootController.user.getUserID() +  homePageGridPane.getId());
                    HomeWidgetCustomDAO.markAsRemoved(rootController.user.getUserID(),  quickContactGridPane.getId());
                }
                catch (SQLException e)
                {
                    System.err.println("error during elimination of " + quickContactGridPane.getId() + e);
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    @Override
    public String getWidgetType(){ return quickContactGridPane.getId();}

}
