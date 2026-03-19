package bankapp.progetto20242025piragine.util;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.util.ArrayList;
import java.util.List;

public class ChatBot {

    // Il client per comunicare con Google Gemini
    private final Client client = new Client();

    // Lista per mantenere la cronologia (così il bot ha memoria)
    private final List<Content> history = new ArrayList<>();


    public String Chat(String text) {
        try {
            // 1. Se è il primo messaggio, impostiamo il comportamento da Maze Bank
            if (history.isEmpty()) {
                history.add(Content.builder()
                        .role("user")
                        .parts(List.of(Part.builder()
                                .text("Sei l'assistente virtuale di Maze Bank. " +
                                        " Sei l'assistente virtuale della 'Maze Bank' un'applicazione fatta da studenti universitari come progetto d'esame.\n" +
                                        "                Il tuo obiettivo è aiutare i clienti con informazioni su:\n" +
                                        "                - Blocco carta, il blocc avviene andando su \"carte\" , selezioni la carta che vuoi bloccare -> gestisci -> blocca carta\n" +
                                        "                - Creazione carta (non esiste debito o credito)carte -> crea nuova carta -> ricorda di ineserire un mnickname e un limite di spesa valido ->  crea carta\n" +
                                        "                - Spiegare cos'è un IBAN.\n" +
                                        "                - Dare consigli sulla sicurezza (es. non condividere mai la password).\n" +
                                        "                Sii professionale e gentile.\n" +
                                        "                non ricordare il menù ogni volta, sii piu un assistente, formatta bene il codice")
                                .build()))
                        .build());
            }

            // 2. Aggiungiamo il messaggio dell'utente alla storia
            history.add(Content.builder()
                    .role("user")
                    .parts(List.of(Part.builder().text(text).build()))
                    .build());

            // 3. Chiamata al modello Gemini (Flash 3 è perfetto per velocità e costi)
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-3-flash-preview",
                    history,
                    null
            );

            String botResponse = response.text();

            // 4. Salviamo la risposta dell'AI nella storia
            history.add(Content.builder()
                    .role("model")
                    .parts(List.of(Part.builder().text(botResponse).build()))
                    .build());

            return botResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return "Errore di connessione: " + e.getLocalizedMessage();
        }
    }
}