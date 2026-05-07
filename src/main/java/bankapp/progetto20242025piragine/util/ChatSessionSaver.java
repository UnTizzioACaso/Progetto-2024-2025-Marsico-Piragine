package bankapp.progetto20242025piragine.util;

import java.util.ArrayList;
import java.util.List;

public class ChatSessionSaver {
    private static ChatSessionSaver instance;
    private final List<ChatMessage> history = new ArrayList<>();

    private ChatSessionSaver() {}

    public static ChatSessionSaver getInstance() {
        if (instance == null) instance = new ChatSessionSaver();
        return instance;
    }

    public void addMessage(String text, boolean isUser) {
        history.add(new ChatMessage(text, isUser));
    }

    public List<ChatMessage> getHistory() {
        return history;
    }

    public static class ChatMessage {
        public String text;
        public boolean isUser;
        public ChatMessage(String text, boolean isUser) {
            this.text = text;
            this.isUser = isUser;
        }
    }
}


