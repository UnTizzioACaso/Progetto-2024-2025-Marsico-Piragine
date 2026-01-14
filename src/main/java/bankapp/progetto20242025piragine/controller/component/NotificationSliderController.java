package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class NotificationSliderController extends BranchController
{
    @FXML
    public VBox notificationVBox;

    @FXML
    AnchorPane notificationAnchorPane;

    @FXML
    public void close()
    {
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(250), notificationAnchorPane);
        slideOut.setFromX(0);
        slideOut.setToX(190);
        slideOut.setOnFinished(e -> {rootController.rootWindow.setRight(null);});
        slideOut.play();
    }

}
