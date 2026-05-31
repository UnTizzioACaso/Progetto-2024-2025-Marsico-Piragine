package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.NotifyDAO;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import bankapp.progetto20242025piragine.util.VisualNotificationCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

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

    @FXML
    public void initialize()
    {
        ThemeManager.applyTheme(notificationSliderAnchorPane.getScene(), CurrentSession.getLoggedUser().getTheme());
    }



}
