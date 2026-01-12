package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class NotificationAnchorPaneController extends BranchController
{
    @FXML
    public VBox notificationVBox;


    @FXML
    public void close()
    {
        rootController.rootWindow.setRight(null);
    }

}
