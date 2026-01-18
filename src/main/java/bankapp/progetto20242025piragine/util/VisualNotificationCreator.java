package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.RootWindowController;
import bankapp.progetto20242025piragine.controller.component.NotificationController;
import bankapp.progetto20242025piragine.db.Notify;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;



public class VisualNotificationCreator {

    public static Node createVisualTransaction(RootWindowController rootController, Notify notify)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(VisualTransactionCreator.class.getResource("/bankapp/progetto20242025piragine/fxml/component/notification.fxml"));
            Node visualNotification = fxmlLoader.load();
            NotificationController controller = fxmlLoader.getController();
            controller.setRootController(rootController);
            controller.setCorrectValues(notify);
            return visualNotification;
        }
        catch(Exception e)
        {
            System.err.println("error during creating a transaction " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
