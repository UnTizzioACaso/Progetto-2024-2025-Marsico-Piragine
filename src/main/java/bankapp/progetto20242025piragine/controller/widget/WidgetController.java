package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.HomeWidgetCustomDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.sql.SQLException;
import java.util.List;

public class WidgetController extends BranchController {

    private GridPane homePageGridPane;

    public String getWidgetType(){ return "";}

    private int x, y;

    private Node widget;

    public void setWidget( Node widget) {this.widget = widget;}

    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @FXML
    private Label widgetTitleLabel;

    public void setHomePageGridPane(GridPane homePageGridPane) {this.homePageGridPane = homePageGridPane;}

    //takes the root of the widget and removes it from the home page grid pane
    public void removeWidget()
    {
        homePageGridPane.getChildren().remove(widget);
        HomeWidgetCustomDAO.markAsRemoved(CurrentSession.getLoggedUser().getUserID(), widget.getId());
    }


}
