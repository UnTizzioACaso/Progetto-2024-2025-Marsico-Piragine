package bankapp.progetto20242025piragine.controller;

import bankapp.progetto20242025piragine.util.ChatBot;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.ThinkingConfig;
import com.google.genai.types.ThinkingLevel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatBotController {

    @FXML private TextArea chatDisplay;
    @FXML
    private TextField inputField;

    // Linking the utility class you created
    private final ChatBot geminiService = new ChatBot();

    @FXML
    public void initialize() {
        // Automatic welcome message on startup
        chatDisplay.appendText("MazeBot: Benvenuto in Maze Bank, come posso aiutarti?\n\n");
    }

    @FXML
    private void onSendButtonClick() {
        String userMessage = inputField.getText();
        if (userMessage == null || userMessage.trim().isEmpty()) return;

        processMessage(userMessage);
        inputField.clear();
    }

    // Central method to handle communication with the AI
    private void processMessage(String text) {
        chatDisplay.appendText("Tu: " + text + "\n");

        // IMPORTANT: Run the AI in a separate thread to avoid freezing the UI
        new Thread(() -> {
            String aiResponse = geminiService.Chat(text);

            // Switch back to the JavaFX Application Thread to update the UI
            Platform.runLater(() -> {
                chatDisplay.appendText("MazeBot: " + aiResponse + "\n\n");
            });
        }).start();
    }
}