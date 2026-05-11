package bankapp.progetto20242025piragine.controller;

import bankapp.progetto20242025piragine.controller.component.FromUserTextCloudController;
import bankapp.progetto20242025piragine.controller.component.ToUserTextCloudController;
import bankapp.progetto20242025piragine.util.ChatBot;
import bankapp.progetto20242025piragine.util.ChatSessionSaver;
import bankapp.progetto20242025piragine.util.EasyFxmlLoader;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class ChatBotController extends BranchController {

    @FXML private VBox chatDisplay;
    @FXML private TextField inputField;
    @FXML private GridPane botGridPane;
    @FXML private ScrollPane scrollPaneChatSupport;

    private final ChatBot geminiService = new ChatBot();
    private final ChatSessionSaver sessionSaver = ChatSessionSaver.getInstance();

    @Override
    public void initializer() {
        updateTheme();
        loadMessages();

        // 1. MESSAGGIO DI BENVENUTO AUTOMATICO (Solo se la chat è vuota)
        if (chatDisplay.getChildren().isEmpty()) {
            renderBotCloud("Benvenuto in Maze Bank, come posso aiutarti?\n");
        }

        Thread themeWatcher = new Thread(() -> {
            String lastTheme = rootController.user.getTheme();
            while (true) {
                try {
                    Thread.sleep(500);
                    if (rootController.user != null) {
                        String currentTheme = rootController.user.getTheme();
                        if (!currentTheme.equals(lastTheme)) {
                            lastTheme = currentTheme;
                            Platform.runLater(this::updateTheme);
                        }
                    }
                } catch (InterruptedException e) { break; }
            }
        });
        themeWatcher.setDaemon(true);
        themeWatcher.start();

        Platform.runLater(() -> {
            if (scrollPaneChatSupport != null) scrollPaneChatSupport.setVvalue(1.0);
        });
    }

    

    private void updateTheme() {
        if (botGridPane != null && rootController.user != null) {
            String theme = rootController.user.getTheme();
            String cssPath = ThemeManager.getThemePath(theme);
            if (cssPath != null) {
                botGridPane.getStylesheets().clear();
                botGridPane.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
                botGridPane.applyCss();
                botGridPane.layout();
            }
        }
    }

    private void loadMessages() {
        chatDisplay.getChildren().clear();
        for (ChatSessionSaver.ChatMessage msg : sessionSaver.getHistory()) {
            if (msg.isUser) renderUserCloud(msg.text);
            else renderBotCloud(msg.text);
        }
    }

    @FXML
    private void onSendButtonClick() {
        String userMessage = inputField.getText();
        if (userMessage == null || userMessage.trim().isEmpty()) return;
        sessionSaver.addMessage(userMessage, true);
        processMessage(userMessage);
        inputField.clear();
    }

    private void processMessage(String text) {
        renderUserCloud(text + "\n");
        new Thread(() -> {
            String aiResponse = geminiService.generateResponse(text);
            Platform.runLater(() -> {
                sessionSaver.addMessage(aiResponse, false);
                renderBotCloud(aiResponse + "\n");
                if (scrollPaneChatSupport != null) scrollPaneChatSupport.setVvalue(1.0);
            });
        }).start();
    }

    // Metodo standard per il bot (Senza margini extra)
    private void renderBotCloud(String text)
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/toUserTextCloud.fxml");
        Node cloud = p.getValue();
        ToUserTextCloudController controller = (ToUserTextCloudController) p.getKey();
        controller.textLabel.setText(text);
        chatDisplay.getChildren().add(cloud);
    }

    private void renderUserCloud(String text) {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/FromUserTextCloud.fxml");
        Node cloud = p.getValue();
        FromUserTextCloudController controller = (FromUserTextCloudController) p.getKey();
        controller.textLabel.setText(text);
        chatDisplay.getChildren().add(cloud);
        VBox.setMargin(cloud, new Insets(0, -100, 0, 0));

    }

}