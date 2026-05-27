package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class NotificationSliderController extends BranchController
{
    @FXML
    public VBox notificationVBox;

    @FXML
    public AnchorPane notificationSliderAnchorPane;

    @FXML
    public void close()
    {
        CurrentSession.getTopbarController().showSlider();
    }

}
