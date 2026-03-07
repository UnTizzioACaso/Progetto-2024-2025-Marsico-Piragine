package bankapp.progetto20242025piragine.controller;

import bankapp.progetto20242025piragine.util.ChatBot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class ChatBotController{

    @FXML private TextArea chatDisplay;
    @FXML private TextField inputField;

    // Colleghiamo la classe di utilità che hai creato
    private final ChatBot geminiService = new ChatBot();

    @FXML
    public void initialize() {
        // Messaggio di benvenuto automatico all'avvio
        chatDisplay.appendText("PiragineBot: Benvenuto in Piragine Bank! Come posso aiutarti oggi?\n\n");
    }

    @FXML
    private void onSendButtonClick() {
        String messaggioUtente = inputField.getText();
        if (messaggioUtente == null || messaggioUtente.trim().isEmpty()) return;

        processaMessaggio(messaggioUtente);
        inputField.clear();
    }

    // Gestione dei pulsanti rapidi (come nello screenshot dell'assistenza)
    @FXML
    private void onSaldoButtonClick() {
        chatDisplay.appendText("Tu: [Richiesta Saldo]\n");
        processaMessaggio("Vorrei conoscere il mio saldo attuale.");
    }

    @FXML
    private void onSicurezzaButtonClick() {
        chatDisplay.appendText("Tu: [Info Sicurezza]\n");
        processaMessaggio("Dammi dei consigli su come proteggere il mio conto.");
    }

    // Metodo centrale per gestire la comunicazione con l'IA
    private void processaMessaggio(String testo) {
        chatDisplay.appendText("Tu: " + testo + "\n");

        // IMPORTANTE: Eseguiamo l'IA in un thread separato per non bloccare la grafica (UI)
        new Thread(() -> {
            String rispostaIA = geminiService.generaRisposta(testo);

            // Torniamo sul thread principale di JavaFX per aggiornare l'interfaccia
            Platform.runLater(() -> {
                chatDisplay.appendText("PiragineBot: " + rispostaIA + "\n\n");
            });
        }).start();
    }
}