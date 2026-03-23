package bankapp.progetto20242025piragine.util;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

public class ChatBot {

    // This interface defines the bot's "personality"
    public interface VirtualAssistant {
        @SystemMessage("""
                Sei l'assistente virtuale della 'Maze Bank' un'applicazione fatta da studenti universitari come progetto d'esame.
                Il tuo obiettivo è aiutare i clienti con informazioni su:
                - Blocco carta, il blocc avviene andando su "carte" , selezioni la carta che vuoi bloccare -> gestisci -> blocca carta
                - Creazione carta (non esiste debito o credito)carte -> crea nuova carta -> ricorda di ineserire un mnickname e un limite di spesa valido ->  crea carta
                - Spiegare cos'è un IBAN.
                - Dare consigli sulla sicurezza (es. non condividere mai la password).
                Sii professionale e gentile.
                non ricordare il menù ogni volta, sii piu un assistente, formatta bene il codice
            """)
        String ask(String userMessage);
    }

    private final VirtualAssistant bot;

    public ChatBot() {
        // Configuring Gemini with your API Key
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey("AIzaSyCRxVRF0VhnbIBBZmrC7AFw-BQNgaD2n7A")
                .modelName("gemini-flash-latest") // Riportiamolo al nome base
                .timeout(java.time.Duration.ofSeconds(60))
                .logRequestsAndResponses(true)
                .build();

        this.bot = AiServices.create(VirtualAssistant.class, model);
        // Creating the AI service
    }

    public String generateResponse(String text) {
        try {
            System.out.println("DEBUG: Provo a contattare Gemini...");
            return bot.ask(text);
        } catch (Exception e) {
            // QUESTO CI DIRÀ TUTTO:
            System.err.println("--- LOG ERRORE GEMINI ---");
            e.printStackTrace();

            // Restituisci l'errore specifico alla chat per vederlo subito
            return "Errore Tecnico: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
    }
}
