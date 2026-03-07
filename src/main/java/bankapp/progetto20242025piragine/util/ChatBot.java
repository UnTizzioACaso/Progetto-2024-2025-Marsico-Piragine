package bankapp.progetto20242025piragine.util;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

public class ChatBot {

    // Questa interfaccia definisce la "personalità" del bot
    public interface AssistenteVirtuale {
        @SystemMessage("""
            Sei l'assistente virtuale della 'Maze Bank' un'applicazione fatta da studenti universitari come progetto d'esame. 
            Il tuo obiettivo è aiutare i clienti con informazioni su:
            - Blocco carta
            - Creazione carta
            - Spiegare cos'è un IBAN.
            - Dare consigli sulla sicurezza (es. non condividere mai la password).
            Sii professionale e gentile.
            """)
        String chiedi(String messaggioUtente);
    }

    private final AssistenteVirtuale bot;

    public ChatBot() {
        // Configuriamo Gemini con la tua API Key
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey("AIzaSyBXXtFOTcC7G3S1DfxdUjyfWv7dIPrz7PM")
                .modelName("gemini-1.5-flash")
                .build();

        // Creiamo il servizio AI
        this.bot = AiServices.create(AssistenteVirtuale.class, model);
    }

    public String generaRisposta(String testo) {
        try {
            return bot.chiedi(testo);
        } catch (Exception e) {
            return "Spiacente, il servizio di assistenza è momentaneamente offline. Riprova più tardi.";
        }
    }
}