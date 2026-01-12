package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Notify;
import bankapp.progetto20242025piragine.db.NotifyDAO;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;

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

    private ScrollPane notificationScrollPane;  {FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/popup/accountPopup.fxml"));};
    private NotificationScrollPaneController notificationScrollPaneController;
    private Stack<String> backwardStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();

    @FXML
    public void initialize() {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/notificationScrollPane.fxml"));
            notificationScrollPane =  loader.load();
            notificationScrollPaneController = loader.getController();
            notificationScrollPaneController.setRootController(rootController);
            notificationScrollPaneController.initializer();
            hidePopup();
            notificationScrollPane.setTranslateY(-20); // leggermente sopra

        }
        catch (Exception e)
        {
            System.err.println("error during /bankapp/progetto20242025piragine/fxml/component/notificationScrollPane.fxml" + e);
            e.printStackTrace();
        }


    }

    @FXML
    public void hidePopup()
    {
        TranslateTransition slideUp = new TranslateTransition();
        slideUp.setNode(notificationScrollPane);
        slideUp.setFromY(0);
        slideUp.setToY(-20);
        slideUp.setDuration(javafx.util.Duration.millis(150));
        slideUp.setInterpolator(javafx.animation.Interpolator.EASE_IN);

        javafx.animation.FadeTransition fadeOut =
                new javafx.animation.FadeTransition(javafx.util.Duration.millis(150), notificationScrollPane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        slideUp.setOnFinished(e -> {
            notificationScrollPane.setVisible(false);
            notificationScrollPane.setDisable(true);
        });

        slideUp.play();
        fadeOut.play();
    }



    @FXML
    public void showPopup()
    {
        try {
            if (notificationScrollPane.isDisable())
            {
                updateNotifications();

                notificationScrollPane.setVisible(true);
                notificationScrollPane.setDisable(false);

                // posizione iniziale (sopra)
                notificationScrollPane.setTranslateY(-20);
                notificationScrollPane.setOpacity(0);

                TranslateTransition slideDown = new TranslateTransition();
                slideDown.setNode(notificationScrollPane);
                slideDown.setFromY(-20);
                slideDown.setToY(0);
                slideDown.setDuration(javafx.util.Duration.millis(200));
                slideDown.setInterpolator(javafx.animation.Interpolator.EASE_OUT);

                javafx.animation.FadeTransition fadeIn =
                        new javafx.animation.FadeTransition(javafx.util.Duration.millis(200), notificationScrollPane);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);

                slideDown.play();
                fadeIn.play();
            }
            else
            {
                hidePopup();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    private void updateNotifications() throws SQLException {
        notificationScrollPaneController.notificationVBox.getChildren().clear();

        int currentUserId = 1; // TODO: sostituire con ID utente corrente

        List<Notify> notifies = NotifyDAO.getNotifyByUserId(currentUserId);

        if (notifies.isEmpty()) {
            Label empty = new Label("Nessuna notifica");
            empty.setStyle("-fx-padding: 5;");
            notificationScrollPaneController.notificationVBox.getChildren().add(empty);
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
                notificationScrollPaneController.notificationVBox.getChildren().add(label);
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
