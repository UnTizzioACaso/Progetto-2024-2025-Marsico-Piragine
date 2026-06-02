package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.dao.NotifyDAO;
import bankapp.progetto20242025piragine.util.*;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.List;
import java.util.Stack;

public class TopbarController extends BranchController {

    @FXML
    private ImageView backArrowButton;
    @FXML
    private GridPane topbarGridPane;

    private Stage popupStage;

    private NotificationSliderController notificationSliderController;

    private Node notificationSlider;

    private boolean sliderIsActive = false;

    private Stack<String> backwardStack = new Stack<>();

    private Stack<String> forwardStack = new Stack<>();

    public void clear()
    {
        backwardStack.clear();
        forwardStack.clear();
    }

    public boolean isSliderActive() {return sliderIsActive;}

    @FXML
    private void initialize()
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/notificationSlider.fxml");
        notificationSliderController = (NotificationSliderController) p.getKey();
        notificationSlider = p.getValue();
        double d = 0;
        AnchorPane.setTopAnchor(notificationSlider, d);
        AnchorPane.setBottomAnchor(notificationSlider, d);
        AnchorPane.setRightAnchor(notificationSlider, d);
    }

    private void showBottomRightPopup(String fxmlPath, Stage primaryStage)
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader(fxmlPath);
        Parent root = (Parent) p.getValue();
        popupStage = new Stage();
        popupStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        popupStage.initOwner(primaryStage);
        ThemeManager.applyTheme(scene, CurrentSession.getLoggedUser().getTheme());
        popupStage.setScene(scene);
        popupStage.show();
        CurrentSession.setPrimaryStage(popupStage);

        // CALCOLO POSIZIONE RISPETTO ALLA FINESTRA PRINCIPALE
        // Coordinata X: Inizio finestra + Larghezza finestra - Larghezza popup - Margine
        double x = primaryStage.getX() + primaryStage.getWidth() - popupStage.getWidth() - 20;
        // Coordinata Y: Inizio finestra + Altezza finestra - Altezza popup - Margine
        double y = primaryStage.getY() + primaryStage.getHeight() - popupStage.getHeight() - 20;
        // Chiude se clicchi fuori dal popup

        popupStage.setX(x);
        popupStage.setY(y);

        TranslateTransition slideIn = new TranslateTransition(Duration.millis(400), root);
        slideIn.setFromY(y+400);
        slideIn.setToY(0);
        slideIn.play();
    }

    @FXML
    public void showSlider()
    {
        if (!sliderIsActive) {
            updateNotifications();
            CurrentSession.getRootController().centerAnchorPane.getChildren().add(notificationSlider);
            TranslateTransition slideIn = new TranslateTransition(Duration.millis(400), notificationSlider);
            slideIn.setFromX(190);
            slideIn.setToX(0);
            slideIn.play();
            sliderIsActive = true;
            return;
        }
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(250), notificationSlider);
        slideOut.setFromX(0);
        slideOut.setToX(190);
        slideOut.setOnFinished(event -> {CurrentSession.getRootController().centerAnchorPane.getChildren().remove(notificationSlider);  sliderIsActive = false;});
        slideOut.play();
    }

    public void updateNotifications()
    {
        notificationSliderController.getNotificationVBox().getChildren().clear();
        List<Notify> notifies = NotifyDAO.getNotifyByUserId(CurrentSession.getLoggedUser().getUserID());

        if (notifies.isEmpty())
        {
            Label empty = new Label("Nessuna notifica");
            empty.setStyle("-fx-padding: 5;");
            notificationSliderController.getNotificationVBox().getChildren().add(empty);
        }
        else
        {
            for (Notify n : notifies)
            {
                if (!n.isRead())
                {
                    Node notification = VisualNotificationCreator.createVisualNotification(n);
                    notificationSliderController.getNotificationVBox().getChildren().add(notification);
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
    private void backPage()
    {
        if (backwardStack.size() > 1)
        {
            if (backwardStack.peek().equals("/bankapp/progetto20242025piragine/fxml/page/bankAccountSettingsPage.fxml")) //if we are loading the bank account settings page,
            {                                                                                                           // we need to ask for the PIN
                PopupCreator.showAndWaitPopup("Inserisci il PIN", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
                if (!CurrentSession.isPinCorrect()) {return;}
            }
            String current = backwardStack.pop();
            forwardStack.push(current);
            CurrentSession.getRootController().switchPage(backwardStack.peek());
        }
    }

    @FXML
    private void nextPage()
    {
        if (!forwardStack.isEmpty())
        {
            if (forwardStack.peek().equals("/bankapp/progetto20242025piragine/fxml/page/bankAccountSettingsPage.fxml")) //if we are loading the bank account settings page,
            {                                                                                                           // we need to ask for the PIN
                PopupCreator.showAndWaitPopup("Inserisci il PIN", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
                if (!CurrentSession.isPinCorrect()) {return;}
            }
            String page = forwardStack.pop();
            backwardStack.push(page);
            CurrentSession.getRootController().switchPage(page);
        }
    }

    @FXML
    public void reloadPage()
    {
        Node node = EasyFxmlLoader.loader(CurrentSession.getRootController().currentPage).getValue();
        CurrentSession.getRootController().setCenter(node);
    }

    @FXML
    private void showAssistance()
    {
        showBottomRightPopup("/bankapp/progetto20242025piragine/fxml/popup/chatSupport.fxml", (Stage) backArrowButton.getScene().getWindow());
    }
}
