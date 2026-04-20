package bankapp.progetto20242025piragine.controller;

import bankapp.progetto20242025piragine.controller.component.FromFriendTransactionCloudController;
import bankapp.progetto20242025piragine.controller.component.ToFriendTransactionCloudController;
import bankapp.progetto20242025piragine.util.ChatBot;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ChatBotController extends BranchController{

    @FXML private VBox chatDisplay;
    @FXML private TextField inputField;
    @FXML private GridPane botGridPane;

    // Linking the utility class you created
    private final ChatBot geminiService = new ChatBot();

    private void addUserCloud(String text)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/toFriendTransactionCloud.fxml"));
        try
        {
            Parent cloud = loader.load();
            ToFriendTransactionCloudController controller= loader.getController();
            controller.textLabel.setText(text);
            chatDisplay.getChildren().add(cloud);
            VBox.setMargin(cloud, new Insets(0, -100 , 0, 0));
        }
        catch (Exception e)
        {
            System.err.println("error creating user's text cloud " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void addBotCloud(String text)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/fromFriendTransactionCloud.fxml"));
        try
        {
            Parent cloud = loader.load();
            FromFriendTransactionCloudController controller= loader.getController();
            controller.textLabel.setText(text);
            chatDisplay.getChildren().add(cloud);
        }
        catch (Exception e)
        {
            System.err.println("error creating bot's text cloud " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initializer() {
        ThemeManager.applyTheme(botGridPane.getScene(), rootController.user.getTheme());

        // Aggiunta logica CSS mantenendo tutto il resto intatto
        if (botGridPane != null) {
            String theme = rootController.user.getTheme();
            String cssPath = ThemeManager.getThemePath(theme);
            if (cssPath != null) {
                botGridPane.getStylesheets().clear();
                botGridPane.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
            }
        }

        // Automatic welcome message on startup
        addBotCloud( "Benvenuto in Maze Bank, come posso aiutarti?\n");
    }

    @FXML
    private void onSendButtonClick() {
        String userMessage = inputField.getText();
        if (userMessage == null || userMessage.trim().isEmpty()) return;

        processMessage(userMessage);
        inputField.clear();
    }

    // Quick button handling (as seen in the support screenshot)

    // Central method to handle communication with the AI
    private void processMessage(String text) {
        addUserCloud(text + "\n");

        // IMPORTANT: Run the AI in a separate thread to avoid freezing the UI
        new Thread(() -> {
            String aiResponse = geminiService.generateResponse(text);

            // Switch back to the JavaFX Application Thread to update the UI
            Platform.runLater(() -> {
                addBotCloud(aiResponse + "\n");
            });
        }).start();
    }
}