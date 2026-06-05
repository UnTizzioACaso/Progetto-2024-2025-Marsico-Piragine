package bankapp.progetto20242025piragine.controller;

import bankapp.progetto20242025piragine.controller.component.FromUserTextCloudController;
import bankapp.progetto20242025piragine.controller.component.ToUserTextCloudController;
import bankapp.progetto20242025piragine.util.*;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

public class ChatBotController extends BranchController {

    @FXML private VBox chatDisplay;
    @FXML private TextField chatSupportTextField;
    @FXML private GridPane botGridPane;
    @FXML private ScrollPane chatSupportScrollPane;

    private final ChatBot geminiService = new ChatBot();
    private final ChatSessionSaver sessionSaver = ChatSessionSaver.getInstance();

    @FXML
    private void initialize()
    {
        loadMessages();
        if (sessionSaver.getHistory().isEmpty()) {
            String welcome = "Benvenuto in Maze Bank, come posso aiutarti?\n";
            sessionSaver.addMessage(welcome, false);
            renderBotCloud(welcome);
        }
    }

    @FXML
    private void close()
    {
        Stage primaryStage = (Stage) CurrentSession.getRootController().getRootWindow().getScene().getWindow();;
        Stage popupStage = (Stage) botGridPane.getScene().getWindow();
        double y = primaryStage.getY() + primaryStage.getHeight() - popupStage.getHeight() - 20;
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(400), botGridPane);
        slideOut.setFromY(0);
        slideOut.setToY(y+400);
        slideOut.setOnFinished(e -> {popupStage.close();});
        slideOut.play();
    }


    private void loadMessages() {

        chatDisplay.getChildren().clear();

        for (ChatSessionSaver.ChatMessage msg : sessionSaver.getHistory()) {

            if (msg.isUser) {
                renderUserCloud(msg.text);
            } else {
                renderBotCloud(msg.text);
            }
        }
    }

    @FXML
    private void onSendButtonClick() {

        String userMessage = chatSupportTextField.getText();

        if (userMessage == null || userMessage.trim().isEmpty()) {
            return;
        }

        sessionSaver.addMessage(userMessage, true);
        processMessage(userMessage);
        chatSupportTextField.clear();
    }

    private void processMessage(String text) {

        renderUserCloud(text + "\n");

        new Thread(() -> {
            String aiResponse = geminiService.generateResponse(text);
            Platform.runLater(() -> {
                sessionSaver.addMessage(aiResponse, false);
                renderBotCloud(aiResponse + "\n");
                if (chatSupportScrollPane != null)
                {
                    chatSupportScrollPane.setVvalue(1.0);
                }
            });

        }).start();
    }

    private void renderBotCloud(String text)
    {

        Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/toUserTextCloud.fxml");
        Node cloud = p.getValue();
        ToUserTextCloudController controller = (ToUserTextCloudController) p.getKey();
        controller.setText(text);
        chatDisplay.getChildren().add(cloud);
    }

    private void renderUserCloud(String text) {

        Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/FromUserTextCloud.fxml");
        Node cloud = p.getValue();
        FromUserTextCloudController controller = (FromUserTextCloudController) p.getKey();
        controller.setText(text);
        chatDisplay.getChildren().add(cloud);
        VBox.setMargin(cloud, new Insets(0, -100, 0, 0));
    }
}