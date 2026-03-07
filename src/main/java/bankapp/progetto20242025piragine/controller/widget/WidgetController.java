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

    //same as menuShower, but without the option to show the bigger version of the widget
    public void miniMenuShower(Node root)
    {
        Popup popup = getMiniMenuPopup(root);
        if (popup.isShowing())
        {
            popup.hide();
            return;
        }
        Bounds bounds = widgetTitleLabel.localToScreen(widgetTitleLabel.getBoundsInLocal());
        ThemeManager.applyTheme(popup.getScene(), rootController.user.getTheme());
        popup.show(widgetTitleLabel, bounds.getMinX(), bounds.getMaxY());
    }

    //takes a string with the name visualized on the given node as root, creates and shows the menu popup
    public void menuShower(Node root, String fxml, String title)
    {
        Popup popup = getMenuPopup(root,  fxml, title);
        if (popup.isShowing())
        {
            popup.hide();
            return;
        }
        Bounds bounds = widgetTitleLabel.localToScreen(widgetTitleLabel.getBoundsInLocal());
        ThemeManager.applyTheme(popup.getScene(), rootController.user.getTheme());
        popup.show(widgetTitleLabel, bounds.getMinX(), bounds.getMaxY());
    }


    //takes a string with the name visualized on the given node as root, and creates the menu popup with relative buttons
    public Popup getMenuPopup(Node root, String fxml, String title) {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setAutoFix(true);

        VBox content = new VBox(1);
        content.setStyle("-fx-background-color: black; -fx-border-color: black; -fx-alignment: CENTER;" );
        Button one = new Button("ingrandisci " + widgetTitleLabel.getText());
        Button two = new Button("rimuovi " + widgetTitleLabel.getText());
        one.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        two.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        one.setStyle( "    -fx-background-color: #ff0000;\n" +
                "        -fx-effect: innershadow(\n" +
                "            three-pass-box,\n" +
                "            rgba(0, 0, 0, 0.25),\n" +
                "            27.63,\n" +
                "            0.17,\n" +
                "            -10,\n" +
                "            -10\n" +
                "        );");

        two.setStyle( "    -fx-background-color: #ff0000;\n" +
                "        -fx-effect: innershadow(\n" +
                "            three-pass-box,\n" +
                "            rgba(0, 0, 0, 0.25),\n" +
                "            27.63,\n" +
                "            0.17,\n" +
                "            -10,\n" +
                "            -10\n" +
                "        );");

        one.setOnAction(e -> {rootController.showPopup(title, fxml, 600, 600);});
        two.setOnAction(e -> {removeWidget(root);});
        content.getChildren().addAll(one, two);

        popup.getContent().add(content);
        return popup;
    }

    //same as getMenuPopup, but without the option to show the bigger version of the widget
    public Popup getMiniMenuPopup(Node root) {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setAutoFix(true);

        VBox content = new VBox();
        content.setStyle("-fx-background-color: black; -fx-border-color: black; -fx-alignment: CENTER;" );
        Button button = new Button("rimuovi " + widgetTitleLabel.getText());
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        button.setStyle( "    -fx-background-color: #ff0000;\n" +
                "        -fx-effect: innershadow(\n" +
                "            three-pass-box,\n" +
                "            rgba(0, 0, 0, 0.25),\n" +
                "            27.63,\n" +
                "            0.17,\n" +
                "            -10,\n" +
                "            -10\n" +
                "        );");

        button.setOnAction(e -> {removeWidget(root);});
        content.getChildren().add(button);

        popup.getContent().add(content);
        return popup;
    }

}
