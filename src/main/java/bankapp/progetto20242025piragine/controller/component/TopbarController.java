package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Notify;
import bankapp.progetto20242025piragine.db.NotifyDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    private NotificationAnchorPaneController notificationAnchorPaneController;
    private Stack<String> backwardStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();

    @Override
    public void initializer()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/notificationScrollPane.fxml"));
            notificationAnchorPane =  loader.load();
            notificationAnchorPaneController = loader.getController();
            notificationAnchorPaneController.setRootController(rootController);
            notificationAnchorPaneController.initializer();
            hidePopup();
        }
        catch (Exception e)
        {
            System.err.println("error during loading /bankapp/progetto20242025piragine/fxml/component/notificationAnchorPane.fxml" + e);
            e.printStackTrace();
        }
        rootController.rootWindow.setRight(notificationAnchorPane);
    }

    @FXML
    public void hidePopup()
    {
        rootController.rootWindow.setRight(null);
    }

    @FXML
    public void showPopup()
    {
        try {
            if (rootController.rootWindow.getRight() == null)
            {
                updateNotifications();
                rootController.rootWindow.setRight(notificationAnchorPane);
            }
            else
            {
                hidePopup();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateNotifications() throws SQLException {
        notificationAnchorPaneController.notificationVBox.getChildren().clear();

        int currentUserId = 1; // TODO: sostituire con ID utente corrente

        List<Notify> notifies = NotifyDAO.getNotifyByUserId(currentUserId);

        if (notifies.isEmpty()) {
            Label empty = new Label("Nessuna notifica");
            empty.setStyle("-fx-padding: 5;");
            notificationAnchorPaneController.notificationVBox.getChildren().add(empty);
        } else {
            for (Notify n : notifies) {
                Label label = new Label(n.getMessage());
                label.setStyle(
                        "-fx-padding: 5; " +
                                "-fx-border-color: lightgray; " +
                                "-fx-border-width: 0 0 1 0;"
                );
                // Cliccando su notifica -> marca come letta e chiude popup
                label.setOnMouseClicked(event -> {
                    try {
                        NotifyDAO.markAsRead(n.getIdNotify());
                        hidePopup();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                notificationAnchorPaneController.notificationVBox.getChildren().add(label);
            }
        }
    }

    // ======= Navigazione pagine =======
    @FXML
    public void visitPage(String fxml) {
        backwardStack.push(fxml);
        forwardStack.clear();
    }

    @FXML
    public void backPage() {
        if (backwardStack.size() > 1) {
            String current = backwardStack.pop();
            forwardStack.push(current);
            rootController.switchPage(backwardStack.peek());
        }
    }

    @FXML
    public void nextPage() {
        if (!forwardStack.isEmpty()) {
            String page = forwardStack.pop();
            backwardStack.push(page);
            rootController.switchPage(page);
        }
    }

    @FXML
    public void reloadPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(backwardStack.peek()));
            Parent node = fxmlLoader.load();
            BranchController controller = fxmlLoader.getController();
            controller.initializer();
            rootController.rootWindow.setCenter(node);
            controller.setRootController(rootController);
        } catch (IOException e) {
            System.err.println("error reloading " + backwardStack.peek() + e.getMessage());
            e.printStackTrace();
        }
    }
}
