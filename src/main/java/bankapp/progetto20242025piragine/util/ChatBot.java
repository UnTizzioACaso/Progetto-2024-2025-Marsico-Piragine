package bankapp.progetto20242025piragine.util;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

public class ChatBot {

    // Questa interfaccia definisce la "personalità" del bot
    public interface AssistenteVirtuale {
        @SystemMessage("""
            Sei l'assistente virtuale della 'Piragine Bank'. 
            Il tuo obiettivo è aiutare i clienti con informazioni su:
            - Come effettuare bonifici.
            - Spiegare cos'è un tasso d'interesse o un IBAN.
            - Dare consigli sulla sicurezza (es. non condividere mai la password).
            Sii professionale, gentile e non dare mai veri consigli finanziari legali.
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