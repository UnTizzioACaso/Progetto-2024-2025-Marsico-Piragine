package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Notify;
import bankapp.progetto20242025piragine.db.NotifyDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

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

    private Popup notifyPopup;
    private VBox popupContent;
    private boolean popupVisible = false;

    private Stack<String> backwardStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();

    @FXML
    public void initialize() {
        // Crea popup notifiche
        notifyPopup = new Popup();
        popupContent = new VBox();
        popupContent.setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-color: gray; " +
                        "-fx-padding: 5;"
        );
        notifyPopup.getContent().add(popupContent);

        // Mostra o nasconde popup al click sulla campanella
        notificationButton.setOnMouseClicked(e -> {
            try {
                if (!popupVisible) {
                    updateNotifications();
                    javafx.geometry.Bounds bounds = notificationButton.localToScreen(notificationButton.getBoundsInLocal());
                    notifyPopup.show(notificationButton, bounds.getMinX(), bounds.getMaxY());
                    popupVisible = true;
                } else {
                    notifyPopup.hide();
                    popupVisible = false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Nasconde popup quando clicchi fuori
        popupContent.setOnMouseExited(e -> {
            notifyPopup.hide();
            popupVisible = false;
        });
    }

    private void updateNotifications() throws SQLException {
        popupContent.getChildren().clear();

        int currentUserId = 1; // TODO: sostituire con ID utente corrente

        List<Notify> notifies = NotifyDAO.getNotifyByUserId(currentUserId);

        if (notifies.isEmpty()) {
            Label empty = new Label("Nessuna notifica");
            empty.setStyle("-fx-padding: 5;");
            popupContent.getChildren().add(empty);
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
                        notifyPopup.hide();
                        popupVisible = false;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                popupContent.getChildren().add(label);
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
            controller.setRootController(rootController);
        } catch (IOException e) {
            System.err.println("error reloading " + backwardStack.peek() + e.getMessage());
            e.printStackTrace();
        }
    }
}
