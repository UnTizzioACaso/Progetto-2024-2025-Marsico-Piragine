package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Notify;
import bankapp.progetto20242025piragine.db.NotifyDAO;
import bankapp.progetto20242025piragine.util.VisualNotificationCreator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.management.Notification;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Stack;

public class TopbarController extends BranchController {

    @FXML
    private ImageView notificationButton;
    @FXML
    private ImageView backArrowButton;
    @FXML
    private ImageView forwarArrowButton;
    @FXML
    private ImageView reloadPageButton;

    private AnchorPane notificationAnchorPane;
    private NotificationSliderController notificationAnchorPaneController;
    private Stack<String> backwardStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();

    @Override
    public void initializer()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/notificationSlider.fxml"));
            notificationAnchorPane =  loader.load();
            notificationAnchorPaneController = loader.getController();
            notificationAnchorPaneController.setRootController(rootController);
            notificationAnchorPaneController.initializer();
        }
        catch (Exception e)
        {
            System.err.println("error during loading /bankapp/progetto20242025piragine/fxml/component/notificationAnchorPane.fxml" + e);
            e.printStackTrace();
        }
    }






    @FXML
    public void showPopup()
    {
        try {
            if (rootController.rootWindow.getRight() == null)
            {
                updateNotifications();
                rootController.rootWindow.setRight(notificationAnchorPane);
                notificationAnchorPane.setTranslateX(190);
                TranslateTransition slideIn = new TranslateTransition(Duration.millis(400), notificationAnchorPane);
                slideIn.setFromX(190);
                slideIn.setToX(0);
                slideIn.play();
            }
            else
            {
                TranslateTransition slideOut = new TranslateTransition(Duration.millis(250), notificationAnchorPane);
                slideOut.setFromX(0);
                slideOut.setToX(190);
                slideOut.setOnFinished(e -> {rootController.rootWindow.setRight(null);});
                slideOut.play();
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateNotifications() throws SQLException {
        notificationAnchorPaneController.notificationVBox.getChildren().clear();


        List<Notify> notifies = NotifyDAO.getNotifyByUserId(rootController.user.getUserID());

        if (notifies.isEmpty())
        {
            Label empty = new Label("Nessuna notifica");
            empty.setStyle("-fx-padding: 5;");
            notificationAnchorPaneController.notificationVBox.getChildren().add(empty);
        }
        else
        {
            for (Notify n : notifies)
            {
                if(!n.isRead())
                {
                    Node notification = VisualNotificationCreator.createVisualTransaction(rootController, n);
                    notificationAnchorPaneController.notificationVBox.getChildren().add(notification);
                }
            }
        }
    }


    @FXML
    public void visitPage(String fxml)
    {
        backwardStack.push(fxml);
        forwardStack.clear();
    }

    @FXML
    public void backPage()
    {
        if (backwardStack.size() > 1)
        {
            String current = backwardStack.pop();
            forwardStack.push(current);
            rootController.switchPage(backwardStack.peek());
        }
    }

    @FXML
    public void nextPage()
    {
        if (!forwardStack.isEmpty())
        {
            String page = forwardStack.pop();
            backwardStack.push(page);
            rootController.switchPage(page);
        }
    }

    @FXML
    public void reloadPage()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(backwardStack.peek()));
            Parent node = fxmlLoader.load();
            BranchController controller = fxmlLoader.getController();
            controller.setRootController(rootController);
            controller.initializer();
            rootController.rootWindow.setCenter(node);
            controller.setRootController(rootController);
        }
        catch (IOException e)
        {
            System.err.println("error reloading " + backwardStack.peek() + e.getMessage());
            e.printStackTrace();
        }
    }
}
